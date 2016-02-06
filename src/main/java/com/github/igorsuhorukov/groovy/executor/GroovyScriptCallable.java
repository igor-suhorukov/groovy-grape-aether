package com.github.igorsuhorukov.groovy.executor;

import com.github.igorsuhorukov.codehaus.plexus.util.IOUtil;
import com.github.igorsuhorukov.groovy.GroovyMain;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;

public class GroovyScriptCallable<T extends Serializable> extends AbstractGroovyScript implements Callable<T>{

    private Map<String, Serializable> variables;

    public GroovyScriptCallable(URL scriptPath, String... args) {
        super(scriptPath, args);
    }

    public GroovyScriptCallable(URL scriptPath, Map<String, Serializable> variables, String... args) {
        super(scriptPath, args);
        validateVariables(variables);
        this.variables = variables;
    }

    public GroovyScriptCallable(String scriptSource, String... args) throws IOException {
        super(scriptSource, args);
    }

    public GroovyScriptCallable(String scriptSource, Map<String, Serializable> variables, String... args) throws IOException {
        super(scriptSource, args);
        validateVariables(variables);
        this.variables = variables;
    }

    @SuppressWarnings("unchecked")
    public T call() throws Exception {
        GroovyClassLoader groovyClassLoader = GroovyMain.getGroovyClassLoader();
        Class scriptClass = groovyClassLoader.parseClass(getScriptText());
        Binding context = new Binding(args);
        setScriptVariables(context);
        Constructor constructor = scriptClass.getConstructor(Binding.class);
        Script script = (Script) constructor.newInstance(context);
        return (T) script.run();
    }

    private String getScriptText() throws IOException {
        InputStream inputStream = scriptPath.openStream();
        try {
            return IOUtil.toString(inputStream);
        } finally {
            IOUtil.close(inputStream);
        }
    }

    private void setScriptVariables(Binding context) {
        if (variables!=null && !variables.isEmpty()){
            for (Map.Entry<String, Serializable> variable: variables.entrySet()){
                context.setProperty(variable.getKey(), variable.getValue());
            }
        }
    }

    private void validateVariables(Map<String, Serializable> variables) {
        if (variables!=null && !variables.isEmpty() && variables.containsKey("args")){
            throw new IllegalArgumentException("Variable with key 'args' is prohibited");
        }
        if(variables.containsKey(null)){
            throw new IllegalArgumentException("Variable with NULL key is prohibited");
        }
    }
}

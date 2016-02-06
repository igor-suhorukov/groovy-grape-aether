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
import java.util.concurrent.Callable;

public class GroovyScriptCallable<T extends Serializable> extends AbstractGroovyScript implements Callable<T>{

    public GroovyScriptCallable(URL scriptPath, String... args) {
        super(scriptPath, args);
    }

    public GroovyScriptCallable(String scriptSource, String... args) throws IOException {
        super(scriptSource, args);
    }

    @SuppressWarnings("unchecked")
    public T call() throws Exception {
        GroovyClassLoader groovyClassLoader = GroovyMain.getGroovyClassLoader();
        Class scriptClass = groovyClassLoader.parseClass(getScriptText());
        Binding context = new Binding();
        context.setProperty("args", args);
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
}

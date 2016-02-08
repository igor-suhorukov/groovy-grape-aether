package com.github.igorsuhorukov.groovy.executor.settings;

import com.github.igorsuhorukov.codehaus.plexus.util.IOUtil;
import com.github.igorsuhorukov.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Map;

public class ScriptSettings extends BaseSettings implements Serializable{

    protected Map<String, Serializable> variables;

    ScriptSettings(String scriptPath, String scriptSource,
                          Map<String, String> systemProperties, Map<String, Serializable> variables, String... args) {
        super(scriptPath, scriptSource, systemProperties, args);
        this.variables = variables;
        validateVariables(variables);
    }

    public Map<String, Serializable> getVariables() {
        return variables;
    }

    @Override
    public String getGroovySource() throws IOException {
        if(StringUtils.hasText(getScriptPath())){
            InputStream inputStream = new URL(getScriptPath()).openStream();
            try {
                return IOUtil.toString(inputStream);
            } finally {
                IOUtil.close(inputStream);
            }
        } else {
            return getScriptSource();
        }
    }

    private void validateVariables(Map<String, Serializable> variables) {
        if (variables!=null && !variables.isEmpty() && variables.containsKey("args")){
            throw new IllegalArgumentException("Variable with key 'args' is prohibited");
        }
        if(variables!=null && variables.containsKey(null)){
            throw new IllegalArgumentException("Variable with NULL key is prohibited");
        }
    }
}

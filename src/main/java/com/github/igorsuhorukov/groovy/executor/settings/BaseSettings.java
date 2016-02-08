package com.github.igorsuhorukov.groovy.executor.settings;

import com.github.igorsuhorukov.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSettings implements Serializable{
    protected Map<String, String> systemProperties = new HashMap<String, String>();
    protected String[] args;
    protected String scriptPath;
    protected String scriptSource;

    protected BaseSettings(String scriptPath, String scriptSource, Map<String, String> systemProperties, String... args) {
        this.systemProperties = systemProperties;
        this.args = args;
        this.scriptPath = scriptPath;
        this.scriptSource = scriptSource;
        if(!StringUtils.hasText(scriptPath) && !StringUtils.hasText(scriptSource)){
            throw new IllegalArgumentException("scriptPath and scriptSource both empty at the same time");
        }
        if(StringUtils.hasText(scriptPath) && StringUtils.hasText(scriptSource)){
            throw new IllegalArgumentException("scriptPath and scriptSource both defined at the same time");
        }
    }

    public Map<String, String> getSystemProperties() {
        return systemProperties;
    }

    public String[] getArgs() {
        return args;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public String getScriptSource() {
        return scriptSource;
    }

    public abstract String getGroovySource() throws IOException;

    public void setSystemProperties() {
        if(getSystemProperties()!=null && !getSystemProperties().isEmpty()) {
            for(Map.Entry<String, String> property: getSystemProperties().entrySet()){
                System.setProperty(property.getKey(), property.getValue());
            }
        }
    }
}

package com.github.igorsuhorukov.groovy.executor.settings;

import java.util.Map;

public class GroovyMainSettingsBuilder {
    private String scriptPath;
    private String scriptSource;
    private Map<String, String> systemProperties;
    private String[] args;

    public GroovyMainSettingsBuilder setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
        return this;
    }

    public GroovyMainSettingsBuilder setScriptSource(String scriptSource) {
        this.scriptSource = scriptSource;
        return this;
    }

    public GroovyMainSettingsBuilder setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
        return this;
    }

    public GroovyMainSettingsBuilder setArgs(String... args) {
        this.args = args;
        return this;
    }

    public GroovyMainSettings createGroovyMainSettings() {
        return new GroovyMainSettings(scriptPath, scriptSource, systemProperties, args);
    }
}
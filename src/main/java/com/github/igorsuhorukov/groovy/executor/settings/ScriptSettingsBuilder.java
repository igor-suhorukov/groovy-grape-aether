package com.github.igorsuhorukov.groovy.executor.settings;

import java.io.Serializable;
import java.util.Map;

public class ScriptSettingsBuilder {
    private String scriptPath;
    private String scriptSource;
    private Map<String, String> systemProperties;
    private Map<String, Serializable> variables;
    private String[] args;

    public ScriptSettingsBuilder setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
        return this;
    }

    public ScriptSettingsBuilder setScriptSource(String scriptSource) {
        this.scriptSource = scriptSource;
        return this;
    }

    public ScriptSettingsBuilder setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
        return this;
    }

    public ScriptSettingsBuilder setVariables(Map<String, Serializable> variables) {
        this.variables = variables;
        return this;
    }

    public ScriptSettingsBuilder setArgs(String... args) {
        this.args = args;
        return this;
    }

    public ScriptSettings createScriptSettings() {
        return new ScriptSettings(scriptPath, scriptSource, systemProperties, variables, args);
    }
}
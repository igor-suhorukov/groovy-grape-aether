package com.github.igorsuhorukov.groovy.executor;

import com.github.igorsuhorukov.groovy.GroovyMain;
import com.github.igorsuhorukov.groovy.executor.settings.GroovyMainSettings;
import com.github.igorsuhorukov.springframework.util.Assert;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class GroovyScriptRunnable implements Runnable, Serializable{

    private GroovyMainSettings groovyMainSettings;

    public GroovyScriptRunnable(GroovyMainSettings groovyMainSettings) {
        Assert.notNull(groovyMainSettings, "groovyMainSettings is null");
        this.groovyMainSettings = groovyMainSettings;
    }

    public void run() {
        groovyMainSettings.setSystemProperties();
        try {
            GroovyMain.main(getGroovyParameters());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected String[] getGroovyParameters() throws IOException {
        ArrayList<String> groovyParams = new ArrayList<String>();
        String scriptPath = groovyMainSettings.getGroovySource();
        if(scriptPath!=null){
            groovyParams.add(scriptPath);
        }
        if (groovyMainSettings.getArgs()!=null && groovyMainSettings.getArgs().length>0){
            Collections.addAll(groovyParams, groovyMainSettings.getArgs());
        }
        return groovyParams.toArray(new String[groovyParams.size()]);
    }
}

package com.github.igorsuhorukov.groovy.executor;

import com.github.igorsuhorukov.groovy.GroovyMain;

import java.io.IOException;
import java.net.URL;

public class GroovyScriptRunnable extends AbstractGroovyScript implements Runnable{

    public GroovyScriptRunnable(URL scriptPath, String... args) {
        super(scriptPath, args);
    }

    public GroovyScriptRunnable(String scriptSource, String... args) throws IOException {
        super(scriptSource, args);
    }

    public void run() {
        try {
            GroovyMain.main(getGroovyParameters());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

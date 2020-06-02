package com.github.igorsuhorukov.groovy.executor;

import com.github.igorsuhorukov.smreed.dropship.Dropship;

import java.io.Serializable;
import java.util.Map;

import static com.github.igorsuhorukov.google.common.base.Joiner.checkArgument;
import static com.github.igorsuhorukov.google.common.base.Joiner.checkNotNull;


public class DropshipRunnable implements Runnable, Serializable{

    private String[] args;
    private Map<String, String> systemProperties;

    public DropshipRunnable(String... args) {
        args = checkNotNull(args);
        checkArgument(args.length >= 2, "Must specify groupId:artifactId[:version] and classname!");
        this.args = args;
    }

    public DropshipRunnable(Map<String, String> systemProperties, String... args) {
        this(args);
        this.systemProperties = systemProperties;
    }

    public void run() {
        setSystemProperties();
        try {
            Dropship.main(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setSystemProperties() {
        if(systemProperties!=null && !systemProperties.isEmpty()) {
            for(Map.Entry<String, String> property: systemProperties.entrySet()){
                System.setProperty(property.getKey(), property.getValue());
            }
        }
    }
}

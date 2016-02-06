package com.github.igorsuhorukov.groovy;

import groovy.lang.GroovyClassLoader;

public class GroovyMain {

    static {URLStreamHandlerFactoryInitialization.init();}

    public static void main(String[] args) throws Exception{
        getGroovyClassLoader();
        groovy.ui.GroovyMain.main(args);
    }

    public static GroovyClassLoader getGroovyClassLoader() {
        return GroovyClassLoaderFactory.createGroovyClassLoader();
    }
}

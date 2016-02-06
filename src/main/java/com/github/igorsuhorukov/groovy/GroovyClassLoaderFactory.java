package com.github.igorsuhorukov.groovy;

import com.github.igorsuhorukov.AetherEngine;
import groovy.lang.GroovyClassLoader;

public class GroovyClassLoaderFactory {

    static {URLStreamHandlerFactoryInitialization.init();}

    public static GroovyClassLoader createGroovyClassLoader(){
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        AetherEngine.install(groovyClassLoader);
        return groovyClassLoader;
    }
}

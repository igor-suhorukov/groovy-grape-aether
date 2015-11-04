package com.github.igorsuhorukov;

import groovy.lang.GroovyClassLoader;

public class GroovyClassLoaderFactory {

    public static GroovyClassLoader createGroovyClassLoader(){
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        AetherEngine.install(groovyClassLoader);
        return groovyClassLoader;
    }
}

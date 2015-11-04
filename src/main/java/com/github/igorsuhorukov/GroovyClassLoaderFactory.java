package com.github.igorsuhorukov;

import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;

public class GroovyClassLoaderFactory {

    public GroovyClassLoader createGroovyClassLoader(){
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        AetherEngine.install(groovyClassLoader);
        return groovyClassLoader;
    }
}

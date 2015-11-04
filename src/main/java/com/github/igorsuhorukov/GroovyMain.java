package com.github.igorsuhorukov;

public class GroovyMain {

    public static void main(String[] args) throws Exception{
        GroovyClassLoaderFactory.createGroovyClassLoader();
        groovy.ui.GroovyMain.main(args);
    }
}

package com.github.igorsuhorukov;

import com.github.igorsuhorukov.url.handler.mvn.MavenURLStreamHandlerFactory;

import java.net.URL;

public class GroovyMain {

    public static void main(String[] args) throws Exception{
        URL.setURLStreamHandlerFactory(new MavenURLStreamHandlerFactory());
        GroovyClassLoaderFactory.createGroovyClassLoader();
        groovy.ui.GroovyMain.main(args);
    }
}

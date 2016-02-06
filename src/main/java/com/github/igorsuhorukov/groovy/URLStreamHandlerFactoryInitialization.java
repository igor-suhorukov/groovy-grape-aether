package com.github.igorsuhorukov.groovy;

import com.github.igorsuhorukov.url.handler.UniversalURLStreamHandlerFactory;

import java.net.URL;
import java.net.URLStreamHandlerFactory;

public class URLStreamHandlerFactoryInitialization {

    static final URLStreamHandlerFactory streamHandlerFactory;

    static {
        if(Boolean.getBoolean("skipURLStreamHandlerFactoryInit")) {
            streamHandlerFactory = null;
        } else {
            streamHandlerFactory = new UniversalURLStreamHandlerFactory();
            URL.setURLStreamHandlerFactory(streamHandlerFactory);
        }
    }

    public static void init(){}
}

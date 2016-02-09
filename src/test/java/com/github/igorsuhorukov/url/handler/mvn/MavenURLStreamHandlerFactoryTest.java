package com.github.igorsuhorukov.url.handler.mvn;

import com.github.igorsuhorukov.codehaus.plexus.util.IOUtil;
import com.github.igorsuhorukov.url.handler.UniversalURLStreamHandlerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MavenURLStreamHandlerFactoryTest {

    @BeforeClass
    public static void setUp() throws Exception {
        try {
            URL.setURLStreamHandlerFactory(new UniversalURLStreamHandlerFactory());
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
    }

    @Test
    public void testMavenUrlWithSlash() throws Exception {
        String textContent = getTextContent("mvn:/com.google.guava:guava:pom:18.0");
        Assert.assertTrue(textContent.contains("<name>Guava: Google Core Libraries for Java</name>"));
    }

    @Test
    public void testMavenUrlWithoutSlash() throws Exception {
        String textContent = getTextContent("mvn:com.google.guava:guava:pom:18.0");
        Assert.assertTrue(textContent.contains("<name>Guava: Google Core Libraries for Java</name>"));
    }

    private String getTextContent(String gav) throws IOException {
        String textContent = null;
        InputStream input = new URL(gav).openStream();
        try {
            textContent = IOUtil.toString(input);
        } finally {
            IOUtil.close(input);
        }
        return textContent;
    }
}

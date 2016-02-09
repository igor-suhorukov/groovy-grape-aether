package com.github.igorsuhorukov.service;

import com.github.igorsuhorukov.DefaultAetherGrapeEngine;
import groovy.grape.GrapeEngine;
import org.junit.Test;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GrapeTest {
    @Test
    public void testGetGrapeSerive() throws Exception {
        ClassLoader classLoader = MavenServiceLoader.class.getClassLoader();
        Collection<GrapeEngine> grapes = MavenServiceLoader.loadServices(classLoader, GrapeEngine.class);
        assertNotNull(grapes);
        assertEquals(1, grapes.size());
        GrapeEngine grapeEngine = grapes.iterator().next();
        assertTrue(grapeEngine instanceof DefaultAetherGrapeEngine);
    }
}

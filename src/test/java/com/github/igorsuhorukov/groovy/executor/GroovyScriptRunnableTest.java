package com.github.igorsuhorukov.groovy.executor;

import com.github.igorsuhorukov.groovy.executor.settings.GroovyMainSettingsBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GroovyScriptRunnableTest {

    private static ExecutorService executorService;

    @BeforeClass
    public static void setUp() throws Exception {
        executorService = Executors.newSingleThreadExecutor();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        executorService.shutdownNow();
    }

    @Test
    public void testIsGrovyExecuted() throws Exception {
        assertNull(System.getProperty("SUPER_PROPERTY"));
        GroovyScriptRunnable task = new GroovyScriptRunnable(new GroovyMainSettingsBuilder()
                        .setScriptSource("System.setProperty('SUPER_PROPERTY','SET')").createGroovyMainSettings());
        executorService.submit(task).get();
        assertEquals("SET", System.getProperty("SUPER_PROPERTY"));
    }

    @Test
    public void testGroovyExecutionException() throws Exception {
        GroovyScriptRunnable task = new GroovyScriptRunnable(new GroovyMainSettingsBuilder().setScriptSource("sdghs").createGroovyMainSettings());
        try {
            executorService.submit(task).get();
        } catch (Throwable e) {
            assertTrue(e.getMessage().contains("Use of System.exit() is forbidden!"));
        }
    }
}

package com.github.igorsuhorukov.groovy.executor;

import com.github.igorsuhorukov.groovy.executor.settings.ScriptSettings;
import com.github.igorsuhorukov.groovy.executor.settings.ScriptSettingsBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroovyScriptCallableTest {
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
    public void testIsScriptReturnValue() throws Exception {
        ScriptSettings scriptSettings = new ScriptSettingsBuilder().setScriptSource("Integer.MIN_VALUE").createScriptSettings();
        Integer result = executorService.submit(new GroovyScriptCallable<Integer>(scriptSettings)).get();
        assertEquals(new Integer(Integer.MIN_VALUE), result);
    }

    @Test
    public void testSystemExitInScript() throws Exception {
        ScriptSettings scriptSettings = new ScriptSettingsBuilder().setScriptSource("System.exit(1)").createScriptSettings();
        try {
            executorService.submit(new GroovyScriptCallable<Serializable>(scriptSettings)).get();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Use of System.exit() is forbidden!"));
        }

    }
}

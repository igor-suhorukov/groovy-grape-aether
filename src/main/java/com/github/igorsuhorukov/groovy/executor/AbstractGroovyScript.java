package com.github.igorsuhorukov.groovy.executor;

import com.github.igorsuhorukov.codehaus.plexus.util.IOUtil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

abstract class AbstractGroovyScript implements Serializable {

    final protected URL scriptPath;
    final protected String[] args;

    public AbstractGroovyScript(URL scriptPath, String... args) {
        validateArguments(scriptPath, args);
        this.scriptPath = scriptPath;
        this.args = args;
    }

    public AbstractGroovyScript(String scriptSource, String... args) throws IOException {
        this(generateScriptFileName(scriptSource), args);
    }

    protected String[] getGroovyParameters() {
        ArrayList<String> groovyParams = new ArrayList<String>();
        if(scriptPath!=null){
            groovyParams.add(scriptPath.toString());
        }
        if (args!=null && args.length>0){
            Collections.addAll(groovyParams, args);
        }
        return groovyParams.toArray(new String[groovyParams.size()]);
    }

    private static URL generateScriptFileName(String scriptSource) throws IOException {
        String fileName = "script" + System.currentTimeMillis() + Math.abs(scriptSource.hashCode()) + ".groovy";
        File scriptFile = new File(System.getProperty("java.io.tmpdir"), fileName);
        scriptFile.deleteOnExit();
        FileOutputStream fileOutputStream = new FileOutputStream(scriptFile);
        try {
            IOUtil.copy(new StringReader(scriptSource), fileOutputStream);
        } finally {
            IOUtil.close(fileOutputStream);
        }
        return scriptFile.toURI().toURL();
    }

    private void validateArguments(URL scriptPath, String[] args) {
        if(scriptPath==null || scriptPath.getFile() == null) throw new IllegalArgumentException("scriptPath");
        if(args!=null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg == null) throw new IllegalArgumentException(String.format("args[%d] is null", i));
            }
        }
    }
}

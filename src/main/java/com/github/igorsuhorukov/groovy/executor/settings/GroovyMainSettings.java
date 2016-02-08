package com.github.igorsuhorukov.groovy.executor.settings;

import com.github.igorsuhorukov.codehaus.plexus.util.IOUtil;
import com.github.igorsuhorukov.springframework.util.StringUtils;

import java.io.*;
import java.util.Map;

public class GroovyMainSettings extends BaseSettings implements Serializable{

    GroovyMainSettings(String scriptPath, String scriptSource,
                              Map<String, String> systemProperties, String... args) {
        super(scriptPath, scriptSource, systemProperties, args);
    }

    @Override
    public String getGroovySource() throws IOException {
        if(StringUtils.hasText(getScriptPath())){
            return getScriptPath();
        } else {
            return saveScriptSourceToFile();
        }
    }

    private String saveScriptSourceToFile() throws IOException {
        String fileName = "script" + System.currentTimeMillis() + Math.abs(getScriptSource().hashCode()) + ".groovy";
        File scriptFile = new File(System.getProperty("java.io.tmpdir"), fileName);
        scriptFile.deleteOnExit();
        FileOutputStream fileOutputStream = new FileOutputStream(scriptFile);
        try {
            IOUtil.copy(new StringReader(getScriptSource()), fileOutputStream);
        } finally {
            IOUtil.close(fileOutputStream);
        }
        return scriptFile.getCanonicalPath();
    }
}

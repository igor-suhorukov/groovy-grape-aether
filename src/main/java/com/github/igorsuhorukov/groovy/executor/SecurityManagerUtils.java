package com.github.igorsuhorukov.groovy.executor;

import org.codehaus.groovy.tools.shell.util.NoExitSecurityManager;

public class SecurityManagerUtils {

    static void applyNoExitSecurityManager() {
        if(!Boolean.getBoolean("skipSetupNoExit")) {
            System.setSecurityManager(new NoExitSecurityManager());
        }
    }
}

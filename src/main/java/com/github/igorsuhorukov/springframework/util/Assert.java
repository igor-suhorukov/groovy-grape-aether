package com.github.igorsuhorukov.springframework.util;

public class Assert {
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}

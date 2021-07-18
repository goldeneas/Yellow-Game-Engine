package com.yellow.engine.utils;

public class Logger {

    // TODO: Questi metodi possono essere uniti e puliti creando
    // un metodo log(prefix, string) che viene usato da entrambi.
    // O forse no, non so quanto convenga.

    public static void debug(String string) {
        System.out.println(String.format("[DEBUG] %s", string));
    }

    public static void error(String string) {
        System.out.println(String.format("[ERROR] %s", string));
    }

}

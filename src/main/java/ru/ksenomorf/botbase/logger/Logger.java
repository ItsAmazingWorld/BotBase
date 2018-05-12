/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.logger;

public class Logger {
    /**
     * Prints out a log message
     * @param message The message
     */
    public static void info(String message){
        System.out.println("[Bot Thread/INFO] " + message);
    }

    /**
     * Prints out a warning message
     * @param message The message
     */
    public static void warn(String message){
        System.out.println("[Bot Thread/WARN] " + message);
    }

    /**
     * Prints out an error message
     * @param message The message
     */
    public static void severe(String message){
        System.out.println("[Bot Thread/SEVERE] " + message);
    }
}

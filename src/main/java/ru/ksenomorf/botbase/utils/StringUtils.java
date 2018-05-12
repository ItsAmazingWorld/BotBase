/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.utils;

public class StringUtils {
    public static String getTextBefore(String text, int beforeLimit){
        return text.length() <= beforeLimit ? "" : text.substring(0, Math.min(text.length(), beforeLimit));
    }
}

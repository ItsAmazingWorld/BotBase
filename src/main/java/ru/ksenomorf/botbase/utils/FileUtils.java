/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**
     * Prints out a text message to a file
     * @param file File path
     * @param text Text that should be written
     * @throws NullPointerException if the given text is empty
     * @throws IOException if some error occurred or file is not found
     */
    public static void setText(String file, String text) throws NullPointerException, IOException {
        File filePath = new File(file);
        if(!filePath.exists()) throw new FileNotFoundException("File not found");
        if(text == null || text.isEmpty()) throw new NullPointerException("Text is empty");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write(text);
        bufferedWriter.close();
    }

    /**
     * Appends text to a file
     * @param filePath File path
     * @param text The text that should be appended
     * @throws IOException if the file is empty, text is empty or some other error occurred
     */
    public static void appendText(String filePath, String text) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) throw new IOException("File not found");
        if(text.isEmpty()) throw new NullPointerException("Text is empty");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.write(text);
        bufferedWriter.close();
    }

    /**
     * Reads file from text with specified encoding
     * @param filePath File path
     * @param encoding Encoding that you are going to read file with
     * @return File text
     * @throws IOException If was not found or some other error occurred
     */
    public static String readText(String filePath, String encoding) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) throw new IOException("File not found");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));

        StringBuilder stringBuilder = new StringBuilder();
        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            stringBuilder.append(currentLine);
        }

        return stringBuilder.toString();
    }

    /**
     * Reads file text with default encoding "UTF-8"
     * @param filePath File path
     * @return File text
     * @throws IOException If the file was not found or some other error occurred
     * @see FileUtils#readText(String,String) for encoding types
     */
    public static String readText(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) throw new IOException("File not found");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            stringBuilder.append(currentLine);
        }

        return stringBuilder.toString();
    }

    /**
     * Reads list from a file
     * @param filePath File path
     * @return Read list
     * @throws IOException If the file was not found or some other error occurred
     */
    public static List<String> readList(String filePath) throws IOException{
        File file = new File(filePath);
        if(!file.exists()) throw new IOException("File not found");

        List<String> tempList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String currentLine;

        while((currentLine = bufferedReader.readLine()) != null){
            tempList.add(currentLine);
        }

        return tempList;
    }
}

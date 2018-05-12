/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.config;

import ru.ksenomorf.botbase.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {
    /** Bot's token **/
    private static String botToken;

    /** Bot's prefix **/
    private static String botPrefix;

    /** Current playing game **/
    private static String botGame;

    /** Current playing status **/
    private static String botPlayingStatus;

    /** Bot admins **/
    private static List<String> botAdmins;

    /**
     * Initializes config file
     * @throws IOException If something went wrong
     */
    public Config() throws IOException {
        File IbotToken, IbotPrefix, IbotGame, IbotPlayingStatus, IbotAdmins;
        IbotToken = new File("botToken.xconf");
        IbotPrefix = new File("botPrefix.xconf");
        IbotGame = new File("botGame.xconf");
        IbotPlayingStatus = new File("botPlayStatus.xconf");
        IbotAdmins = new File("botAdmins.xconf");

        if(!IbotToken.exists()) {
            IbotToken.createNewFile();
            FileUtils.setText(IbotToken.getAbsolutePath(), "empty token");
        }else{
            botToken = FileUtils.readText(IbotToken.getAbsolutePath());
        }

        if(!IbotAdmins.exists()) {
            IbotAdmins.createNewFile();
            FileUtils.setText(IbotAdmins.getAbsolutePath(), "put your id here LOL");
            botAdmins = FileUtils.readList(IbotAdmins.getAbsolutePath());
        }else{
            botAdmins = FileUtils.readList(IbotAdmins.getAbsolutePath());
        }

        if(!IbotPrefix.exists()) {
            IbotPrefix.createNewFile();
            FileUtils.setText(IbotPrefix.getAbsolutePath(), "ks!");
        }else{
            botPrefix = FileUtils.readText(IbotPrefix.getAbsolutePath());
        }

        if(!IbotGame.exists()) {
            IbotGame.createNewFile();
            FileUtils.setText(IbotGame.getAbsolutePath(), FileUtils.readText(IbotPrefix.getAbsolutePath()) + "help");
        }else{
            botGame = FileUtils.readText(IbotGame.getAbsolutePath());
        }

        if(!IbotPlayingStatus.exists()) {
            IbotPlayingStatus.createNewFile();
            FileUtils.setText(IbotPlayingStatus.getAbsolutePath(), "PLAYING"); // Available modes: PLAYING, WATCHING, LISTENING
        }else{
            botPlayingStatus = FileUtils.readText(IbotPlayingStatus.getAbsolutePath());
        }

        // etc..
    }

    /**
     * Gets the bot's prefix
     * @return Bot prefix
     */
    public static String getBotToken() {
        return botToken;
    }

    /**
     * Sets up a bot token
     * @param botToken Bot token
     * @throws IOException if the file was empty or some other error occurred
     */
    public static void setBotToken(String botToken) throws IOException {
        Config.botToken = botToken;
        FileUtils.setText(new File("botToken.xconf").getAbsolutePath(), botToken);
    }

    /**
     * Returns the bot's prefix
     * @return Bot prefix
     */
    public static String getBotPrefix() {
        return botPrefix;
    }

    /**
     * Sets up a bot prefix
     * @param botPrefix Bot prefix
     * @throws IOException if the file was empty or some other error occurred
     */
    public static void setBotPrefix(String botPrefix) throws IOException {
        Config.botPrefix = botPrefix;
        FileUtils.setText(new File("botPrefix.xconf").getAbsolutePath(), botPrefix);
    }

    /**
     * Returns bot's game
     * @return Bot game
     */
    public static String getBotGame() {
        return botGame;
    }

    /**
     * Sets the bot's current playing game
     * @param botGame Bot game
     * @throws IOException if the file was empty or some other error occurred
     */
    public static void setBotGame(String botGame) throws IOException {
        Config.botGame = botGame;
        FileUtils.setText(new File("botGame.xconf").getAbsolutePath(), botGame);
    }

    /**
     * Returns bot's current playing status, defined in the config file
     * @return Current playing status
     */
    public static String getBotPlayingStatus() {
        return botPlayingStatus;
    }

    /**
     * Sets up a bot playing status
     * @param botPlayingStatus Status, available: PLAYING, WATCHING, LISTENING
     * @throws IOException if the file was empty or some other error occurred
     */
    public static void setBotPlayingStatus(String botPlayingStatus) throws IOException {
        Config.botPlayingStatus = botPlayingStatus;
        FileUtils.setText(new File("botPlayStatus.xconf").getAbsolutePath(), botPlayingStatus);
    }

    /**
     * Returns bot administrator
     * @return Bot administrators
     */
    public static List<String> getBotAdmins() {
        return botAdmins;
    }

    /**
     * Adds an admin to the bot's admin list and saves the file
     * @param adminID User's ID
     */
    public static void addBotAdmin(String adminID) throws IOException {
        Config.botAdmins.add(adminID);
        FileUtils.appendText(new File("botAdmins.xconf").getAbsolutePath(), adminID + "\n");
    }

    /**
     * Determines is user an admin or not
     * @param userID The user's ID
     * @return Is user admin?
     */
    public static boolean isUserAdmin(String userID){
        return Config.botAdmins.contains(userID);
    }
}

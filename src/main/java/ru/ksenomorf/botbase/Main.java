/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import ru.ksenomorf.botbase.command.Command;
import ru.ksenomorf.botbase.command.CommandManager;
import ru.ksenomorf.botbase.command.commands.admin.ShutdownCommand;
import ru.ksenomorf.botbase.command.commands.other.PingCommand;
import ru.ksenomorf.botbase.config.Config;
import ru.ksenomorf.botbase.listeners.MessageListener;
import ru.ksenomorf.botbase.logger.Logger;
import ru.ksenomorf.botbase.utils.GameUtils;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    /** JDA var, used to access self user, etc.. **/
    public static JDA bot;

    /** Bot name **/
    public static String botName = "Ksenomorf BotBase";

    /** Bot version **/
    public static String botVersion = "1.0-SNAPSHOT";

    /** Command statistic **/
    public static int commandsCalled = 0;

    /** Configuration File **/
    public static Config configFile;

    /**
     * Launches the bot
     * @param args Program arguments
     * @throws IOException If something went wrong
     */
    public static void main(String[] args) throws IOException {
        Logger.info("Starting " + botName + " v" + botVersion + "...");

        // Reading the bot config file
        Logger.info("Reading config...");
        configFile = new Config();

        Logger.info("Bot prefix: " + Config.getBotPrefix());
        Logger.info("Logging in with bot token: " + Config.getBotToken());

        // Logging in to a bot account
        try {
            bot = new JDABuilder(AccountType.BOT).setToken(Config.getBotToken()).buildBlocking();
        } catch (LoginException e) {
            Logger.severe("A login exception has occurred! Please check your config file! " + e);
        } catch (InterruptedException e) {
            Logger.severe("Login process was interrupted");
        }

        Logger.info("Successfully logged in!");

        // Setting up game
        bot.getPresence().setGame(Game.of(GameUtils.getGameType(Config.getBotPlayingStatus()), Config.getBotGame()));

        // Setting up listeners
        Logger.info("Began setting up listeners...");

        bot.addEventListener(new MessageListener());

        // Loading commands
        Logger.info("Began loading commands");

        addCommand(new PingCommand());
        addCommand(new ShutdownCommand());

        Logger.info("[CommandManager] Loaded " + CommandManager.getCommands().size() + " commands!");
        Logger.info("Bot is ready!");
    }

    /**
     * Adds a command to the command list
     * @param c Command
     */
    private static void addCommand(Command c){
        CommandManager.registerCommand(c);
        Logger.info("[CommandManager] Loaded command: " + "{" + CommandManager.getAllCommandAliases(c, ",") + "}");
    }
}

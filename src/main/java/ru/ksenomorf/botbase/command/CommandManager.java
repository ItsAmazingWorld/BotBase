/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.command;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import ru.ksenomorf.botbase.Main;
import ru.ksenomorf.botbase.config.Config;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    /** Commands list **/
    private static List<Command> commands = new ArrayList<>();

    /** Returns commands **/
    public static List<Command> getCommands() { return commands; }

    /** Adds a command to the list **/
    public static void registerCommand(Command c) { commands.add(c); }

    /**
     * Executes a command
     * @param message Message WITHOUT bot prefix
     * @param event Message event
     * @throws Throwable If there was an error executing the command
     */
    public static void callCommand(String message, MessageReceivedEvent event) throws Throwable {
        String[] rawArgs = message.split(" ");
        String commandCalled = rawArgs[0];
        String[] args = message.substring(commandCalled.length()).split(" ");

        for(Command c : getCommands()){
            for(String s : c.getAlias()){
                if(s.equalsIgnoreCase(commandCalled)){
                    if(c.getSide() == CommandSide.GUILD_ONLY && event.getGuild() == null){
                        event.getChannel().sendMessage("**This command is only available in guilds!**").queue();
                        return;
                    }else if(c.getSide() == CommandSide.DM_ONLY && event.getGuild() != null){
                        event.getChannel().sendMessage("**This command is only available in DM!**").queue();
                        return;
                    }else if(c.getCategory() == CommandCategory.MOD && !event.getMember().hasPermission(Permission.MANAGE_SERVER)){
                        event.getChannel().sendMessage("**This command is only available to members with the MANAGE_SERVER permission!**").queue();
                        return;
                    }else if(c.getCategory() == CommandCategory.ADMIN && !Config.isUserAdmin(event.getAuthor().getId())){
                        event.getChannel().sendMessage("**You don't have enough permissions to execute this command**").queue();
                        return;
                    }

                    c.onCommand(args, event);
                    Main.commandsCalled++;
                }
            }
        }
    }

    /**
     * Returns all command aliases in a string
     * @param c Command
     * @param delimiter Delimiter, like "," or " "
     * @return All command aliases
     */
    public static String getAllCommandAliases(Command c, String delimiter){
        String commandAliases = "";
        for(String s : c.getAlias()){
            commandAliases += s + delimiter;
        }
        return commandAliases;
    }
}

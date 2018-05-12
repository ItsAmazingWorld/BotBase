/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.listeners;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ru.ksenomorf.botbase.command.CommandManager;
import ru.ksenomorf.botbase.config.Config;
import ru.ksenomorf.botbase.logger.Logger;
import ru.ksenomorf.botbase.utils.StringUtils;

public class MessageListener extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getAuthor().isBot() || event.getGuild() != null & !event.getTextChannel().canTalk() || event.getMessage().getContentRaw().length() <= Config.getBotPrefix().length()) return;

        if(event.getGuild() != null) Logger.info("(" + event.getGuild().getName() + "/#" + event.getChannel().getName() + ") " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
            else Logger.info("(DM) " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());

        String messagePrefix = StringUtils.getTextBefore(event.getMessage().getContentRaw(), Config.getBotPrefix().length());
        String commandMessage = event.getMessage().getContentRaw().substring(Config.getBotPrefix().length());

        if(messagePrefix.equalsIgnoreCase(Config.getBotPrefix())){
            try{
                CommandManager.callCommand(commandMessage, event);
            }catch(Throwable throwable){
                Logger.severe("An error has occurred while performing a command: " + throwable);
                Logger.severe("Stack trace: ");
                throwable.printStackTrace();
            }
        }
    }
}

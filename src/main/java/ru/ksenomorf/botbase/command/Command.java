/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {
    public abstract String[] getAlias();
    public abstract String getDescription();
    public abstract CommandCategory getCategory();
    public abstract CommandSide getSide();
    public abstract void onCommand(String[] args, MessageReceivedEvent e) throws Throwable;
}

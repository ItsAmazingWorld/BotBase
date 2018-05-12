/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.command.commands.admin;

import javafx.geometry.Side;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import ru.ksenomorf.botbase.Main;
import ru.ksenomorf.botbase.command.Command;
import ru.ksenomorf.botbase.command.CommandCategory;
import ru.ksenomorf.botbase.command.CommandSide;

public class ShutdownCommand extends Command{
    @Override
    public String[] getAlias() {
        return new String[]{"shutdown", "off"};
    }

    @Override
    public String getDescription() {
        return "Shutdowns the bot";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.ADMIN;
    }

    @Override
    public CommandSide getSide() {
        return CommandSide.BOTH;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e) throws Throwable {
        e.getChannel().sendMessage("Shutting down...").queue(success -> Main.bot.shutdown());
    }
}

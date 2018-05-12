/*
 * Project: BotBase
 * -------------------------------------------------------
 * Copyright (c) 2018, ksenomorf.tk. All rights reserved
 */

package ru.ksenomorf.botbase.command.commands.other;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import ru.ksenomorf.botbase.command.Command;
import ru.ksenomorf.botbase.command.CommandCategory;
import ru.ksenomorf.botbase.command.CommandSide;

public class PingCommand extends Command {
    @Override
    public String[] getAlias() {
        return new String[]{"ping"};
    }

    @Override
    public String getDescription() {
        return "Returns bot latency";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.OTHER;
    }

    @Override
    public CommandSide getSide() {
        return CommandSide.BOTH;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e) throws Throwable {
        e.getChannel().sendMessage("**Pong!** Bot's ping: " + e.getJDA().getPing() + "ms.").queue();
    }
}

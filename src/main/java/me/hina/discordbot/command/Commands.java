package me.hina.discordbot.command;

import me.hina.discordbot.command.commands.*;

import java.util.LinkedList;

public class Commands {
    public static final LinkedList<Command> commandList = new LinkedList<>();

    public static void init() {
        commandList.add(new CalculateCommand());
    }
}

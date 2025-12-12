package me.hina.discordbot;

import me.hina.Application;
import me.hina.discordbot.command.Command;
import me.hina.discordbot.listeners.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.*;

public class DiscordBot {
    private final JDA jda;

    public DiscordBot() {
        this.jda = JDABuilder.createLight(Application.getInstance().discordBotToken, Collections.emptyList())
                .addEventListeners(new SlashCommandListener())
                .build();

        CommandListUpdateAction commands = jda.updateCommands();

        // Add all your commands on this action instance
//        commands.addCommands(
//                Commands.slash("ananke", "Calculates the best way to spend your ananke feathers on."),
//                Commands.slash("test", "Test")
//        );


        me.hina.discordbot.command.Commands.init();
        LinkedList<Command> commandList = me.hina.discordbot.command.Commands.commandList;
        SlashCommandData[] commandData = new SlashCommandData[commandList.size()];

        for (int i = 0; i < commandList.size(); i++) {
            Command command = commandList.get(i);
            SlashCommandData data = Commands.slash(command.getCommandName(), command.getDescription());
            command.onCommandInit(data);
            commandData[i] = data;
        }

        commands.addCommands(commandData).queue();

        // Then finally send your
    }
}

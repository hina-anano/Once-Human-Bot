package me.hina.discordbot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public abstract class Command {

    public abstract String getCommandName();

    public abstract String getDescription();

    public void onCommandInit(SlashCommandData data) {

    }

    public void onExecute(SlashCommandInteractionEvent event) throws Exception {

    }

    public boolean hasWhitelistCheck() {
        return true;
    }
}

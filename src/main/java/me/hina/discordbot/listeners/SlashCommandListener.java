package me.hina.discordbot.listeners;

import com.google.gson.*;
import me.hina.discordbot.command.Command;
import me.hina.discordbot.command.Commands;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        try {


            for (Command command : Commands.commandList) {
                if (command.getCommandName().equals(event.getName())) {
//                    if (command.hasWhitelistCheck() && !new Gson().fromJson(Files.readString(Path.of("botwhitelist.json")), JsonArray.class).contains(new JsonPrimitive(event.getUser().getId()))) {
//                        event.reply("你沒有權限使用該指令。請聯絡 hina.anano 購買月卡立即解鎖1000種功能 :yum。目前優惠一個月只需 $1萬 即可抱走我 :innocent: ").queue();
//                        break;
//                    }
                    command.onExecute(event);
                    break;
                }
            }
        } catch (Exception e) {
            event.reply("你好，當你看到這到則訊息的時候就代表機器人炸了 :D 請回報給hina.anano").queue();
            e.printStackTrace();
        }

//        switch (event.getName()) {
//            case "ananke" -> {
//                HashMap<String, Double> itemsFeather = new HashMap<>(){{
//                    // Nucleus Run
//                    put("DIVAN_ALLOY", 100_000_000 / 170_00d);
//
//                    // EXP Table
//                    put("ENCHANTMENT_LOOTING_5", 500_000 / 100_000d);
//                    put("ENCHANTMENT_POWER_7", 500_000 / 100_000d);
//                    put("ENCHANTMENT_CRITICAL_7", 500_000 / 100_000d);
//                    put("ENCHANTMENT_SHARPNESS_7", 500_000 / 100_000d);
//
//                    // M7
//                    put("NECRON_HANDLE", 500_000 / 100_000d);
//                }};
//
//                HttpClient client = HttpClient.newHttpClient();
//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create("https://api.hypixel.net/v2/skyblock/bazaar"))
//                        .header("Accept", "application/json") // Request JSON content
//                        .build();
//
//                HttpResponse<String> response = null;
//                try {
//                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                    event.reply("Error").queue();
//                    return;
//                }
//
//                if (response.statusCode() == 200) {
//
//                } else {
//                    throw new RuntimeException("Failed to fetch JSON: HTTP Status " + response.statusCode());
//                }
//            }
//        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String componentId = event.getComponentId(); // Get the custom ID of the clicked button

        if (componentId.equals("send_hello")) {
            event.reply(event.getGuild() + "").queue(); // Respond to the button click
        } else if (componentId.equals("send_goodbye")) {
            event.reply("Goodbye for now!").queue();
        }
        // You can add more conditions for different buttons
    }
}
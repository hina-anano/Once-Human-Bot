package me.hina;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import me.hina.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
    private static Application instance;

    public String discordBotToken;

    public static Application getInstance() {
        if (instance == null) {
            Gson gson = new Gson();
            try {
                instance = gson.fromJson(new String(Files.readAllBytes(Paths.get("application.json"))), Application.class);
            } catch (JsonSyntaxException e) {
                instance = new Application();
            } catch (IOException e) {
                throw new RuntimeException("Missing application.json", e);
            }
        }
        return instance;
    }
}

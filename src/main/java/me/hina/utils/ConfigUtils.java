package me.hina.utils;

import com.google.gson.JsonObject;

public class ConfigUtils {
    public static Config createConfig(String filePath) {

        return new Config();
    }

    public static class Config {
        private JsonObject jsonObject;

    }
}

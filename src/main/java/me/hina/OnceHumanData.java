package me.hina;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class OnceHumanData {

    //
    public static final HashMap<String, HashMap<String, Integer>> BUILDING_MATERIALS;

    static {
        try {
            // Init Building Materials
            JsonObject json = new Gson().fromJson(Files.readString(Path.of("building_materials.json")), JsonObject.class);

            BUILDING_MATERIALS = new HashMap<>();
            for (Map.Entry<String, JsonElement> buildingJson : json.entrySet()) {
                HashMap<String, Integer> buildingMaterial = new HashMap<>();

                for (Map.Entry<String, JsonElement> buildingMaterialJson : buildingJson.getValue().getAsJsonObject().entrySet()) {
                    buildingMaterial.put(buildingMaterialJson.getKey(), buildingMaterialJson.getValue().getAsInt());
                }

                BUILDING_MATERIALS.put(buildingJson.getKey(), buildingMaterial);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

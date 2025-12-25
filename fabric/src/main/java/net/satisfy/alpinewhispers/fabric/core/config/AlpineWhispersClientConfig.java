package net.satisfy.alpinewhispers.fabric.core.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public final class AlpineWhispersClientConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "alpinewhispers.client.json";

    public static boolean snowFogEnabled = true;
    public static double snowFogStrength = 1.0D;

    public static void load() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);

        if (Files.notExists(configPath)) {
            save();
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(configPath)) {
            Data data = GSON.fromJson(reader, Data.class);
            if (data != null) {
                snowFogEnabled = data.snowFogEnabled;
                snowFogStrength = clamp(data.snowFogStrength, 0.0D, 2.0D);
            }
        } catch (Exception ignored) {
        }
    }

    public static void save() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);

        try {
            Files.createDirectories(configPath.getParent());
        } catch (Exception ignored) {
        }

        Data data = new Data();
        data.snowFogEnabled = snowFogEnabled;
        data.snowFogStrength = clamp(snowFogStrength, 0.0D, 2.0D);

        try (BufferedWriter writer = Files.newBufferedWriter(configPath)) {
            GSON.toJson(data, writer);
        } catch (Exception ignored) {
        }
    }

    private static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    private static final class Data {
        boolean snowFogEnabled = true;
        double snowFogStrength = 1.0D;
    }

    private AlpineWhispersClientConfig() {
    }
}
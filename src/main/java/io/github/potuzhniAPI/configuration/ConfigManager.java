package io.github.potuzhniAPI.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private final JavaPlugin plugin;

    private FileConfiguration config;
    private File              configFile;

    public ConfigManager(JavaPlugin plugin) { this.plugin = plugin; }

    /**
     * Setup configuration file.
     * <p>Supported formats:
     * <ul>
     *   <li>{@code "custom"} (auto-adds .yml)</li>
     *   <li>{@code "custom.yml"}</li>
     * </ul>
     *
     * @param name The name of the file
     */
    public void setupConfig(String name) {
        String fileName = name.endsWith(".yml") ? name : name + ".yml";

        configFile = new File(plugin.getDataFolder(), fileName);

        if (!configFile.exists()) {
            plugin.saveResource(fileName, false);
        }

        reloadConfig();

        InputStream defaultStream = plugin.getResource(fileName);
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(
                    new InputStreamReader(defaultStream, StandardCharsets.UTF_8));

            config.setDefaults(defaultConfig);

            saveConfig();
        }
    }

    /**
     * Reload configuration file.
     */
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        plugin.getComponentLogger().info("<green>Config {} reloaded!", configFile.getName());
    }

    /**
     * Save config file.
     */
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getComponentLogger().error("<red>The configuration file could not be saved: {}", configFile.getName());
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() { return config; }

}

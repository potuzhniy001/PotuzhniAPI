package io.github.potuzhniAPI.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private final JavaPlugin plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Setup configuration file. You can use setupConfig("custom") without .yml end,
     * also can use setupConfig("custom.yml).
     */
    public void setupConfig(String name) {
        String fileName = name.endsWith(".yml") ? name : name + ".yml";

        configFile = new File(plugin.getDataFolder(), fileName);

        if (!configFile.exists()) {
            plugin.saveResource(fileName, false);
        }

        reloadConfig();
    }

    /**
     * Reload configuration file.
     */
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Save config file.
     */
    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getComponentLogger().error("<red>The configuration file could not be saved: {}", configFile.getName());
            plugin.getComponentLogger().error(e.getMessage());
        }
    }

    public FileConfiguration getConfig() { return config; }

}

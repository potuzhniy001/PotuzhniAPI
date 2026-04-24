package io.github.potuzhniAPI;

import io.github.potuzhniAPI.configuration.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ConfigManager configManager = new ConfigManager(this);
        configManager.setupConfig("messages");
    }

    @Override
    public void onDisable() {
        //
    }

}

package io.github.potuzhniAPI;

import io.github.potuzhniAPI.configuration.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    private ConfigManager messagesConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        messagesConfig = new ConfigManager(this);
        messagesConfig.setupConfig("messages");
        messagesConfig.reloadConfig();
    }

    @Override
    public void onDisable() {
        //
    }

    public ConfigManager getMessagesConfig() { return messagesConfig; }
}

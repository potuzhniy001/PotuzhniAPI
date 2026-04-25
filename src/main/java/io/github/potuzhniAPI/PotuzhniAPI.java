package io.github.potuzhniAPI;

import io.github.potuzhniAPI.commands.KillCommand;
import io.github.potuzhniAPI.configuration.ConfigManager;
import io.github.potuzhniAPI.listeners.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    private ConfigManager messagesConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        messagesConfig = new ConfigManager(this);
        messagesConfig.setupConfig("messages");
        messagesConfig.reloadConfig();

        getCommand("killcmd").setExecutor(new KillCommand());
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    //onDisable

    public ConfigManager getMessagesConfig() { return messagesConfig; }
}

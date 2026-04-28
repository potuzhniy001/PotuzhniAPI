package io.github.potuzhniAPI;

import io.github.potuzhniAPI.commands.KillCommand;
import io.github.potuzhniAPI.configuration.ConfigManager;
import io.github.potuzhniAPI.listeners.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    private static PotuzhniAPI instance;

    private ConfigManager messagesConfig;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        messagesConfig = new ConfigManager(this);
        messagesConfig.setupConfig("messages");

        getCommand("killcmd").setExecutor(new KillCommand());
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    public static PotuzhniAPI getPlugin() { return instance; }
    public ConfigManager getMessagesConfig() { return messagesConfig; }
}

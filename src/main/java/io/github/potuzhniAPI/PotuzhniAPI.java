package io.github.potuzhniAPI;

import io.github.potuzhniAPI.test.commands.KillCommand;
import io.github.potuzhniAPI.configuration.ConfigManager;
import io.github.potuzhniAPI.test.listeners.InventoryListener;
import io.github.potuzhniAPI.utils.PotuzhniUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    private static PotuzhniAPI        instance;
    private ConfigManager             messagesConfig;
    private static PotuzhniUtils      utils;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        utils = new PotuzhniUtils();

        messagesConfig = new ConfigManager(this);
        messagesConfig.setupConfig("messages");

        getCommand("killcmd").setExecutor(new KillCommand());
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    public static PotuzhniAPI getPlugin() { return instance; }
    public ConfigManager getMessagesConfig() { return messagesConfig; }
    public static PotuzhniUtils getUtils() { return utils; }
}

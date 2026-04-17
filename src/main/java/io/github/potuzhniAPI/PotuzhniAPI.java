package io.github.potuzhniAPI;

import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("API Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("API Disabled!");
    }
}

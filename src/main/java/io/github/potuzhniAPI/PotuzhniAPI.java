package io.github.potuzhniAPI;

import io.github.potuzhniAPI.menu.MenuLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    private MenuLoader menuLoader;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        menuLoader = new MenuLoader(this);
    }

    @Override
    public void onDisable() {
        //
    }

    public MenuLoader getMenuLoader() { return menuLoader; }
}

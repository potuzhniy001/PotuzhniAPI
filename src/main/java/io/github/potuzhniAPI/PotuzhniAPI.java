package io.github.potuzhniAPI;

import io.github.potuzhniAPI.commands.TestCommand;
import io.github.potuzhniAPI.menu.MenuLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    private MenuLoader menuLoader;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        menuLoader = new MenuLoader(this);

        getCommand("testcommand").setExecutor(new TestCommand(this));
    }

    @Override
    public void onDisable() {
        //
    }

    public MenuLoader getMenuLoader() {
        return menuLoader;
    }
}

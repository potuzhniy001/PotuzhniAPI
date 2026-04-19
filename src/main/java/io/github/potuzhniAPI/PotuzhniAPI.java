package io.github.potuzhniAPI;

import io.github.potuzhniAPI.commands.MenuCommand;
import io.github.potuzhniAPI.listeners.MenuListener;
import io.github.potuzhniAPI.menu.MenuConfig;
import io.github.potuzhniAPI.menu.MenuLoader;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class PotuzhniAPI extends JavaPlugin {

    private MenuLoader menuLoader;
    private MenuConfig menuConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        menuLoader = new MenuLoader(this);
        menuConfig = new MenuConfig();

        ConfigurationSection section = getConfig().getConfigurationSection("paginated-menu");

        if (section != null) {
            this.menuConfig = MenuConfig.load(section);
        } else {
            getLogger().severe("Секцію 'paginated-menu' не знайдено в config.yml! Використовую порожні налаштування.");
            this.menuConfig = new MenuConfig();
            this.menuConfig.title = "Стандартний заголовок";
        }

        getCommand("cmd").setExecutor(new MenuCommand(this, menuConfig));
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        //
    }

    public MenuLoader getMenuLoader() { return menuLoader; }
    public MenuConfig getMenuConfig() { return menuConfig; }
}

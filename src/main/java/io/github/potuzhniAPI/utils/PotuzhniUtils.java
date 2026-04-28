package io.github.potuzhniAPI.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import static io.github.potuzhniAPI.PotuzhniAPI.getPlugin;

public class PotuzhniUtils {

    public Component formattedTitle;
    public int rows;

    /**
     * Load menu from config
     */
    public void loadMenu(FileConfiguration config) {
        ConfigurationSection menusSection = config.getConfigurationSection("menus");

        for (String menus : menusSection.getKeys(false)) {
            ConfigurationSection menusSettingsSection = menusSection.getConfigurationSection(menus);

            String title = menusSettingsSection.getString("title", "Default title");
            formattedTitle = parse(title);

            rows = menusSettingsSection.getInt("rows", 3);
            rows *= 9;
        }

        Inventory inventory = Bukkit.createInventory(null, rows, formattedTitle);
    }


    /**
     * Parsing String to Component (MiniMessage)
     */
    public static @NotNull Component parse(String message) {
        return MiniMessage.miniMessage().deserialize("<!i><white>" + message);
    }


    /**
     * Send parsed message to player
     */
    public static void sendMessage(Player player, String message) { player.sendMessage(parse(message)); }


    /**
     * Send parsed message to console sender
     */
    public static void sendMessage(CommandSender sender, String message) { sender.sendMessage(parse(message)); }


    public Component getFormattedTitle() { return formattedTitle; }
    public void setFormattedTitle(Component formattedTitle) { this.formattedTitle = formattedTitle; }

    public int getRows() { return rows; }
    public void setRows(int rows) { this.rows = rows; }
}

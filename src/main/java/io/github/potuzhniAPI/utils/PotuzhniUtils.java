package io.github.potuzhniAPI.utils;

import io.github.potuzhniAPI.menu.AbstractSimpleMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Consumer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PotuzhniUtils {


    /**
     * Load items from config
     * @param itemsSection Name of inventory
     */
    public void loadItems(AbstractSimpleMenu menu, ConfigurationSection itemsSection) {
        if (itemsSection == null) return;

        for (String key : itemsSection.getKeys(false)) {
            ConfigurationSection settings = itemsSection.getConfigurationSection(key);
            if (settings == null) return;

            Material material = Material.matchMaterial(settings.getString("material", "STONE").toUpperCase());
            if (material == null) { material = Material.STONE; }

            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                String displayName = settings.getString("display-name");
                meta.displayName(parse(displayName));

                if (settings.isList("lore")) {
                    List<String> lore = settings.getStringList("lore");
                    List<Component> formattedLore = new ArrayList<>();

                    for (String line : lore) {
                        formattedLore.add(parse(line));
                    }

                    meta.lore(formattedLore);

                } else if (settings.isString("lore")) {
                    String lore = settings.getString("lore", "Default Lore");
                    Component formattedLore = parse(lore);
                    meta.lore(Collections.singletonList(formattedLore));
                }
            }

            String actionStr = settings.getString("action", "");

            Consumer<Player> action = p -> {
                String processedAction = actionStr.replace("%player%", p.getName());
                if (processedAction.toLowerCase().startsWith("[console] ")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), processedAction.substring(10));
                } else if (processedAction.toLowerCase().startsWith("[player] ")) {
                    p.performCommand(processedAction.substring(9));
                }
            };

            int slot = settings.getInt("slot");
            menu.setItem(slot, item, action);
        }
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


}

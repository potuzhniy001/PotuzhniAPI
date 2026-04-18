package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static io.github.potuzhniAPI.utils.PotuzhniUtils.parse;
import static io.github.potuzhniAPI.utils.PotuzhniUtils.sendMessage;

public class MenuLoader {

    private final PotuzhniAPI plugin;

    public MenuLoader(PotuzhniAPI plugin) {
        this.plugin = plugin;
    }

    public ItemStack parseItem(ConfigurationSection section) {
        String type = section.getString("material", "STONE");
        ItemStack item = new ItemStack(Material.valueOf(type.toUpperCase()));
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (section.contains("display-name")) {
                meta.displayName(parse(section.getString("display-name")));
            }

            if (section.contains("lore")) {
                List<Component> lore = section.getStringList("lore").stream()
                        .map(line -> parse(line))
                        .collect(Collectors.toList());
                meta.lore(lore);
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    public Consumer<Player> parseAction(ConfigurationSection section) {
        if (section.contains("actions")) {
            List<String> actionList = section.getStringList("actions");
            return player -> actionList.forEach(action -> executeSingleAction(action, player));
        }

        if (section.contains("action")) {
            String action = section.getString("action");
            if (action == null) return null;
            return player -> executeSingleAction(action, player);
        }

        return null;
    }

    private void executeSingleAction(String action, Player player) {
        if (action.startsWith("[player] ")) {
            player.performCommand(action.substring("[player] ".length()));

        } else if (action.startsWith("[console] ")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action.substring("[console] ".length()));

        } else if (action.startsWith("[sound] ")) {
            String soundName = action.substring("[sound] ".length());
            try {
                Sound sound = Sound.valueOf(soundName.toUpperCase());
                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            } catch (IllegalArgumentException e) {
                sendMessage(player, "<red>ERROR: Unknown sound. Check console for more information.");
                plugin.getLogger().severe("Unknown sound: " + soundName);
            }

        } else if (action.startsWith("[close-menu]")) {
            player.closeInventory();

        } else {
            player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1.0f, 1.0f);
            sendMessage(player, "<red>ERROR: Unknown action. Check console for more information.");
            plugin.getLogger().severe("Unknown action: " + action);
        }
    }

    public void setItems(Menu menu, int slot, ConfigurationSection section) {
        ItemStack item = parseItem(section);
        Consumer<Player> action = parseAction(section);
        menu.setItem(slot, item, action);
    }
}

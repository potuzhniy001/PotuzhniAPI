package io.github.potuzhniAPI.menu;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MenuConfig {
    public String title;
    public int rows;
    public List<Integer> contentSlots;
    public ItemStack backButton;
    public int backSlot;
    public ItemStack nextButton;
    public int nextSlot;

    public static MenuConfig load(ConfigurationSection section) {
        MenuConfig config = new MenuConfig();
        config.title = section.getString("title", "Меню");
        config.rows = section.getInt("rows", 6);
        config.contentSlots = section.getIntegerList("content-slots");

        config.backSlot = section.getInt("navigation.back.slot");
        config.backButton = new ItemStack(Material.valueOf(section.getString("navigation.back.material")));

        config.nextSlot = section.getInt("navigation.next.slot");
        config.nextButton = new ItemStack(Material.valueOf(section.getString("navigation.next.material")));

        return config;
    }
}

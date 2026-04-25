package io.github.potuzhniAPI.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExampleMenu extends SimpleMenu {

    private final Player player;

    public ExampleMenu(Player player) {
        super(Rows.FIVE, Component.text("Example menu btwww", NamedTextColor.GOLD));
        this.player = player;
    }

    @Override
    public void onSetItems() {
        for (int i = 0; i < 27; i++) {
            setItem(i, PLACEHOLDER_ITEM);
        }

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta meta = diamond.getItemMeta();
        meta.displayName(Component.text("Click on me!", NamedTextColor.AQUA));
        diamond.setItemMeta(meta);

        setItem(13, diamond, p -> {
            p.sendMessage(Component.text("You clicked on diamond!", NamedTextColor.GREEN));
            p.closeInventory();
        });
    }
}

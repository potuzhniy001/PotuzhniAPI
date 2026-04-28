package io.github.potuzhniAPI.test;

import io.github.potuzhniAPI.menu.AbstractSimpleMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExampleMenu extends AbstractSimpleMenu {

    public ExampleMenu() {
        super("paginated-menu");
    }

    @Override
    public void onSetItems() {
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

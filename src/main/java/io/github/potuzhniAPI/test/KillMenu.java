package io.github.potuzhniAPI.test;

import io.github.potuzhniAPI.menu.SimpleMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KillMenu extends SimpleMenu {

    public KillMenu() {
        super(Rows.THREE, Component.text("Example menu btw"));
    }

    @Override
    public void onSetItems() {
        final ItemStack killItem = new ItemStack(Material.BARRIER);
        final ItemMeta meta = killItem.getItemMeta();
        meta.displayName(Component.text("Kill yourself", NamedTextColor.RED));
        killItem.setItemMeta(meta);

        setItem(13, killItem, player -> {
            player.sendRichMessage("<red>You killed yourself");
            player.setHealth(0);
        });
    }
}

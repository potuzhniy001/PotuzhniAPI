package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static io.github.potuzhniAPI.utils.PotuzhniUtils.parse;

public class PlayerListMenu extends PaginatedMenu<Player> {

    public PlayerListMenu(PotuzhniAPI plugin, Player viewer, List<Player> players, MenuConfig config) {
        super(plugin, viewer, players, config);
    }

    @Override
    protected ItemStack parseToItemStack(Player target) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(parse("Player: " + target.getName()));
        item.setItemMeta(meta);
        return item;
    }

}

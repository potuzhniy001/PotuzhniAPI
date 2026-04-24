package io.github.potuzhniAPI.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.function.Consumer;

public interface Menu extends InventoryHolder {
    void click(Player player, int slot);

    void setItem(int slot, ItemStack item, Consumer<Player> action);

    void update();

    Map<Integer, ItemStack> getItemsMap();
    Map<Integer, Consumer<Player>> getActionsMap();

    default void open(Player player) {
        if (!player.isOnline()) return;

        player.openInventory(getInventory());
    }
}

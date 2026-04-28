package io.github.potuzhniAPI.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

import java.util.Map;

public interface Menu extends InventoryHolder {
    void click(Player player, int slot);

    void setItem(int slot, ItemStack item, Consumer<Player> action);

    void onSetItems();

    void update();

    default void open(Player player) {
        onSetItems();
        player.openInventory(getInventory());
    }

    Map<Integer, ItemStack> getItemsMap();
    Map<Integer, Consumer<Player>> getActionsMap();
}

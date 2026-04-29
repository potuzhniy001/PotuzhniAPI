package io.github.potuzhniAPI.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

import java.util.Map;

public interface Menu extends InventoryHolder {


    /**
     * Accepting actions on click.
     */
    void click(Player player, int slot);


    /**
     * Set item in slot with item and action.
     */
    void setItem(int slot, ItemStack item, Consumer<Player> action);


    /**
     * Update menu.
     */
    void update();


    /**
     * Open inventory for target (player).
     */
    default void open(Player player) {
        player.openInventory(getInventory());
    }


    Map<Integer, ItemStack> getItemsMap();
    Map<Integer, Consumer<Player>> getActionsMap();


}

package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Menu implements InventoryHolder {

    private final PotuzhniAPI plugin;

    protected final Map<Integer, ItemStack> items = new HashMap<>();
    protected final Map<Integer, Consumer<Player>> actions = new HashMap<>();

    protected Menu(PotuzhniAPI plugin) { this.plugin = plugin; }


    /*
    Handle click
     */
    public void click(Player player, int slot) {
        Consumer<Player> action = actions.get(slot);
        if (action != null) {
            action.accept(player);
        }
    }


    /*
    Set items in HashMap
     */
    public void setItem(int slot, ItemStack item) {
        items.put(slot, item);
        actions.remove(slot);
    }


    /*
    Set items in HashMap with actions
     */
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        items.put(slot, item);
        if (action != null) {
            actions.put(slot, action);
        } else actions.remove(slot);
    }


    /*
    Abstract | On set items
     */
    public abstract void onSetItems();


    /*
    Handle open menu
     */
    public void open(Player player) {
        if (!player.isOnline()) return;
        onSetItems();
        update();
        player.openInventory(getInventory());
    }


    /*
    Update menu
     */
    public void update() {}


    /// Getters
    public Map<Integer, ItemStack> getItemsMap() { return Collections.unmodifiableMap(items); }
    public Map<Integer, Consumer<Player>> getActionsMap() { return Collections.unmodifiableMap(actions); }

}

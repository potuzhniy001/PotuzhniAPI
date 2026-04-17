package io.github.potuzhniAPI.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Menu implements InventoryHolder {

    protected final Map<Integer, ItemStack> items = new HashMap<>();
    protected final Map<Integer, Consumer<Player>> actions = new HashMap<>();

    public void click(Player player, int slot) {
        Consumer<Player> action = actions.get(slot);
        if (action != null) {
            action.accept(player);
        }
    }

    public void setItem(int slot, ItemStack item) {
        items.put(slot, item);
        actions.remove(slot);
    }

    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        items.put(slot, item);
        if (action != null) {
            actions.put(slot, action);
        } else actions.remove(slot);
    }

    public abstract void onSetItems();
    public void open(Player player) {
        if (!player.isOnline()) return;
        setPlaceholders(player);
        onSetItems();
        player.openInventory(getInventory());
    }

    public void setPlaceholders(Player player) {}
    public void update() {}

    public Map<Integer, ItemStack> getItemsMap() { return Collections.unmodifiableMap(items); }
    public Map<Integer, Consumer<Player>> getActionsMap() { return Collections.unmodifiableMap(actions); }



}

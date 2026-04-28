package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSimpleMenu implements Menu {

    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();
    private final Map<Integer, ItemStack>       items = new HashMap<>();

    private final Inventory inventory;

    public AbstractSimpleMenu(Rows rows, Component title) {
        this.inventory = Bukkit.createInventory(this, rows.getSize(), title);
    }

    /**
     * Accept action from {@code Consumer<Player>}
     */
    @Override
    public void click(Player player, int slot) {
        Consumer<Player> action = this.actions.get(slot);
        if (action != null) { action.accept(player); }
    }

    /**
     * Set items into inventory
     * @param slot The slot where you need to place the item
     * @param item The type of item needed
     * @param action Action to be performed
     */
    @Override
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {
        this.actions.put(slot, action);
        this.items.put(slot, item);
        getInventory().setItem(slot, item);
    }

    @Override
    public void update() {
        getInventory().clear();

        for (int i = 0; i < getInventory().getSize(); i++) {
            ItemStack item = getItemsMap().get(i);
            if (item != null) getInventory().setItem(i, item);
        }
    }

    public abstract void onSetItems();

    @Override
    public @NotNull Inventory getInventory() { return inventory; }

    @Override
    public Map<Integer, ItemStack> getItemsMap() { return items; }

    @Override
    public Map<Integer, Consumer<Player>> getActionsMap() { return actions; }

    public enum Rows {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);

        private final int size;

        Rows(int rows) { this.size = rows * 9; }

        public int getSize() { return size; }
    }
}
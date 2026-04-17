package io.github.potuzhniAPI.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Menu implements InventoryHolder {

    final Map<Integer, ItemStack> items = new HashMap<>();
    protected final Map<Integer, Consumer<Player>> actions = new HashMap<>();

    public abstract void click(Player player, int slot);

    public abstract void setItem(int slot, ItemStack item);

    public abstract void setItem(int slot, ItemStack item, Consumer<Player> action);

    public abstract void onSetItems();

    public abstract void setPlaceholders(Player player);

    public abstract void update();

    public abstract Map<Integer, ItemStack> getItemsMap();
    public abstract  Map<Integer, Consumer<Player>> getActionsMap();

    public abstract void open(Player player);

}

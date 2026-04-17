package io.github.potuzhniAPI.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public class ConfigMenu extends Menu {

    @Override
    public void click(Player player, int slot) {

    }

    @Override
    public void setItem(int slot, ItemStack item) {

    }

    @Override
    public void setItem(int slot, ItemStack item, Consumer<Player> action) {

    }

    @Override
    public void onSetItems() {

    }

    @Override
    public void setPlaceholders(Player player) {

    }

    @Override
    public void update() {

    }

    @Override
    public Map<Integer, ItemStack> getItemsMap() {
        return Map.of();
    }

    @Override
    public Map<Integer, Consumer<Player>> getActionsMap() {
        return Map.of();
    }

    @Override
    public void open(Player player) {

    }

    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }
}

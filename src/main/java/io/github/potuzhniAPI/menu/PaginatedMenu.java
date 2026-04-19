package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class PaginatedMenu<T> extends Menu {

    protected Player player;
    protected List<T> allItems;
    protected int currentPage = 0;
    public MenuConfig config;
    private Inventory inventory;

    public PaginatedMenu(PotuzhniAPI plugin, Player player, List<T> allItems, MenuConfig config) {
        super(plugin);
        this.player = player;
        this.allItems = allItems;
        this.config = config;
    }

    @Override
    public void open(Player player) {
        String formattedTitle = config.title.replace("<page>", String.valueOf(currentPage + 1));
        this.inventory = Bukkit.createInventory(this, config.rows * 9, formattedTitle);
        update();
        player.openInventory(inventory);
    }

    @Override
    public void update() {
       inventory.clear();

       int itemsPerPage = config.contentSlots.size();
       int start = currentPage * itemsPerPage;
       int end = Math.min(start + itemsPerPage, allItems.size());

        for (int i = start; i < end; i++) {
            int slot = config.contentSlots.get(i - start);
            inventory.setItem(slot, parseToItemStack(allItems.get(i)));
        }

        if (currentPage > 0) {
            inventory.setItem(config.backSlot, config.backButton);
        }

        if (end < allItems.size()) {
            inventory.setItem(config.nextSlot, config.nextButton);
        }
    }

    protected abstract ItemStack parseToItemStack(T item);

    public void nextPage() {
        if ((currentPage + 1) * config.contentSlots.size() < allItems.size()) {
            currentPage++;
            open(player);
        }
    }

    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            open(player);
        }
    }

    @Override
    public @NotNull Inventory getInventory() { return inventory; }
}

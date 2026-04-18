package io.github.potuzhniAPI.menu;

import io.github.potuzhniAPI.PotuzhniAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static io.github.potuzhniAPI.utils.PotuzhniUtils.parse;

public abstract class PaginatedMenu<T> extends Menu {

    private int currentPage = 0;
    private int maxPage = 0;

    protected PaginatedMenu(PotuzhniAPI plugin) { super(plugin); }


    protected void setNavigation() {
        setItem(getInventory().getSize() - 1, getItemNextPage(), player -> {
            currentPage = Math.min(maxPage, currentPage + 1);
            update();
        });
        setItem(getInventory().getSize() - 9, getItemPreviousPage(), player -> {
            currentPage = Math.max(0, currentPage - 1);
            update();
        });
    }


    public void addAll(Map<ItemStack, Consumer<Player>> entries) {
        final int safeArea = getInventory().getSize() - 9;

        int index = 0;
        for (Map.Entry<ItemStack, Consumer<Player>> entry : entries.entrySet()) {
            int page = index / safeArea;
            int slot = index % safeArea;

            ItemStack item = entry.getKey();
            Consumer<Player> action = entry.getValue();

            setItem(page, slot, item, action);

            index++;
        }
    }


    @Override
    public void update() {
        getInventory().clear();

        for (int i = 0; i < getInventory().getSize(); i++) {
            final int index = currentPage * getInventory().getSize() + i;
            final ItemStack item = this.getItemsMap().get(index);

            if (item != null)
                getInventory().setItem(i, item);
        }

        setNavigation();
    }

    public void setItem(int page, int slot, ItemStack item) {
        setItem(page, slot, item, player -> {});
    }

    public void setItem(int page, int slot, ItemStack item, Consumer<Player> action) {
        final int index = page * getInventory().getSize() + slot;
        actions.put(index, action);
        items.put(index, item);

        if (page == 0)
            getInventory().setItem(index, item);

        if (page > maxPage)
            maxPage = page;
    }

    public ItemStack getItemPreviousPage() {
        final ItemStack item = new ItemStack(Material.STONE_BUTTON);
        final ItemMeta meta = item.getItemMeta();
        meta.displayName(parse("Previous page"));
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getItemNextPage() {
        final ItemStack item = new ItemStack(Material.STONE_BUTTON);
        final ItemMeta meta = item.getItemMeta();
        meta.displayName(parse("Next page"));
        item.setItemMeta(meta);

        return item;
    }
}

package io.github.potuzhniAPI.listeners;

import io.github.potuzhniAPI.menu.PaginatedMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof PaginatedMenu<?> menu)) return;

        event.setCancelled(true);
        int slot = event.getRawSlot();

        if (slot == menu.config.nextSlot) {
            menu.nextPage();
        } else if (slot == menu.config.backSlot) {
            menu.previousPage();
        }
    }

}

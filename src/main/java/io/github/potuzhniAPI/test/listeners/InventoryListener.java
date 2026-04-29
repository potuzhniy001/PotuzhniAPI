package io.github.potuzhniAPI.test.listeners;

import io.github.potuzhniAPI.menu.AbstractSimpleMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof AbstractSimpleMenu menu)) return;

        event.setCancelled(true);

        if (event.getClickedInventory() != event.getView().getTopInventory()) return;

        if (event.getCurrentItem() == null) return;
        if (!(event.getWhoClicked() instanceof Player player)) return;

        menu.click(player, event.getSlot());
    }

}

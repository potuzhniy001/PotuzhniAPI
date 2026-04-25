package io.github.potuzhniAPI.listeners;

import io.github.potuzhniAPI.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!(event.getInventory().getHolder() instanceof Menu menu)) return;

        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;

        menu.click(player, event.getSlot());
    }

}

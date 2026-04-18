package io.github.potuzhniAPI.listeners;

import io.github.potuzhniAPI.menu.TestMenu;
import io.github.potuzhniAPI.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TestListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof Menu menu)) return;

        if (menu instanceof TestMenu) {
            event.setCancelled(true);
            menu.click((Player) event.getWhoClicked(), event.getSlot());
        }
    }

}

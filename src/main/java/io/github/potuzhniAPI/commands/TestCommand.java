package io.github.potuzhniAPI.commands;

import io.github.potuzhniAPI.PotuzhniAPI;
import io.github.potuzhniAPI.menu.PaginatedMenu;
import io.github.potuzhniAPI.menu.TestMenu;
import io.github.potuzhniAPI.menu.TestPaginatedMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {

    private final PotuzhniAPI plugin;

    public TestCommand(PotuzhniAPI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        ConfigurationSection config = plugin.getConfig().getConfigurationSection("paginated-menu");
        TestPaginatedMenu paginatedMenu = new TestPaginatedMenu(plugin, config);
        paginatedMenu.open(player);

        return true;
    }
}

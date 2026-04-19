package io.github.potuzhniAPI.commands;

import io.github.potuzhniAPI.PotuzhniAPI;
import io.github.potuzhniAPI.menu.MenuConfig;
import io.github.potuzhniAPI.menu.PlayerListMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MenuCommand implements CommandExecutor {

    private final PotuzhniAPI plugin;
    private final MenuConfig menuConfig;

    public MenuCommand(PotuzhniAPI plugin, MenuConfig menuConfig) {
        this.plugin = plugin;
        this.menuConfig = menuConfig;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("only for players!");
            return true;
        }

        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        PlayerListMenu menu = new PlayerListMenu(plugin, player, onlinePlayers, menuConfig);

        menu.open(player);

        return true;
    }

}

package io.github.potuzhniAPI.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PotuzhniUtils {


    /**
     * Parsing String to Component (MiniMessage)
     */
    public static @NotNull Component parse(String message) {
        return MiniMessage.miniMessage().deserialize("<!i><white>" + message);
    }


    /**
     * Send parsed message to player
     */
    public static void sendMessage(Player player, String message) { player.sendMessage(parse(message)); }


    /**
     * Send parsed message to console sender
     */
    public static void sendMessage(CommandSender sender, String message) { sender.sendMessage(parse(message)); }

}

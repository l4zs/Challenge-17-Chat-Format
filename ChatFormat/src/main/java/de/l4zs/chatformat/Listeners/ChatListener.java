package de.l4zs.chatformat.Listeners;

import de.l4zs.chatformat.Utils.Settings;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        String msg = event.getMessage();

        String format = "%player_name%: ";
        int priority = -1;
        /* Chat - Format */
        for (int i = 0; i < Settings.format.size(); i++) {
            if (player.hasPermission(Settings.format_permission.get(i))) {
                if (Settings.format_priority.get(i) > priority) {
                    format = ChatColor.translateAlternateColorCodes('&', Settings.format.get(i));
                    priority = Settings.format_priority.get(i);
                }
            }
        }
        format = PlaceholderAPI.setPlaceholders(player, format);

        /* Chat Filter */
        if (Settings.filter_enabled) {
            if (Settings.filter_replace_enabled) {
                for (int i = 0; i < Settings.bad_words.size(); i++) {
                    if (event.getMessage().toLowerCase().contains(Settings.bad_words.get(i).toLowerCase())) {
                        StringBuilder replace = new StringBuilder();
                        for (int j = 0; j < Settings.bad_words.get(i).length(); j++) {
                            replace.append("*");
                        }
                        msg = msg.replace(Settings.bad_words.get(i), replace.toString());
                        player.sendMessage("§4§lWARNING §r§4you used a bad word! it was replaced with§f" + replace.toString());
                    }
                }
            } else {
                for (int i = 0; i < Settings.bad_words.size(); i++) {
                    if (event.getMessage().contains(Settings.bad_words.get(i))) {
                        event.setCancelled(true);
                        player.sendMessage("§4§lWARNING §r§4you used a bad word! it wasn't sent.");
                    }
                }
            }
        }

        if (player.hasPermission("chat.color")) {
            event.setMessage(ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            event.setMessage(msg);
        }
        event.setFormat(format + event.getMessage());
    }
}

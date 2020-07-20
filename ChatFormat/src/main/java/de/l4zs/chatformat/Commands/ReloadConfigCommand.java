package de.l4zs.chatformat.Commands;

import de.l4zs.chatformat.Utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Config.loadConfiguration();
            sender.sendMessage("§7Reloading the §4§lConfig");
            return true;
        }
        return false;
    }
}
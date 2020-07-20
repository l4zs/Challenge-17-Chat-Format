package de.l4zs.chatformat.Commands;

import de.l4zs.chatformat.Utils.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddSetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 0) {
                if (label.equalsIgnoreCase("add")) {
                    if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("bad") && args[1].equalsIgnoreCase("word")) {
                            Settings.bad_words.add(args[2]);
                            player.sendMessage("§aAdded §f" + args[2] + " §a to bad words");
                        }
                    } else if (args.length > 5) {
                        if (args[0].equalsIgnoreCase("format")) {
                            StringBuilder format = new StringBuilder(ChatColor.translateAlternateColorCodes('&', args[4]));
                            for (int i = 5; i < args.length; i++) {
                                format.append(" ").append(ChatColor.translateAlternateColorCodes('&', args[i]));
                            }
                            format.append(" ");
                            if (format.toString().contains("%player_name%")) {

                                Settings.format_name.add(args[1]);
                                Settings.format_priority.add(Integer.parseInt(args[2]));
                                Settings.format_permission.add(args[3]);

                                Settings.format.add(format.toString());
                                player.sendMessage("§aAdded Format with" +
                                        "\n §cName: §f" + args[1] +
                                        "\n §cPriority: §f" + args[2] +
                                        "\n §cPermission: §f" + args[3] +
                                        "\n §cFormat: §f" + format.toString());
                            }
                        }
                    }
                } else if (label.equalsIgnoreCase("set")) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("enabled")) {
                            if (args[1].equalsIgnoreCase("true")) {
                                Settings.enabled = true;
                                player.sendMessage("§7Set enabled to §atrue");
                            } else if (args[1].equalsIgnoreCase("false")) {
                                Settings.enabled = false;
                                player.sendMessage("§7Set enabled to §cfalse");
                            }
                        }
                    } else if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("chat-filter")) {
                            if (args[1].equalsIgnoreCase("enabled")) {
                                if (args[2].equalsIgnoreCase("true")) {
                                    Settings.filter_enabled = true;
                                    player.sendMessage("§7Set chat-filter enabled to §atrue");
                                } else if (args[2].equalsIgnoreCase("false")) {
                                    Settings.filter_enabled = false;
                                    player.sendMessage("§7Set chat-filter enabled to §cfalse");
                                }
                            } else if (args[1].equalsIgnoreCase("replace")) {
                                if (args[2].equalsIgnoreCase("true")) {
                                    Settings.filter_replace_enabled = true;
                                    player.sendMessage("§7Set chat-filter replace to §atrue");
                                } else if (args[2].equalsIgnoreCase("false")) {
                                    Settings.filter_replace_enabled = false;
                                    player.sendMessage("§7Set chat-filter replace to §cfalse");
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
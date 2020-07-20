package de.l4zs.chatformat.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AddSetTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (alias.equalsIgnoreCase("add")) {
            if (args.length == 1) {
                if (args[0].toLowerCase().equalsIgnoreCase("")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("bad");
                    tabComplete.add("format");
                    return tabComplete;
                } else if (args[0].toLowerCase().startsWith("b")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("bad");
                    return tabComplete;
                } else if (args[0].toLowerCase().startsWith("f")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("format");
                    return tabComplete;
                }
            } else if (args.length == 2) {
                if (args[0].toLowerCase().equalsIgnoreCase("bad")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("word");
                    return tabComplete;
                } else {
                    return null;
                }
            }
        } else if (alias.equalsIgnoreCase("set")) {
            if (args.length == 1) {
                if (args[0].toLowerCase().equalsIgnoreCase("")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("enabled");
                    tabComplete.add("chat-filter");
                    return tabComplete;
                } else if (args[0].toLowerCase().startsWith("e")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("enabled");
                    return tabComplete;
                } else if (args[0].toLowerCase().startsWith("c")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("chat-filter");
                    return tabComplete;
                }
            } else if (args.length == 2) {
                if (args[0].toLowerCase().equalsIgnoreCase("enabled")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("true");
                    tabComplete.add("false");
                    return tabComplete;
                } else if (args[0].toLowerCase().equalsIgnoreCase("chat-filter")) {
                    List<String> tabComplete = new ArrayList<>();
                    tabComplete.add("enabled");
                    tabComplete.add("replace");
                    return tabComplete;
                }
            } else if (args.length == 3) {
                if (args[0].toLowerCase().equalsIgnoreCase("chat-filter")) {
                    if (args[1].toLowerCase().equalsIgnoreCase("enableed") || args[1].toLowerCase().equalsIgnoreCase("replace")) {
                        List<String> tabComplete = new ArrayList<>();
                        tabComplete.add("true");
                        tabComplete.add("false");
                        return tabComplete;
                    }
                }
            }
        }
        return null;
    }
}

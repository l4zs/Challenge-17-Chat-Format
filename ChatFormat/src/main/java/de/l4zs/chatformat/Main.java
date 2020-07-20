package de.l4zs.chatformat;

import de.l4zs.chatformat.Commands.ReloadConfigCommand;
import de.l4zs.chatformat.Commands.AddSetCommand;
import de.l4zs.chatformat.Commands.AddSetTabComplete;
import de.l4zs.chatformat.Listeners.ChatListener;
import de.l4zs.chatformat.Utils.Config;
import de.l4zs.chatformat.Utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    public static String getPrefix() {
        return "§8┃ §aServer §8» §7";
    }

    @Override
    public void onLoad() {
        Config.loadConfiguration();

        Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "The §eChat-Plugin §7loads.");
    }

    @Override
    public void onEnable() {
        if (!Settings.enabled) {
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            try {
                Config.loadConfiguration();
                Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "The §eChat-Plugin §7is active.");

                listenerRegistration();
                commandRegistration();
            } catch (Exception e) {
                Config.configCreated = true;
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        if (Settings.enabled) {
            if (!Config.configCreated) {
                Config.saveConfiguration();
            }
            Bukkit.getConsoleSender().sendMessage(Main.getPrefix() + "The §eChat-Plugin §7is deactivated.");
        }
    }

    private void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatListener(), this);
    }

    public void commandRegistration() {
        getCommand("creload").setExecutor(new ReloadConfigCommand());
        getCommand("add").setExecutor(new AddSetCommand());
        getCommand("add").setTabCompleter(new AddSetTabComplete());
    }
}

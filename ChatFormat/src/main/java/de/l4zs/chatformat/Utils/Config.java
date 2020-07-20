package de.l4zs.chatformat.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private static File file = new File("plugins/l4zs ChatFormat/", "config.yml");
    private static YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
    public static boolean configCreated = false;

    public static boolean loadConfiguration() {
        if (!file.exists()) {
            resetConfiguration();
        }

        try {
            Settings.enabled = yml.getBoolean("enabled");
            Settings.filter_enabled = yml.getBoolean("chat-filter.enabled");
            Settings.filter_replace_enabled = yml.getBoolean("chat-filter.replace");
            Settings.bad_words = yml.getStringList("chat-filter.bad-words");

            ConfigurationSection inventorySection = getConfig().getConfigurationSection("formats");
            for (String key : inventorySection.getKeys(false)) {
                if (!Settings.format_name.contains(inventorySection.getString(key + ".name"))) {
                    Settings.format_name.add(inventorySection.getString(key + ".name"));
                }
                if (!Settings.format_priority.contains(inventorySection.getInt(key + ".priority"))) {
                    Settings.format_priority.add(inventorySection.getInt(key + ".priority"));
                }
                if (!Settings.format_permission.contains(inventorySection.getString(key + ".permission"))) {
                    Settings.format_permission.add(inventorySection.getString(key + ".permission"));
                }
                if (!Settings.format.contains(inventorySection.getString(key + ".format"))) {
                    Settings.format.add(inventorySection.getString(key + ".format"));
                }
            }

            return true;
        } catch (Exception e) {
            resetConfiguration();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean resetConfiguration() {
        try {
            new File("plugins/l4zs ChatFormat/").mkdirs();
            file.createNewFile();
            yml.set("enabled", Settings.enabled);
            yml.set("chat-filter.enabled", Settings.filter_enabled);
            yml.set("chat-filter.replace", Settings.filter_replace_enabled);
            yml.set("chat-filter.bad-words", Settings.bad_words);
            for (int i = 0; i < Settings.format.size(); i++) {
                yml.set("formats." + i + ".name", Settings.format_name.get(i));
                yml.set("formats." + i + ".priority", Settings.format_priority.get(i));
                yml.set("formats." + i + ".permission", Settings.format_permission.get(i));
                yml.set("formats." + i + ".format", Settings.format.get(i));
            }
            yml.save(file);
            configCreated = true;
            Bukkit.getConsoleSender().sendMessage("§4§lWARNING! §r§4Config created!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§4§lWARNING! §r§4Not able to create Config. => Restarting Server!");
            Bukkit.spigot().restart();
            return false;
        }
    }

    public static boolean saveConfiguration() {
        try {
            yml.set("enabled", Settings.enabled);
            yml.set("chat-filter.enabled", Settings.filter_enabled);
            yml.set("chat-filter.replace", Settings.filter_replace_enabled);
            yml.set("chat-filter.bad-words", Settings.bad_words);
            for (int i = 0; i < Settings.format.size(); i++) {
                yml.set("formats." + i + ".name", Settings.format_name.get(i));
                yml.set("formats." + i + ".priority", Settings.format_priority.get(i));
                yml.set("formats." + i + ".permission", Settings.format_permission.get(i));
                yml.set("formats." + i + ".format", Settings.format.get(i));
            }
            yml.save(file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static File getFile() {
        return file;
    }

    public static YamlConfiguration getConfig() {
        return yml;
    }

}

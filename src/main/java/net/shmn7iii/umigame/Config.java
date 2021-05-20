package net.shmn7iii.umigame;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static Umigame  plugin;
    public Config(Umigame instance){ plugin = instance; }

    public static void load() {
        FileConfiguration config = Umigame.plugin.getConfig();
    }
}

package net.shmn7iii.umigame;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Umigame extends JavaPlugin {
    public static Umigame plugin = null;

    public static String MessagePrefix = "" + ChatColor.AQUA + ChatColor.BOLD + "[UMGM] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        plugin = this;
        Command command = new Command();
        Objects.requireNonNull(getCommand("umigame")).setExecutor(command);
        Objects.requireNonNull(getCommand("umigame")).setTabCompleter(command);

        getServer().getPluginManager().registerEvents(new Event(this), this);

        ScoreBoard.registScoreBoard();

        saveDefaultConfig();
        Config.load();

        saveIfNotExist("Questions/_sample.txt");
        saveIfNotExist("Answers/_sample.txt");
        saveIfNotExist("README.txt");

        getLogger().info("Hello!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye!");
        ScoreBoard.unregisterScoreBoard();
    }

    public static void saveIfNotExist(String pathWOPLFILE){
        File file = new File("./plugins/Umigame/" + pathWOPLFILE);
        if(!(file.exists())){
            plugin.saveResource(pathWOPLFILE,false);
        }
    }

}

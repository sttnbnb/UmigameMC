package net.shmn7iii.umigame;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

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

        try{
            saveResource("Questions/sample.txt",false);
            saveResource("Answers/sample.txt",false);
        } catch (Exception ignored) { }


        getLogger().info("Hello!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye!");
        ScoreBoard.unregisterScoreBoard();
    }

}

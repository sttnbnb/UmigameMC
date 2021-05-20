package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;
import java.util.Objects;

public final class Umigame extends JavaPlugin {
    public static Umigame plugin = null;
    public static ScoreboardManager manager;
    public static Scoreboard board;
    public static Objective SCUMIGAME;

    public static String MessagePrefix = "" + ChatColor.AQUA + ChatColor.BOLD + "[UMGM] " + ChatColor.RESET;

    @Override
    public void onEnable() {
        plugin = this;
        Command command = new Command();
        Objects.requireNonNull(getCommand("umigame")).setExecutor(command);
        Objects.requireNonNull(getCommand("umigame")).setTabCompleter(command);

        getServer().getPluginManager().registerEvents(new Event(this), this);


        registScoreBoard();


        saveDefaultConfig();
        saveResource("Questions/sample.txt",false);
        saveResource("Answers/sample.txt",false);
        Config.load();

        getLogger().info("Hello!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye!");
        unregistScoreBoard();
    }

    public static void registScoreBoard(){
        manager = Bukkit.getScoreboardManager();
        board = manager.getMainScoreboard();
        SCUMIGAME = board.registerNewObjective("umigame","dummy",""+ ChatColor.GREEN + ChatColor.BOLD +"-うみがめのスープ-");
    }

    public static void unregistScoreBoard(){
        if(SCUMIGAME != null) {
            SCUMIGAME.unregister();
        }
    }
}

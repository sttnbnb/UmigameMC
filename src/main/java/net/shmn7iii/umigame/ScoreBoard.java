package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoard {
    public static ScoreboardManager manager;
    public static Scoreboard board;
    public static Objective SCUMIGAME;

    public static void registScoreBoard(){
        manager = Bukkit.getScoreboardManager();
        board = manager.getMainScoreboard();
        SCUMIGAME = board.registerNewObjective("umigame","dummy",""+ ChatColor.GREEN + ChatColor.BOLD +"-うみがめのスープ-");
    }

    public static void unregisterScoreBoard(){
        if(SCUMIGAME != null) {
            SCUMIGAME.unregister();
        }
    }

    public static void registUmigameScoreboard4eachPL(UPlayer uplayer){
        int num = uplayer.getNum();
        Score score = SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + " └ " + uplayer.getPlayer().getName());
        score.setScore(num * -10);
        score = SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   ├ " + ChatColor.YELLOW + "正答ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getGpoint());
        score.setScore(num * -10 -1);
        score = SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   └ " + ChatColor.GREEN + "質問ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getEpoint());
        score.setScore(num * -10 -2);
        score = SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num);
        score.setScore(num * -10 -3);
    }

    public static void unregistUmigameScoreboard4eachPL(UPlayer uplayer){
        int num = uplayer.getNum();
        board.resetScores("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   ├ " + ChatColor.YELLOW + "正答ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getGpoint());
        board.resetScores("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   └ " + ChatColor.GREEN + "質問ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getEpoint());
    }

    public static void registUmigameScoreboard4GM(UPlayer uplayer){
        Score score = SCUMIGAME.getScore("" + ChatColor.RED + ChatColor.BOLD + "GameMaster");
        score.setScore(0);
        score = SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + "0" + ChatColor.RESET + " └ " + uplayer.getPlayer().getName());
        score.setScore(-1);
        score = SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + "0");
        score.setScore(-2);
        score = SCUMIGAME.getScore("" + ChatColor.AQUA + ChatColor.BOLD + "Player");
        score.setScore(-3);
    }
}

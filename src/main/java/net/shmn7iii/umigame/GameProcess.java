package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class GameProcess {
    public static UPlayer GM;
    public static boolean GAME;

    public static void gameStart(Player _GM){
        if(GAME){
            _GM.sendMessage(Umigame.MessagePrefix + "ゲーム中です．一度ゲームを終了してください．");
            return;
        }

        GAME = true;
        ScoreBoard.unregisterScoreBoard();
        ScoreBoard.registScoreBoard();

        UPlayer.setUPlayer(_GM).setGM(true);

        int i = 1;
        for(Player p: Bukkit.getOnlinePlayers()){
            UPlayer uplayer = UPlayer.setUPlayer(p);
            uplayer.setEpoint(0);
            uplayer.setGpoint(0);

            if(uplayer.getPlayer().equals(_GM)){
                GM = uplayer;
                uplayer.setGM(true);
                // Scoreboard
                ScoreBoard.registUmigameScoreboard4GM(uplayer);
            }
            else{
                // Scoreboard
                uplayer.setNum(i);
                ScoreBoard.registUmigameScoreboard4eachPL(uplayer);
                i++;
            }
        }

        for(Player p: Bukkit.getOnlinePlayers()){
            p.sendTitle("" + ChatColor.GREEN + ChatColor.BOLD + "うみがめのスープ","" + ChatColor.YELLOW + ChatColor.BOLD + "GM: " + ChatColor.RESET + GM.getPlayer().getDisplayName(),20,100,20);
            p.sendMessage(Umigame.MessagePrefix + "ゲームが始まりました．");
            p.sendMessage(Umigame.MessagePrefix + "GMは問題を読み上げゲームを進行してください．");
            p.setGameMode(GameMode.ADVENTURE);
            p.setAllowFlight(true);
        }

        ScoreBoard.SCUMIGAME.setDisplaySlot(DisplaySlot.SIDEBAR);

    }

    public static void gameEnd(){
        GAME = false;
        for(Player p: Bukkit.getOnlinePlayers()){
            p.setAllowFlight(false);
        }
        ScoreBoard.SCUMIGAME.setDisplaySlot(null);
        System.displayRanking();
        UPlayer.reset();
        Bukkit.broadcastMessage("続けて次のゲームを行う場合はサーバーをリロードしてください．");
    }
}

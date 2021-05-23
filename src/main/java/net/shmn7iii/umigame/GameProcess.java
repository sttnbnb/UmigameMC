package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.List;

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

        UPlayer.getUPlayer(_GM).setGM(true);

        int i = 1;
        for(Player p: Bukkit.getOnlinePlayers()){
            UPlayer uplayer = UPlayer.getUPlayer(p);
            uplayer.setEpoint(0);
            uplayer.setGpoint(0);

            if(uplayer.getPlayer().equals(_GM)){
                GM = uplayer;
                uplayer.setGM(true);

                // Scoreboard
                ScoreBoard.registUmigameScoreboard4GM(uplayer);

                // GOLD BLOCK
                ItemStack STACKgold = new ItemStack(Material.GOLD_BLOCK);
                ItemMeta IMgold = STACKgold.getItemMeta();
                IMgold.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "プレイヤーを左クリックで正答ポイント");
                List<String> lore = new ArrayList<>();
                lore.add("" + ChatColor.LIGHT_PURPLE + "つまり殴ればポイント付与");
                IMgold.setLore(lore);
                STACKgold.setItemMeta(IMgold);

                // EMERALD BLOCK
                ItemStack STACKemerald = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta IMemerald = STACKemerald.getItemMeta();
                IMemerald.setDisplayName("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "プレイヤーを左クリックで質問ポイント");
                List<String> loree = new ArrayList<>();
                loree.add("" + ChatColor.LIGHT_PURPLE + "つまり殴ればポイント付与");
                IMemerald.setLore(loree);
                STACKemerald.setItemMeta(IMemerald);

                uplayer.getPlayer().getInventory().addItem(STACKgold);
                uplayer.getPlayer().getInventory().addItem(STACKemerald);
            }
            else{
                // Scoreboard
                uplayer.setNum(i);
                ScoreBoard.registUmigameScoreboard4eachPL(uplayer);
                i++;
            }

            QA.loadFiles();
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
        System.displayGPointRanking();
        UPlayer.reset();
        Bukkit.broadcastMessage("続けて次のゲームを行う場合はサーバーをリロードしてください．");
    }
}

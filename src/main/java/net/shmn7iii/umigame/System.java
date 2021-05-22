package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class System {
    // 単純にdisplayするだけ
    public static void displayGPointRanking(){
        ArrayList<String[]> ranking = new ArrayList<>();
        HashMap<Integer,UPlayer> map = new HashMap<>();

        for(Player p:UPlayer.map.keySet()){
            UPlayer uplayer = UPlayer.getUPlayer(p);
            if(!(uplayer.getGM())){
                map.put(uplayer.getGpoint(), uplayer);
            }
        }

        //sorting
        Object[] maparray = map.keySet().toArray();
        Arrays.sort(maparray);

        // これは小さい順にソートされたmapをくるくるする
            // からrankingには先頭に追加していくよ
                // そうすれば最終的にGPointが高い人から順になったrankingが得られるよね
        for(Integer key:map.keySet()){
            String[] list = new String[3];
            UPlayer uplayer = map.get(key);
            list[0] = String.valueOf(uplayer.getGpoint());
            list[1] = String.valueOf(uplayer.getEpoint());
            list[2] = uplayer.getPlayer().getDisplayName();
            ranking.add(0,list);
        }

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("===" + ChatColor.RED + ChatColor.BOLD + " [最終結果] " + ChatColor.RESET + "=====================================");
        Bukkit.broadcastMessage("");
        int rank = 1;
        for(String[] str:ranking){
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + rank + "位" + ChatColor.RESET
                    + " - " + ChatColor.YELLOW + "正答ポイント: " + ChatColor.GOLD + ChatColor.BOLD + str[0] + ChatColor.RESET
                    + " - " + ChatColor.GREEN + "質問ポイント: " + ChatColor.GOLD + ChatColor.BOLD + str[1] + ChatColor.RESET
                    + " - " + ChatColor.AQUA + ChatColor.BOLD + str[2]);
            Bukkit.broadcastMessage("");
            rank++;
        }
        Bukkit.broadcastMessage("================================================");
        Bukkit.broadcastMessage("");
    }
}

package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PinPon {
    public static boolean PNPN = false;
    public static HashMap<Integer, Player> PPM = new HashMap<>();

    // ん？ピンポンしたらチャット欄に順に表示させるだけでよくね？わかるやんそれで
    public static void doPinpon(Player player){
        // 1番目の回答者でこっちに来る
        if(!(PNPN)){
            PNPN = true;
            // タイマースタート
            new Timer(Umigame.plugin, 1).runTaskTimer(Umigame.plugin, 10,20);
            PPM.put(1,player);
            display(1, player);
        }
        // それ以外でこっちにくるよ
        else{
            int i = PPM.size() + 1;
            PPM.put(i,player);
            display(i, player);
        }
    }

    public static void display(int i, Player player){
        Bukkit.broadcastMessage("" + ChatColor.RED + ChatColor.BOLD + i + ChatColor.RESET + " - " + ChatColor.AQUA + ChatColor.BOLD + player.getDisplayName());
    }

}

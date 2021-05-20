package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

public class Event implements Listener {
    public static Umigame plugin;
    public Event(Umigame instance) { plugin = instance; }


    // インタラクトだと2回呼ばれてうざいからダメージにしたけどこれだとクリエになられると動かないね
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){
        Player gm;
        Entity entd = e.getDamager();
        Player clicked;
        Entity ent = e.getEntity();

        if (!(entd instanceof Player)) { return; }
        if (!(ent instanceof Player)) { return; }
        gm = (Player) entd;
        clicked = (Player) ent;

        if(!((gm.getInventory().getItemInMainHand().getType().equals(Material.GOLD_BLOCK)) || (gm.getInventory().getItemInMainHand().getType().equals(Material.EMERALD_BLOCK)))) { return; }
        if(!(GameProcess.GAME)){
            gm.sendMessage(Umigame.MessagePrefix + "ゲーム外です");
            return;
        }
        if(UPlayer.getUPlayer(clicked).getGM()){ return; }

        e.setCancelled(true);

        if(gm.getInventory().getItemInMainHand().getType().equals(Material.GOLD_BLOCK)) {
            UPlayer.getUPlayer(clicked).addGpoint(1);
            for(Player p: Bukkit.getOnlinePlayers()){
                p.sendMessage(Umigame.MessagePrefix + clicked.getDisplayName() + ": " + ChatColor.GOLD + "正答ポイント +1 !");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP,1,1);
            }
        } else if(gm.getInventory().getItemInMainHand().getType().equals(Material.EMERALD_BLOCK)){
            UPlayer.getUPlayer(clicked).addEpoint(1);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Umigame.MessagePrefix + clicked.getDisplayName() + ": " + ChatColor.GOLD + "質問ポイント +1 !");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
            }

        }
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        if(GameProcess.GAME){ e.setCancelled(true); }
    }
}

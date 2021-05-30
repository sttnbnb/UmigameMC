package net.shmn7iii.umigame;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;

import java.util.HashSet;
import java.util.Set;

public class Event implements Listener {
    public static Umigame plugin;
    public Event(Umigame instance) { plugin = instance; }


    // インタラクトだと2回呼ばれてうざいからダメージにしたけどこれだとクリエになられると動かないね
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        e.setCancelled(true);

        Player gm;
        Entity entd = e.getDamager();
        Player clicked;
        Entity ent = e.getEntity();

        if (!(entd instanceof Player)) { return; }
        if (!(ent instanceof Player)) { return; }
        gm = (Player) entd;
        clicked = (Player) ent;

        addPoint(gm, clicked);
    }

    public static void addPoint(Player gm, Player target){
        if(!((gm.getInventory().getItemInMainHand().getType().equals(Material.GOLD_BLOCK)) || (gm.getInventory().getItemInMainHand().getType().equals(Material.EMERALD_BLOCK)))) { return; }
        if(!(GameProcess.GAME)){
            gm.sendMessage(Umigame.MessagePrefix + "ゲーム外です");
            return;
        }

        if(!(UPlayer.map.containsKey(target.getDisplayName()))){ return; }

        if(UPlayer.getUPlayer(target).getGM()){ return; }

        if(gm.getInventory().getItemInMainHand().getType().equals(Material.GOLD_BLOCK)) {
            UPlayer.getUPlayer(target).addGpoint(1);
            for(Player p: Bukkit.getOnlinePlayers()){
                p.sendMessage(Umigame.MessagePrefix + target.getDisplayName() + ": " + ChatColor.GOLD + "正答ポイント +1 !");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP,1,1);
            }
        } else if(gm.getInventory().getItemInMainHand().getType().equals(Material.EMERALD_BLOCK)){
            UPlayer.getUPlayer(target).addEpoint(1);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Umigame.MessagePrefix + target.getDisplayName() + ": " + ChatColor.GOLD + "質問ポイント +1 !");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
            }
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(GameProcess.GAME){
            e.getPlayer().setAllowFlight(true);
            e.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
    }



    /* 動くけど挙動が不審なので保留
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_AIR)){
            Player p = event.getPlayer();
            int range = 50;
            Set<Entity> entities = new HashSet<>();
            for (Block b : p.getLineOfSight((HashSet<Material>) null, range)) {
                for (Entity e: b.getWorld().getNearbyEntities(b.getLocation(), 0.1, 0.1, 0.1)){
                    if(!(e.equals(event.getPlayer()))){
                        entities.add(e);
                    }
                }
            }

            Entity targetedEntity = null;
            double distance = Double.MAX_VALUE;

            for (Entity e : entities) {
                Bukkit.broadcastMessage(String.valueOf(e));
                double tmpDistance = e.getLocation().distance(p.getLocation());
                if (targetedEntity == null || tmpDistance < distance) {
                    targetedEntity = e;
                    distance = tmpDistance;
                }
            }

            if(targetedEntity instanceof Player) {
                Player targ = (Player) targetedEntity;
                addPoint(p, targ);
                Bukkit.broadcastMessage("debug2" + targ.getDisplayName());
            }
        }
    }
     */

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        e.setCancelled(true);
    }
}

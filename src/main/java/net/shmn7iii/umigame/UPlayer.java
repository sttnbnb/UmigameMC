package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

public class UPlayer {
    private static HashMap<Player, UPlayer> map = new HashMap();
    private Player player;
    //private Scoreboard board;
    private int gpoint;
    private int epoint;
    private int num;
    private boolean GM;

    public static UPlayer getUPlayer(Player playername) {
        if (!map.containsKey(playername)) {
            map.put(playername, new UPlayer(playername));
        }

        return map.get(playername);
    }

    public UPlayer(Player player) {
        this.player = player;
        this.gpoint = 0;
        this.epoint = 0;
        this.GM = false;
        this.num = 0;
        //this.board = Bukkit.getScoreboardManager().getNewScoreboard();
        //player.setScoreboard(this.board);
    }

    public Player getPlayer(){ return this.player; }

    public int getGpoint(){ return this.gpoint;}
    public void setGpoint(int _gpoint){
        if(this.num != 0){
            System.unregistUmigameScoreboard4eachPL(this);
        }
        this.gpoint = _gpoint;
        if(this.num != 0){
            System.registUmigameScoreboard4eachPL(this);
        }
    }
    public void addGpoint(int _gpoint){
        System.unregistUmigameScoreboard4eachPL(this);
        this.gpoint += _gpoint;
        System.registUmigameScoreboard4eachPL(this);
    }

    public int getEpoint(){ return this.epoint;}
    public void setEpoint(int _epoint){
        if(this.num != 0){
            System.unregistUmigameScoreboard4eachPL(this);
        }
        this.gpoint = _epoint;
        if(this.num != 0){
            System.registUmigameScoreboard4eachPL(this);
        }
    }
    public void addEpoint(int _epoint){
        System.unregistUmigameScoreboard4eachPL(this);
        this.epoint += _epoint;
        System.registUmigameScoreboard4eachPL(this);
    }

    public boolean getGM(){return this.GM;}
    public void setGM(boolean bool){this.GM = bool;}

    public int getNum(){ return this.num;}
    public void setNum(int _num){ this.num = _num;}
}

package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class UPlayer {
    public static HashMap<String, UPlayer> map = new HashMap();
    private final Player player;
    private final String playername;
    private int gpoint;
    private int epoint;
    private int num;
    private boolean GM;

    // mapのkeyをPlayerではなくプレイヤーネームのString型に変更してみた．setは廃止でgetで無ければ生成する方式をとりあえず採用．
    // これでリログしてPlayer型が変わっても同一UPlayerが得られるはず...?
    // ただし，予想されるバグとしてゲーム中にレフト，名前を変えてジョインした場合はバグる．まあそんなんことはあるはずない．UUIDで回避するコストは高すぎる．

    /*
    public static UPlayer setUPlayer(Player player){
        map.put(player.getDisplayName(), new UPlayer(player));
        return map.get(player.getDisplayName());
    }
     */

    public static UPlayer getUPlayer(Player player) {
        if (!map.containsKey(player.getDisplayName())) {
            map.put(player.getDisplayName(), new UPlayer(player));
        }
        return map.get(player.getDisplayName());
    }

    public UPlayer(Player player) {
        this.player = player;
        this.playername = player.getDisplayName();
        this.gpoint = 0;
        this.epoint = 0;
        this.GM = false;
        this.num = 0;
    }

    public Player getPlayer(){ return this.player; }

    public String getPlayername(){ return this.playername; }

    public int getGpoint(){ return this.gpoint;}
    public void setGpoint(int _gpoint){
        if(this.num != 0){
            ScoreBoard.unregistUmigameScoreboard4eachPL(this);
        }
        this.gpoint = _gpoint;
        if(this.num != 0){
            ScoreBoard.registUmigameScoreboard4eachPL(this);
        }
    }
    public void addGpoint(int _gpoint){
        ScoreBoard.unregistUmigameScoreboard4eachPL(this);
        this.gpoint += _gpoint;
        ScoreBoard.registUmigameScoreboard4eachPL(this);
    }

    public int getEpoint(){ return this.epoint;}
    public void setEpoint(int _epoint){
        if(this.num != 0){
            ScoreBoard.unregistUmigameScoreboard4eachPL(this);
        }
        this.gpoint = _epoint;
        if(this.num != 0){
            ScoreBoard.registUmigameScoreboard4eachPL(this);
        }
    }
    public void addEpoint(int _epoint){
        ScoreBoard.unregistUmigameScoreboard4eachPL(this);
        this.epoint += _epoint;
        ScoreBoard.registUmigameScoreboard4eachPL(this);
    }

    public boolean getGM(){return this.GM;}
    public void setGM(boolean bool){this.GM = bool;}

    public int getNum(){ return this.num;}
    public void setNum(int _num){ this.num = _num;}


    public static void reset() {
        map.clear();
    }
}

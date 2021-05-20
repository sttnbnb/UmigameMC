package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class UPlayer {
    public static HashMap<Player, UPlayer> map = new HashMap();
    private Player player;
    private int gpoint;
    private int epoint;
    private int num;
    private boolean GM;



    // 色々やりすぎてわけ分からんくなった．多分現状で多分動くと思うけど変なことするとエラー出ると思う．
    // getUPlayerでいない人指定した時にエラー吐くと思う．いなかったら追加する処理消したし．
    public static UPlayer setUPlayer(Player playername){
        map.put(playername, new UPlayer(playername));
        return map.get(playername);
    }

    public static UPlayer getUPlayer(Player playername) {
        // なんか〜これで判定取ると〜〜，map内にあるのに〜，無い判定されて〜〜，UPlayerが無限に増えるんですよ〜〜〜
            // なんでなんぶっ飛ばすぞ粕が

//        if (!map.containsKey(playername)) {
//            Bukkit.broadcastMessage("[debug] b4: " + map);
//            map.put(playername, new UPlayer(playername));
//            Bukkit.broadcastMessage("[debug] one added");
//            Bukkit.broadcastMessage("[debug] af: " + map);
//        }
        return map.get(playername);
    }

    public UPlayer(Player player) {
        this.player = player;
        this.gpoint = 0;
        this.epoint = 0;
        this.GM = false;
        this.num = 0;
    }

    public Player getPlayer(){ return this.player; }

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

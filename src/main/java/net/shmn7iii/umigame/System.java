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

    public static String getTXTContent(String filename){
        StringBuilder sb = new StringBuilder();
        try {
            // ファイルのパスを指定する
            File file = new File("./plugins/Umigame/" + filename + ".txt");

            // ファイルが存在しない場合に例外が発生するので確認する
            if (!file.exists()) {
                return "エラー:ファイルが存在しません - " + filename;
            }

            // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                sb.append(data);
            }
            // 最後にファイルを閉じてリソースを開放する
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void broadcastQuestion(String filename){
        Bukkit.broadcastMessage(""+ ChatColor.GREEN + ChatColor.BOLD + "[問題]\n" + ChatColor.RESET + getTXTContent("Questions/" + filename));
    }

    public static void broadcastAnswer(String filename){
        Bukkit.broadcastMessage(""+ ChatColor.RED + ChatColor.BOLD + "[解答]\n" + ChatColor.RESET + getTXTContent("Answers/" + filename));
    }


    public static void registUmigameScoreboard4eachPL(UPlayer uplayer){
        int num = uplayer.getNum();
        Score score = Umigame.SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + " └ " + uplayer.getPlayer().getName());
        score.setScore(num * -10);
        score = Umigame.SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   ├ " + ChatColor.YELLOW + "正答ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getGpoint());
        score.setScore(num * -10 -1);
        score = Umigame.SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   └ " + ChatColor.GREEN + "質問ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getEpoint());
        score.setScore(num * -10 -2);
        score = Umigame.SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + num);
        score.setScore(num * -10 -3);
    }

    public static void unregistUmigameScoreboard4eachPL(UPlayer uplayer){
        int num = uplayer.getNum();
        Umigame.board.resetScores("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   ├ " + ChatColor.YELLOW + "正答ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getGpoint());
        Umigame.board.resetScores("" + ChatColor.DARK_GRAY + num + ChatColor.RESET + "   └ " + ChatColor.GREEN + "質問ポイント: " + ChatColor.GOLD + ChatColor.BOLD + uplayer.getEpoint());
    }

    public static void registUmigameScoreboard4GM(UPlayer uplayer){
        Score score = Umigame.SCUMIGAME.getScore("" + ChatColor.RED + ChatColor.BOLD + "GameMaster");
        score.setScore(0);
        score = Umigame.SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + "0" + ChatColor.RESET + " └ " + uplayer.getPlayer().getName());
        score.setScore(-1);
        score = Umigame.SCUMIGAME.getScore("" + ChatColor.DARK_GRAY + "0");
        score.setScore(-2);
        score = Umigame.SCUMIGAME.getScore("" + ChatColor.AQUA + ChatColor.BOLD + "Player");
        score.setScore(-3);
    }

    // 単純にdisplayするだけ
    public static void displayRanking(){
        ArrayList<String[]> ranking = new ArrayList<>();
        HashMap<Integer,UPlayer> map = new HashMap<>();
        for(Player p:Bukkit.getOnlinePlayers()){
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

        Bukkit.broadcastMessage("==" + ChatColor.RED + ChatColor.BOLD + " [最終結果] " + ChatColor.RESET + "=====================================");
        int rank = 1;
        for(String[] str:ranking){
            Bukkit.broadcastMessage("" + ChatColor.GOLD + ChatColor.BOLD + rank + "位" + ChatColor.RESET
                    + " - " + ChatColor.YELLOW + "正答ポイント: " + ChatColor.GOLD + ChatColor.BOLD + str[0] + ChatColor.RESET
                    + " - " + ChatColor.GREEN + "質問ポイント: " + ChatColor.GOLD + ChatColor.BOLD + str[1] + ChatColor.RESET
                    + " - " + ChatColor.AQUA + ChatColor.BOLD + str[2]);
        }
        Bukkit.broadcastMessage("================================================");
    }
}

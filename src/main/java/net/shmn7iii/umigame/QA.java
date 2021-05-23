package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QA {

    public static String getTXTContent(String filename){
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File("./plugins/Umigame/" + filename);

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
        Bukkit.broadcastMessage(""+ ChatColor.GREEN + ChatColor.BOLD + "[問題]  「" + filename.replace(".txt", "") + "」\n" + ChatColor.RESET + getTXTContent("Questions/" + filename));
    }

    public static void broadcastAnswer(String filename){
        Bukkit.broadcastMessage(""+ ChatColor.RED + ChatColor.BOLD + "[解答]  「" + filename.replace(".txt", "") + "」\n" + ChatColor.RESET + getTXTContent("Answers/" + filename));
    }


    public static void loadFiles(){

        File file = new File("./plugins/Umigame/Questions/");
        File[] files = file.listFiles();

        if(files != null){
            for (File item : files) {
                if(!(item.getName().startsWith("_"))){
                    Command.SUBCOMMANDS.add("question " + item.getName());
                }
            }
        }

        File filea = new File("./plugins/Umigame/Answers/");
        File[] filesa = filea.listFiles();

        if(filesa != null){
            for (File item : filesa) {
                if(!(item.getName().startsWith("_"))){
                    Command.SUBCOMMANDS.add("answer " + item.getName());
                }
            }
        }
    }


}

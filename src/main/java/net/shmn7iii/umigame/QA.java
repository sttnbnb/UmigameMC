package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class QA {
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
}

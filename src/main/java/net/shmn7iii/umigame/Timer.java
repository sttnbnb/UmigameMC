package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Timer extends BukkitRunnable {
    private final Umigame plg;
    private int count;

    /**
     * コンストラクタ
     *
     * @param plg_   プラグインメインクラスのインスタンス
     * @param count_ 表示する値
     */
    public Timer(Umigame plg_, int count_) {
        plg = plg_;
        count = count_;
    }

    /**
     * 非同期処理実行メソッド
     */
    public void run() {
        if (count == 0) {
            PinPon.PNPN = false;
            PinPon.PPM = new HashMap<>();
            this.cancel();
        }
        count--;
    }
}
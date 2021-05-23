package net.shmn7iii.umigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {

    public static String senderErrorMessage = "Can't execute this from server console!";

    public static ArrayList<String> SUBCOMMANDS = new ArrayList<>(Arrays.asList("help", "start", "end", "question", "answer", "pinpon"));

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("umigame")) {

            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "===[Umigame - CommandHelp]==============");
                sender.sendMessage("\n" + ChatColor.GOLD + ChatColor.BOLD + "/umigame start [<Player>]");
                sender.sendMessage("  - Start game. You can specify GM with the first argument. When not, executor will be it.");
                sender.sendMessage("\n" + ChatColor.GOLD + ChatColor.BOLD + "/umigame end");
                sender.sendMessage("  - End game.");
                sender.sendMessage("\n" + ChatColor.GOLD + ChatColor.BOLD + "/umigame question <filename.txt>");
                sender.sendMessage("  - Broadcast question content you stored before game. *FILENAME DOES NOT REQUIRE EXTENSION\n  e.g.) sample.txt -> \"/umigame question sample.txt\"");
                sender.sendMessage("\n" + ChatColor.GOLD + ChatColor.BOLD + "/umigame answer <filename.txt>");
                sender.sendMessage("  - Broadcast answer content you stored before game. *FILENAME DOES NOT REQUIRE EXTENSION\n  e.g.) sample.txt -> \"/umigame answer sample.txt\"");

                return true;
            }

            else if (args[0].equalsIgnoreCase("start")){
                if(args.length == 2){
                    try{
                        Player GM = Bukkit.getPlayer(args[1]);
                        GameProcess.gameStart(GM);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
                else{
                    if(sender instanceof Player) {
                        Player GM = (Player) sender;
                        GameProcess.gameStart(GM);
                    }
                    else{
                        sender.sendMessage(senderErrorMessage);
                    }
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("end")){
                if(!GameProcess.GAME){
                    sender.sendMessage(Umigame.MessagePrefix + "すでにゲームは終了しています．");
                    return true;
                }
                GameProcess.gameEnd();
                return true;
            }
            else if (args[0].equalsIgnoreCase("question")){
                if(args.length == 2){
                    QA.broadcastQuestion(args[1]);
                }
                else{
                    return false;
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("answer")){
                if(args.length == 2){
                    QA.broadcastAnswer(args[1]);
                }
                else{
                    return false;
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("pinpon")){
                if(args.length == 2) {
                    PinPon.doPinpon(Bukkit.getPlayer(args[1]));
                    return true;
                }
                else{
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        PinPon.doPinpon(player);
                    }
                    else{
                        sender.sendMessage(senderErrorMessage);
                    }
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("debug")){
                if(args[1].equalsIgnoreCase("setGPoint")){
                    try{
                        UPlayer.getUPlayer(Bukkit.getPlayer(args[2])).setGpoint(Integer.parseInt(args[3]));
                    } catch (NumberFormatException e) {
                        sender.sendMessage("/umigame debug setGPoint <Player> <GPoint>");
                    }
                }
                else if(args[1].equalsIgnoreCase("setEPoint")){
                    try{
                        UPlayer.getUPlayer(Bukkit.getPlayer(args[2])).setEpoint(Integer.parseInt(args[3]));
                    } catch (NumberFormatException e) {
                        sender.sendMessage("/umigame debug setEPoint <Player> <EPoint>");
                    }
                }
                else if(args[1].equalsIgnoreCase("getUPlayer")){
                    Bukkit.broadcastMessage(String.valueOf(UPlayer.getUPlayer((Player)sender)));
                }
            }
            else {
                return false;
            }
        }
        return false;
    }


    /*
    パクリ元:
        https://www.spigotmc.org/threads/tab-complete.160308/
    */
    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        //create new array
        final List<String> completions = new ArrayList<>();
        //copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
        StringUtil.copyPartialMatches(args[0], SUBCOMMANDS, completions);
        //sort the list
        Collections.sort(completions);
        return completions;
    }
}

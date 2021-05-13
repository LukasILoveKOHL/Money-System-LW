package net.sernoxcraft.schottersystem.commands;

import net.sernoxcraft.schottersystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ©2016-2021 LvckyWorld - By StossenHDYT all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 **/
public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // /pay <user> <geld>
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                    //Spieler der das Geld bekommen soll.
                if (target == null) {
                    p.sendMessage(Main.prefix + "§cDer Spieler ist nicht online!");
                    return false;
                }
                int sum = 5;
                try{
                    if (args[1].contains("-") || args[1].contains("%") || args[1].contains("*") || args[1].contains("/") ){
                        p.sendMessage(Main.prefix + "§cBitte gebe eine gültige Summe an!");
                        return false;
                    }
                    sum = Integer.parseInt(args[1]);
                }catch (Exception e){
                    p.sendMessage(Main.prefix + "§cBitte gebe eine gültige Summe an!");
                }

                //MySQL part
                //Sender
                int balancePlayerSender = 1000;
                //Target
                int balancePlayerTarget = 1000;

                //Nicht Anfassen
                int sumPlayerSender = 0;
                int sumPlayerTarget = 0;

                if (balancePlayerSender < sum){
                    //EndGeld Player
                     sumPlayerSender = balancePlayerSender - sum
                    //EndGeld Target
                     sumPlayerTarget = balancePlayerTarget + sum;

                     //Set New MySQL Values


                     p.sendMessage(Main.prefix + "§3Du hast den Spieler " + target.getDisplayName() + "§r§b " + sum + "§r§3 überwiesen.");
                     target.sendMessage(Main.prefix + "§3Der Spieler " + p.getDisplayName() + "§r§3 hat dir §b" + sum + "§r§3 Schotter überwiesen.");


                } else {
                    p.sendMessage(Main.prefix + "§cSo viel Geld hast du nicht!");
                    return false;
                }

            } else {
                p.sendMessage(Main.prefix + "§cFalsche Usage. Benutze bitte§7: §b/pay <user> <summe>");
            }
        }
        return false;
    }
}

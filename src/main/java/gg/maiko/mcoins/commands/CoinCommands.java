package gg.maiko.mcoins.commands;

import gg.maiko.mcoins.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Maiko
 * Date: 11/15/2020
 */
public class CoinCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        Profile profile = Profile.getPlayerUUID(player.getUniqueId());
        player.sendMessage(ChatColor.DARK_GREEN + "Coins: " + ChatColor.GREEN + profile.getCoins());
        return false;
    }
}

package gg.maiko.mcoins.profile;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Maiko
 * Date: 11/15/2020
 */

public class ProfileListener implements Listener {

    @EventHandler
    public void onAysncPlayerPreloginEvent(AsyncPlayerPreLoginEvent e) {
        Profile profile = new Profile(e.getUniqueId());
        if (profile.getPlayer() == null) {
            profile.setPlayer(e.getName());
            profile.save();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Profile profile = Profile.getPlayerUUID(e.getPlayer().getUniqueId());
        profile.setPlayer(player.getName());

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Profile profile = Profile.getProfile(player);
        if (profile != null) {
            profile.save();
            Profile.getProfiles().remove(profile);
        }
    }
}

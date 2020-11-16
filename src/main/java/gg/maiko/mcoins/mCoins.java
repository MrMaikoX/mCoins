package gg.maiko.mcoins;

import gg.maiko.mcoins.commands.CoinCommands;
import gg.maiko.mcoins.database.CoinsMongo;
import gg.maiko.mcoins.profile.Profile;
import gg.maiko.mcoins.profile.ProfileListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Maiko
 * Date: 11/15/2020
 */

@Getter
@Setter
public class mCoins extends JavaPlugin {

    public static mCoins instance;
    private CoinsMongo coinsMongo;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        new CoinsMongo(this);
        getCommand("coins").setExecutor(new CoinCommands());
        getServer().getPluginManager().registerEvents(new ProfileListener(), this);
    }

    @Override
    public void onDisable() {
        Profile.getProfiles().forEach(Profile::save);
        coinsMongo.getClient().close();
    }
}

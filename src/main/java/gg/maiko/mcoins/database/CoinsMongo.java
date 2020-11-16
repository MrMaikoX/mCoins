package gg.maiko.mcoins.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gg.maiko.mcoins.mCoins;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Created by Maiko
 * Date: 11/15/2020
 */

@Getter
public class CoinsMongo {

    private final MongoClient client;
    private final MongoDatabase database;
    private final MongoCollection profiles;

    public CoinsMongo(JavaPlugin plugin) {
        if(mCoins.instance.getConfig().getBoolean("MONGO.AUTH.ENABLED"))
            client = new MongoClient(new ServerAddress(plugin.getConfig().getString("MONGO.HOST"), plugin.getConfig().getInt("MONGO.PORT")), Arrays.asList(MongoCredential.createCredential(plugin.getConfig().getString("MONGO.AUTH.USER"), plugin.getConfig().getString("MONGO.DATABASE"), plugin.getConfig().getString("MONGO.AUTH.PASSWORD").toCharArray())));
        else
            client = new MongoClient(new ServerAddress(plugin.getConfig().getString("MONGO.HOST"), plugin.getConfig().getInt("MONGO.PORT")));


        database = client.getDatabase(plugin.getConfig().getString("MONGO.DATABASE"));

        profiles = database.getCollection("profiles");
    }
}
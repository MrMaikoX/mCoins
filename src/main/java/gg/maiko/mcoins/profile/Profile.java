package gg.maiko.mcoins.profile;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import gg.maiko.mcoins.database.CoinsMongo;
import gg.maiko.mcoins.mCoins;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by Maiko
 * Date: 11/15/2020
 */

@Getter @Setter
public class Profile {

    private static List<Profile> profiles = new ArrayList<>();
    private UUID uuid;
    private String player;
    private int coins;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.load();
    }

    private void load() {
        profiles.add(this);

        Document document = (Document) mCoins.instance.getCoinsMongo().getProfiles().find(eq("uuid", this.uuid.toString())).first();
        if(document == null) {
            this.coins = mCoins.instance.getConfig().getInt("COINS.STARTING");
            return;
        }

        this.coins = document.getInteger("coins");

        if (document.containsKey("player")) {
            this.player = document.getString("player");
        }

    }

    public void save() {
        Document document = new Document();

        document.put("uuid", this.uuid.toString());
        document.put("player", this.player);
        document.put("coins", this.coins);

        mCoins.instance.getCoinsMongo().getProfiles().replaceOne(eq("uuid", this.uuid.toString()), document, new UpdateOptions().upsert(true));
    }

    public static Collection<Profile> getProfiles() {
        return profiles;
    }

    public static Profile getPlayerUUID(UUID uuid) {
        return profiles.stream().filter(profile -> profile.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    public static Profile getProfile(Player player) {
        return profiles.stream().filter(profile -> profile.getPlayer().equals(player)).findFirst().orElse(null);
    }
}

package me.darkolythe.betterchatmanager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class BetterChatManager extends JavaPlugin {

    public static String prefix = ChatColor.translateAlternateColorCodes('&', "&f&l[&9&lGroupChat&f&l] ");

    private static BetterChatManager plugin;
    private ChatListener chatlistener = new ChatListener(this);

    public static ArrayList<GroupChat> groups = new ArrayList<>();
    public static HashMap<Player, Member> members = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(chatlistener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BetterChatManager getInstance() {
        return plugin;
    }
}

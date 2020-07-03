package me.darkolythe.betterchatmanager;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private BetterChatManager main;
    ChatListener(BetterChatManager plugin) {
        main = plugin;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    private void onChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }
        player.sendMessage("Here's a message somebody else sent");
        player.sendMessage("Here's another message somebody else sent");

        //send group chat messages here

        Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
            @Override
            public void run() {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.getServer().dispatchCommand(console, "tellraw @a " + "[\"\",{\"text\":\"[General]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/gamemode survival\"}},{\"text\":\"[Tester]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/gc manage\"}}]");
            }
        }, 1L);

        event.setCancelled(true);
    }
}

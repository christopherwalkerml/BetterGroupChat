package me.darkolythe.betterchatmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    private BetterChatManager main = BetterChatManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("bettergroupchat.command")) {
                if (cmd.getName().equalsIgnoreCase("groupchat")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("manage")) {
                            GroupChatManager.createGUI(BetterChatManager.members.get(player));
                        } else {
                            player.sendMessage(BetterChatManager.prefix + ChatColor.WHITE + "Invalid command. /groupchat [manage, create]");
                        }
                    } else if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("create")) {
                            ReturnPackage ret = GroupChat.createGroup(player, args[1]);
                            player.sendMessage(BetterChatManager.prefix + ret.message);
                        } else if (args[0].equalsIgnoreCase("invite")) {

                        } else {
                            player.sendMessage(BetterChatManager.prefix + ChatColor.WHITE + "Invalid command. /groupchat [manage, create]");
                        }
                    }
                }
            }
        }
        return true;
    }
}

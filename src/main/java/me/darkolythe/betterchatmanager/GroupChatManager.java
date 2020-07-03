package me.darkolythe.betterchatmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GroupChatManager {

    public static void createGUI(Member member) {
        Player player = member.getPlayer();
        Inventory inv = Bukkit.getServer().createInventory(player, 54, ChatColor.BLUE + "ChatGroups");

        int index = 0;
        for (GroupChat group : member.getGroups()) {
            ItemStack item;
            if (member.equals(group.getLeader())) {
                item = new ItemStack(Material.BOOK);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(net.md_5.bungee.api.ChatColor.RESET + group.getName());
                meta.setLore(Arrays.asList(
                        ChatColor.YELLOW + "Right Click to view members",
                        ChatColor.RED + "Left Click to disband group",
                        "",
                        ChatColor.GREEN + "you are the leader of this group",
                        ChatColor.YELLOW + "Members: " + ChatColor.GRAY + group.getMembers().size(),
                        ChatColor.DARK_GRAY.toString() + group.getID()));
                item.setItemMeta(meta);
            } else {
                item = new ItemStack(Material.PAPER);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(group.getName());
                meta.setLore(Arrays.asList(
                        ChatColor.YELLOW + "Right Click to view members",
                        ChatColor.RED + "Left Click to leave group",
                        ChatColor.YELLOW + "Members: " + ChatColor.GRAY + group.getMembers().size(),
                        ChatColor.DARK_GRAY.toString() + group.getID()));
                item.setItemMeta(meta);
            }
            inv.setItem(index, item);
            index++;
        }

        player.openInventory(inv);
    }
}

package me.darkolythe.betterchatmanager;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Member {

    private Player player;
    private ArrayList<GroupChat> groups;
    private GroupChat currentGroup;

    Member(Player newMember) {
        player = newMember;
        groups = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public boolean addGroup(GroupChat newGroup) {
        if (!groups.contains(newGroup)) {
            groups.add(newGroup);
            return true;
        }
        return false;
    }

    public boolean removeGroup(GroupChat oldGroup) {
        if (groups.contains(oldGroup)) {
            groups.remove(oldGroup);
            return true;
        }
        return false;
    }

    public ArrayList<GroupChat> getGroups() {
        return groups;
    }

    public boolean canAddGroup() {
        int current = groups.size();

        if (player.hasPermission("bettergroupchat.limit.36")) {
            return current < 36;
        }
        if (player.hasPermission("bettergroupchat.limit.18")) {
            return current < 18;
        }
        if (player.hasPermission("bettergroupchat.limit.9")) {
            return current < 9;
        }
        if (player.hasPermission("bettergroupchat.limit.3")) {
            return current < 3;
        }
        if (player.hasPermission("bettergroupchat.limit.1")) {
            return current < 1;
        }

        return false;
    }
}

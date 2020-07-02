package me.darkolythe.betterchatmanager;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Member {

    private Player player;
    private ArrayList<GroupChat> groups;
    private boolean isLeader;

    Member(Player newMember, boolean leader) {
        player = newMember;
        isLeader = leader;
        groups = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public boolean getLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
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

    public boolean canAddGroup() {
        int current = groups.size();

        if (player.hasPermission("bettergroupchat.limit.none")) {
            return current < 1000;
        }
        if (player.hasPermission("bettergroupchat.limit.5")) {
            return current < 5;
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

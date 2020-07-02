package me.darkolythe.betterchatmanager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

public class GroupChat {

    private String name;
    private ArrayList<Member> members;
    private Queue<Message> messages;
    private ArrayList<Player> invited;

    GroupChat(Member newMember, String newName) {
        members = new ArrayList<>();
        messages = new PriorityQueue<>();
        name = newName;

        members.add(newMember);
    }

    public boolean addMember(Member member) {
        if (member.canAddGroup()) {
            members.add(member);
            member.addGroup(this);
            return true;
        }
        return false;
    }

    /**
     * Adds a new message to the messages queue and removes the front message if the queue is longer than 99 (chat limit).
     * @param message Message to add
     */
    public void addMessage(Message message) {
        messages.add(message);

        if (messages.size() > 99) {
            messages.remove();
        }
    }

    /**
     * Removes a member from the group. Also removes the group from the member.
     * @param member Member to remove
     */
    public void removeMember(Member member) {
        members.remove(member);
        member.removeGroup(this);
    }

    /**
     * Creates a new group with a leader and a name.
     * @param leader Member to be the group leader
     * @param name String name of the group
     * @return boolean on success / fail
     */
    public static boolean createGroup(Player leader, String name) {
        Member member = BetterChatManager.members.get(leader);
        if (member.canAddGroup()) {
            GroupChat group = new GroupChat(member, name);
            member.addGroup(group);
            return true;
        }
        return false;
    }

    public boolean inviteMember(String player) {
        for (Player p : BetterChatManager.getInstance().getServer().getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(player) || p.getDisplayName().equalsIgnoreCase(player)) {
                Member member = BetterChatManager.members.get(p);
                if (member.canAddGroup()) {

                }
            }
        }
    }
}

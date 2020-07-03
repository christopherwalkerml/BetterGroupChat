package me.darkolythe.betterchatmanager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;

public class GroupChat {

    private String name;
    private UUID id;
    private ArrayList<Member> members;
    private Member leader;
    private Queue<Message> messages;
    private ArrayList<Member> invited;
    private boolean isOpen;

    GroupChat(Member newLeader, String newName) {
        members = new ArrayList<>();
        invited = new ArrayList<>();
        leader = newLeader;
        messages = new PriorityQueue<>();
        name = newName;
        isOpen = false;

        id = UUID.randomUUID();

        members.add(newLeader);
    }

    public ReturnPackage addMember(Member member) {
        if (isOpen || invited.contains(member)) {
            if (member.canAddGroup()) {
                members.add(member);
                member.addGroup(this);
                return new ReturnPackage(true, ChatColor.GREEN + "you successfully joined " + name);
            }
        }
        return new ReturnPackage(false, ChatColor.RED + "you do not have an invitation to join " + name);
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

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public UUID getID() {
        return id;
    }

    public Member getLeader() {
        return leader;
    }

    /**
     * Creates a new group with a leader and a name.
     * @param leader Member to be the group leader
     * @param name String name of the group
     * @return boolean on success / fail
     */
    public static ReturnPackage createGroup(Player leader, String name) {
        Member member = BetterChatManager.members.get(leader);
        if (member.canAddGroup()) {
            GroupChat group = new GroupChat(member, name);
            member.addGroup(group);
            BetterChatManager.groups.add(group);
            return new ReturnPackage(true, ChatColor.GREEN + name + " was successfully created!");
        }
        return new ReturnPackage(false, ChatColor.RED + "You are already in too many groups!");
    }

    public ReturnPackage inviteMember(Member inviter, String player) {
        if (inviter.equals(leader)) {
            for (Player p : BetterChatManager.getInstance().getServer().getOnlinePlayers()) {
                if (p.getName().equalsIgnoreCase(player) || p.getDisplayName().equalsIgnoreCase(player)) {
                    Member member = BetterChatManager.members.get(p);
                    if (member.canAddGroup()) {
                        invited.add(member);
                        return new ReturnPackage(true, ChatColor.GREEN + p.getName() + " was invited to the group!");
                    }
                    return new ReturnPackage(false, ChatColor.RED + p.getName() + " is in too many groups!");
                }
            }
            return new ReturnPackage(false, ChatColor.RED + player + " is not online.");
        } else {
            return new ReturnPackage(false, ChatColor.RED + "You are not allowed to invite members.");
        }
    }
}

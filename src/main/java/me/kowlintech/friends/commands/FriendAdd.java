package me.kowlintech.friends.commands;

import me.kowlintech.friends.Friends;
import me.kowlintech.friends.utils.Colour;
import me.kowlintech.friends.utils.FriendsManager;
import me.kowlintech.friends.utils.enums.Error;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class FriendAdd implements CommandExecutor {

    private boolean hasperms = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            hasperms = true;
        } else {
            if(sender.hasPermission("friends.addfriend")) {
                hasperms = true;
            } else {
                hasperms = false;
            }
        }
        if(hasperms) {
            if(args.length == 1) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
                if(player.hasPlayedBefore()) {
                    Player senderPlayer = (Player) sender;
                    try {
                        if(FriendsManager.incomingRequests(player.getUniqueId().toString()).size() > 20) {
                            sender.sendMessage(Colour.prefix(ChatColor.RED) + Colour.translate("&cThat player already has the maximum number of incoming friend requests (20). Please try again later or ask them to either accept or deny some requests."));
                            return true;
                        } else {
                            if(FriendsManager.getPlayerFriends(player.getUniqueId().toString()).size() > 20) {
                                sender.sendMessage(Colour.prefix(ChatColor.RED) + Colour.translate("&cThat player already has the maximum number of friends (20). Please try again later or ask them to remove some friends."));
                                return true;
                            } else {
                                if(FriendsManager.outgoingRequests(senderPlayer.getUniqueId().toString()).size() > 20) {
                                    sender.sendMessage(Colour.prefix(ChatColor.RED) + Colour.translate("&cYou already have the maximum number of friend requests (20). Please either accept or deny some requests."));
                                    return true;
                                } else {
                                    if(FriendsManager.getPlayerFriends(senderPlayer.getUniqueId().toString()).size() > 20) {
                                        sender.sendMessage(Colour.prefix(ChatColor.RED) + Colour.translate("&cYou already have the maximum number of friends (20). Please remove some friends."));
                                        return true;
                                    } else {
                                        FriendsManager.addFriendRequest(senderPlayer.getUniqueId().toString(), player.getUniqueId().toString());
                                        sender.sendMessage(Colour.prefix(ChatColor.GREEN) + Colour.translate("Friend request has been sent to " + senderPlayer.getName() + ". To remove/delete this request please use /fgui."));
                                        return true;
                                    }
                                }
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return true;
                } else {
                    sender.sendMessage(Colour.prefix(ChatColor.RED) + "According to the world files that player has not played before. If they are online please ask them to relog.");
                    return true;
                }
            } else {
                sender.sendMessage(Colour.translate("&cUsage: /friendadd <player>"));
                return true;
            }
        } else {
            sender.sendMessage(Error.NO_PERMISSION.getError());
            return true;
        }
    }
}

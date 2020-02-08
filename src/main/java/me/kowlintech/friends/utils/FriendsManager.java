package me.kowlintech.friends.utils;

import me.kowlintech.friends.Friends;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendsManager {

    public static void addFriendToPlayer(String playera, String playerb) throws SQLException {
        Friends.connection.createStatement().execute("INSERT INTO friends (playera, playerb) VALUES ('" + playera + ", " + playerb + "')");
    }

    public static void removeFriendFromPlayer(String playera, String playerb) throws SQLException {
        Friends.connection.createStatement().execute("DELETE FROM friends WHERE playera='" + playera + "' AND playerb='" + playerb + "'");
    }

    public static ArrayList<String> getPlayerFriends(String playeruuid) throws SQLException {
        ArrayList<String> playerFriends = new ArrayList<String>();
        PreparedStatement st = Friends.connection.prepareStatement("SELECT * FROM friends WHERE playera='" + playeruuid + "'");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            playerFriends.add(rs.getString("playerb"));
        }
        rs.close();
        st.close();
        return playerFriends;
    }
}

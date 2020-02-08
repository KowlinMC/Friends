package me.kowlintech.friends;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Friends extends JavaPlugin {

    public static Connection connection;

    public static Friends getPlugin() {
        return getPlugin(Friends.class);
    }

    @Override
    public void onEnable() {
        getLogger().info("Starting Up...");
        try {
            openConnection();
            checkTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting Down...");
        try {
            connection.close();
            getLogger().info("Closed Connection to Database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://192.168.0.5/friendsbukkit?user=postgres&password=kowlin";
        Connection conn = DriverManager.getConnection(url);
        connection = conn;
        getPlugin().getLogger().info("Connected to Database!");
    }

    public static void checkTables() throws SQLException {
        Statement st = connection.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS friends (playera TEXT NOT NULL, playerb TEXT NOT NULL)");
        getPlugin().getLogger().info("Checked table \"friends\"!");
    }
}

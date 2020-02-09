package me.kowlintech.friends.listeners;

import me.kowlintech.friends.utils.Colour;
import me.kowlintech.friends.utils.GUI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.sql.SQLException;

public class GUIListener implements Listener {

    @EventHandler
    private void inventoryClick(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lFriends GUI"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType().equals(Material.AIR)) {
                return;
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("&6&lFriends List")) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                p.closeInventory();
                p.openInventory(GUI.friendListGUIInventory(p));
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("&6&lIncoming Friend Requests")) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                p.closeInventory();
                p.openInventory(GUI.incomingRequestsGUIInventory(p));
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("&6&lOutgoing Friend Requests")) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                p.closeInventory();
                p.openInventory(GUI.outgoingRequestsGUIInventory(p));
            } else if(e.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lFriends List"))) {
                e.setCancelled(true);
            } else if(e.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lIncoming Friend Requests"))) {
                e.setCancelled(true);
            } else if(e.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lOutgoing Friend Requests"))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInvOpen(InventoryOpenEvent event) {
        if(event.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lFriends List"))) {
            GUI.friendlistguislots = 0;
        } else if(event.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lIncoming Friend Requests"))) {
            GUI.irguislots = 0;
        } else if(event.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lOutgoing Friend Requests"))) {
            GUI.orguislots = 0;
        }
    }
}

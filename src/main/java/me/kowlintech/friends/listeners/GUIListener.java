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
            if(e.getCurrentItem().getType() == Material.BOOK_AND_QUILL) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                p.openInventory(GUI.friendListGUIInventory(p));
                return;
            } else if(e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                p.openInventory(GUI.incomingRequestsGUIInventory(p));
                return;
            } else if(e.getCurrentItem().getType() == Material.APPLE) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                p.openInventory(GUI.outgoingRequestsGUIInventory(p));
                return;
            } else if(e.getCurrentItem().getType() == Material.BARRIER) {
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                p.closeInventory();
                return;
            } else {
                return;
            }
        } else if(e.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lFriends List"))) {
            e.setCancelled(true);
            return;
        } else if(e.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lIncoming Friend Requests"))) {
            e.setCancelled(true);
            return;
        } else if(e.getInventory().getTitle().equalsIgnoreCase(Colour.translate("&9&lOutgoing Friend Requests"))) {
            e.setCancelled(true);
            return;
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

package org.example.teleport_bow.handler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import org.example.teleport_bow.Main;

import java.util.Optional;

public class BowEventHandler implements Listener {

    private final Main plugin;

    public BowEventHandler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleProjectileHitEvent(ProjectileHitEvent e) {
        if (e.getEntity().hasMetadata("teleport_bow")) {
            Player shooter = (Player) e.getEntity().getShooter();
            Vector direction = shooter.getLocation().getDirection();
            Location location = e.getEntity().getLocation();
            location.setDirection(direction);
            shooter.teleport(location);
        }
    }

    @EventHandler
    public void handleProjectileLaunchEvent(ProjectileLaunchEvent e) {
        if (e.getEntityType() == EntityType.ARROW && e.getEntity().getShooter() instanceof Player) {
            Player shooter = (Player) e.getEntity().getShooter();

            ItemMeta bowItemMeta = Optional.ofNullable(shooter.getInventory().getItemInMainHand().getItemMeta())
                    .orElseGet(() -> shooter.getInventory().getItemInOffHand().getItemMeta());
            if (bowItemMeta.getDisplayName().equals(ChatColor.BLUE + "Teleport Bow")) {
                e.getEntity().setMetadata("teleport_bow", new FixedMetadataValue(plugin, true));
            }
        }
    }

}

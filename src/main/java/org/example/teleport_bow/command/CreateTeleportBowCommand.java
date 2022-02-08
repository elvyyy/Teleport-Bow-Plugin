package org.example.teleport_bow.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CreateTeleportBowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (args.length != 0) return true;

        Player player = (Player) sender;
        player.getInventory().addItem(createTeleportBow());
        return true;
    }

    private ItemStack createTeleportBow() {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);

        ItemMeta itemMeta = bow.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BLUE + "Teleport Bow");
        itemMeta.setUnbreakable(true);
        List<String> lores = List.of("Teleport player to the arrow hit");
        itemMeta.setLore(lores);

        bow.setItemMeta(itemMeta);
        return bow;
    }
}

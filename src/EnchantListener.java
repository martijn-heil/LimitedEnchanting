package me.Ninjoh.LimitedEnchanting;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchantListener implements Listener
{
    FileConfiguration config = me.Ninjoh.LimitedEnchanting.Main.plugin.getConfig();

    @EventHandler
    public void onEnchantItem(EnchantItemEvent e)
    {
        List<String> items = config.getStringList("items");

        ItemStack enchantedItem = e.getItem();

        Material enchantedItemMaterial = enchantedItem.getType();

        if(items.contains(enchantedItemMaterial.toString()))
        {
            // Cancel enchant event if the item is blacklisted
            e.setCancelled(true);

            if(config.getBoolean("message.sendMessage"))
            {
                e.getEnchanter().sendMessage(ChatColor.DARK_RED + config.getString("message.cancelledEnchantMessage"));
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        List<String> items = config.getStringList("items");

        if (e.getCurrentItem() != null && e.getClickedInventory() != null)
        {

            // If clicked inventory is an anvil
            if (e.getClickedInventory().getType().equals(InventoryType.ANVIL))
            {
                // Foreach blacklisted item list
                for (String item : items)
                {

                    // Forge material string to material object.
                    Material itemMaterial = Material.getMaterial(item);

                    if (e.getClickedInventory().contains(itemMaterial) && e.getClickedInventory().contains(Material.ENCHANTED_BOOK) && e.getSlotType().equals(InventoryType.SlotType.RESULT))
                    {
                            // If clicked inventory is an anvil && anvil contains enchanted book + a blacklisted item, cancel the event..
                            e.setCancelled(true);

                            // Send user error message..
                            if (config.getBoolean("message.sendMessage"))
                            {
                                e.getWhoClicked().sendMessage(ChatColor.DARK_RED + config.getString("message.cancelledEnchantMessage"));
                            }
                    }
                }
            }
        }
    }
}
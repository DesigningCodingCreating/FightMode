package me.owencoding.fightmode;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin implements CommandExecutor, Listener {
    @Override
    public void onEnable() {
        getLogger().info("You have enabled the FightMode plugin! :)");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("You have disabled the FightMode plugin!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player died = event.getEntity();
        Player killer = event.getEntity().getKiller();
        if (killer != null) {
            killer.sendTitle(ChatColor.RED + "Kill", ChatColor.DARK_RED + "You killed " + died.getName(), 100, 160, 100);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (command.getName().equalsIgnoreCase("FightMode")) {
                player.teleport(new Location(Bukkit.getWorld("FightWorld"), 0, -59, 0));
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 16));
                player.getInventory().addItem(new ItemStack(Material.RED_WOOL, 128));
                player.getInventory().addItem(new ItemStack(Material.COOKED_MUTTON, 8));
                player.getWorld().createExplosion(player.getLocation(), 0);

                Location playerLoc = player.getLocation();
                Location slime1 = new Location(playerLoc.getWorld(), playerLoc.getX() - 5, playerLoc.getY(), playerLoc.getZ());
                Location slime2 = new Location(playerLoc.getWorld(), playerLoc.getX() + 5, playerLoc.getY(), playerLoc.getZ());
                Location slime3 = new Location(playerLoc.getWorld(), playerLoc.getX(), playerLoc.getY(), playerLoc.getZ() - 5);
                Location slime4 = new Location(playerLoc.getWorld(), playerLoc.getX(), playerLoc.getY(), playerLoc.getZ() + 5);

                player.getWorld().spawnEntity(slime1, EntityType.SLIME);
                player.getWorld().spawnEntity(slime2, EntityType.SLIME);
                player.getWorld().spawnEntity(slime3, EntityType.SLIME);
                player.getWorld().spawnEntity(slime4, EntityType.SLIME);

                player.setMaxHealth(500);
                player.setHealth(100);
            }
        }
        return true;
    }
}


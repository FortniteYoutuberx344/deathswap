package deathswap.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.ChatColor;

import deathswap.Main;

public class Swap implements CommandExecutor{
	
	private Main plugin;
	
	public int task1;
	
	public int task2;
	
	public Swap(Main m) {
		this.plugin = m;
		plugin.getCommand("dswap").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p1 = (Player) sender;
		Player p2 = p1.getServer().getPlayer(args[0]);
		BukkitScheduler sched = p1.getServer().getScheduler();
		
		task1 = sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			
			int num = 10;
			
			@Override
			public void run() {
				
				if(num==0) {
					p1.getServer().broadcastMessage("Go!");
					Bukkit.getScheduler().cancelTask(task1);
				} else {
					p1.getServer().broadcastMessage(ChatColor.GREEN + "Game starting in " + Integer.toString(num--));
				}
			}
			
		}, 0L, 20L);  // 20 ticks per second
		
		sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
						
			@Override
			public void run() {
				
				task2 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {

					int num = 20;
				
					@Override
					public void run() {
					
						if(num==0) {
							
							Location l1 = p1.getLocation();
							p1.teleport(p2.getLocation());
							p2.teleport(l1);
							
							Bukkit.getScheduler().cancelTask(task2);
							
						} else {
							
							p1.getServer().broadcastMessage(ChatColor.GOLD + "Swapping in " + Integer.toString(num--) + "seconds");
							
						}
					}
				}, 0L, 20L); // 20 ticks per second
		
			}
			
		}, 6020L, 6000L); // 20+10*20 = 220 +5*60*20 = 6220 - 10*20 = 6020
		
		return true;
	}
}

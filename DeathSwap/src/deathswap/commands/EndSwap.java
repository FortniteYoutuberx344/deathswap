package deathswap.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import deathswap.Main;

public class EndSwap implements CommandExecutor{
	
	private Main plugin;
	
		public EndSwap(Main m) {
			this.plugin = m;
			plugin.getCommand("endswap").setExecutor(this);
		}
		
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			Player p = (Player) sender;
			p.getServer().broadcastMessage(ChatColor.AQUA + "Stopping Death Swap...");
			p.getServer().getScheduler().cancelTasks(this.plugin);
			return true;
		}
}

package deathswap;

import org.bukkit.plugin.java.JavaPlugin;

import deathswap.commands.EndSwap;
import deathswap.commands.Swap;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		new EndSwap(this);
		new Swap(this);
	}

}


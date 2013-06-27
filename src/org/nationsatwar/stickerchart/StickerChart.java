package org.nationsatwar.stickerchart;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.nationsatwar.stickerchart.commands.AddCommand;
import org.nationsatwar.stickerchart.commands.GetCommand;
import org.nationsatwar.stickerchart.commands.ReloadCommand;
import org.nationsatwar.stickerchart.commands.ResetCommand;
import org.nationsatwar.stickerchart.commands.SetCommand;
import org.nationsatwar.stickerchart.commands.SubCommand;
import org.nationsatwar.stickerchart.data.StickerChartDatabase;

public class StickerChart extends JavaPlugin {
	
	private StickerChartDatabase data = null;
	
	public void onEnable() {
		
		data = new StickerChartDatabase(this);
		new StickerChartEventListener(this);
		
		getCommand("repreload").setExecutor(new ReloadCommand(this));
		getCommand("repadd").setExecutor(new AddCommand(this));
		getCommand("repset").setExecutor(new SetCommand(this));
		getCommand("repsub").setExecutor(new SubCommand(this));
		getCommand("represet").setExecutor(new ResetCommand(this));
		getCommand("repget").setExecutor(new GetCommand(this));
	}
	
	public void onDisable() {
		
		this.data.save();
	}

	public StickerChartDatabase getDatasource() {
		
		return data;
	}
	
	public void sendMessage(CommandSender to, String string) {
		
		to.sendMessage(ChatColor.AQUA + "[" + this.getName() + "] " + ChatColor.RESET +string);
	}
	
	public void sendMessage(CommandSender to, String[] string) {
		
		to.sendMessage(ChatColor.AQUA + "[" + this.getName() + "] " + ChatColor.RESET + string);
	}
}

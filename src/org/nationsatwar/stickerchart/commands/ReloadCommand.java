package org.nationsatwar.stickerchart.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.nationsatwar.stickerchart.StickerChart;

public class ReloadCommand extends StickerChartCommandExecutor {
 
	public ReloadCommand(StickerChart stickerChart) {
		
		super(stickerChart);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!sender.isOp() && !sender.hasPermission("stickerchart.admin"))
			return false;
		
		if (plugin.getDatasource().reload()) {
			
			plugin.sendMessage(sender, ChatColor.GREEN + "Reloaded reputation.");
			return true;
		}

		plugin.sendMessage(sender, ChatColor.RED + "Failed to reload reputation.");
		return false;
	}
}

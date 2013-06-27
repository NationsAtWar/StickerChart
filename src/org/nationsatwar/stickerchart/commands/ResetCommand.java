package org.nationsatwar.stickerchart.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.nationsatwar.stickerchart.StickerChart;

public class ResetCommand extends StickerChartCommandExecutor {
 
	public ResetCommand(StickerChart stickerChart) {
		
		super(stickerChart);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!sender.isOp() && !sender.hasPermission("stickerchart.admin"))
			return false;
		
		if (args.length < 1) {
			
			plugin.sendMessage(sender, ChatColor.RED + "Specify a player name");
			return false;
		}
		
		OfflinePlayer player = null;
		String displayName = null;
		
		for (OfflinePlayer p : plugin.getServer().getOfflinePlayers()) {
			
			if(p.getName().equalsIgnoreCase(args[0])) {
				
				player = p;
				
				if(p.getPlayer() != null)
					displayName = p.getPlayer().getDisplayName();
				
				break;
			}
			
			if (p.getPlayer() != null) {
				
				if (p.getPlayer().getDisplayName().equalsIgnoreCase(args[0])) {
					
					player = p;
					displayName = p.getPlayer().getDisplayName();
					break;
				}
			}
		}

		if (player == null) {
			
			plugin.sendMessage(sender, ChatColor.RED + "Player " + args[0] + " doesn't exist.");
			return false;
		}
		
		plugin.getDatasource().set(player.getName(), displayName, 0);
		
		plugin.sendMessage(sender, ChatColor.GREEN + "Set " + args[0] + "'s reputation to 0.");
		
		return true;
	}
}

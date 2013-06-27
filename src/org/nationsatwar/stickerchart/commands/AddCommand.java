package org.nationsatwar.stickerchart.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.nationsatwar.stickerchart.StickerChart;

public class AddCommand extends StickerChartCommandExecutor {
 
	public AddCommand(StickerChart stickerChart) {
		
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
		
		if (args.length < 2) {
			
			plugin.sendMessage(sender, ChatColor.RED + "Specify a reputation amount");
			return false;
		}
		
		OfflinePlayer player = null;
		String displayName = null;
		
		for (OfflinePlayer p : plugin.getServer().getOfflinePlayers()) {
			
			if (p.getName().equalsIgnoreCase(args[0])) {
				
				player = p;
				
				if (p.getPlayer() != null)
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
		
		int amount;
		
		try { amount = Integer.parseInt(args[1]); }
		catch (NumberFormatException e) {
			
			plugin.sendMessage(sender, ChatColor.RED + "Invalid Amount");
			return false;
		}
		
		plugin.getDatasource().add(player.getName(), displayName, amount);
		
		plugin.sendMessage(sender, ChatColor.GREEN + "Added " + amount + " reputation to " + args[0]);
		
		return true;
	}
}

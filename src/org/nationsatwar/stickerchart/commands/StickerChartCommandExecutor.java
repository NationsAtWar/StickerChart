package org.nationsatwar.stickerchart.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.nationsatwar.stickerchart.StickerChart;

public class StickerChartCommandExecutor implements CommandExecutor {
	protected StickerChart plugin;

	protected StickerChartCommandExecutor(StickerChart stickerChart) {
		this.plugin = stickerChart;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		return false;
	}
}

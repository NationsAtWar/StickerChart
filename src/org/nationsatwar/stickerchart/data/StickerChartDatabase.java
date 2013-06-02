package org.nationsatwar.stickerchart.data;

import java.util.logging.Level;

import org.nationsatwar.stickerchart.StickerChart;

public class StickerChartDatabase {
	private StickerChart plugin = null;
	private StickerChartDataSource source = null;

	public StickerChartDatabase(StickerChart stickerChart) {
		this.plugin = stickerChart;
		StickerChartDatabaseType type = StickerChartDatabaseType.fromString(plugin.getConfig().getString("database"));
		switch (type) {
		   case FILE:
			   this.source = new StickerChartDatabaseFile(plugin);
			   this.source.reloadDatabase();
			   break;
		   default:
			   break;
		}
	}
	
	public boolean reload() {
		return this.source.reloadDatabase();
	}
	
	public boolean save() {
		return this.source.saveDatabase();
	}

	public boolean add(String playerName, String playerDisplayName, int amount) {
		if(this.source.exists(playerName)) {
			if(!this.source.getDisplayName(playerName).equals(playerDisplayName)) {
				if(!this.source.setDisplayName(playerName, playerDisplayName)) {
					this.plugin.getLogger().log(Level.SEVERE, "Unable to set new display name " + playerDisplayName + " for " + playerName);
					return false;
				}
			}
		}
		return this.source.setAmount(playerName, this.source.getAmount(playerName) + amount);
	}

	public boolean subtract(String playerName, String playerDisplayName, int amount) {
		if(this.source.exists(playerName)) {
			if(!this.source.getDisplayName(playerName).equals(playerDisplayName)) {
				if(!this.source.setDisplayName(playerName, playerDisplayName)) {
					this.plugin.getLogger().log(Level.SEVERE, "Unable to set new display name " + playerDisplayName + " for " + playerName);
					return false;
				}
			}
		}
		return this.source.setAmount(playerName, this.source.getAmount(playerName) - amount);
	}

	public boolean set(String playerName, String playerDisplayName, int amount) {
		if(this.source.exists(playerName)) {
			if(!this.source.getDisplayName(playerName).equals(playerDisplayName)) {
				if(!this.source.setDisplayName(playerName, playerDisplayName)) {
					this.plugin.getLogger().log(Level.SEVERE, "Unable to set new display name " + playerDisplayName + " for " + playerName);
					return false;
				}
			}
		}
		return this.source.setAmount(playerName, amount);
	}
	
	private enum StickerChartDatabaseType {
		FILE;

		public static StickerChartDatabaseType fromString(String text) {
			if (text != null) {
				for (StickerChartDatabaseType type : StickerChartDatabaseType.values()) {
					if (text.equalsIgnoreCase(type.name())) {
						return type;
					}
				}
			}
			throw new IllegalArgumentException("Invalid Type");
		}
	}

}

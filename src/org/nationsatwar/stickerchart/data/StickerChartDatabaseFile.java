package org.nationsatwar.stickerchart.data;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.nationsatwar.stickerchart.StickerChart;

public class StickerChartDatabaseFile extends StickerChartDataSource {
	private FileConfiguration storage = null;
	private File storageFile = null;
	
	public StickerChartDatabaseFile(StickerChart instance) {
		super(instance);
	}
	
	public boolean reloadDatabase() {
		if(storageFile == null) {
			try {
				storageFile = new File(plugin.getDataFolder(), "database.yml");
			} catch(Exception e) {
				plugin.getLogger().log(Level.SEVERE, "Could not open file " + plugin.getDataFolder()+ "/database.yml");
				return false;
			}
		}
		storage = YamlConfiguration.loadConfiguration(storageFile);
		return true;
	}
	
	private FileConfiguration getDatabase() {
		if(storage == null) {
			this.reloadDatabase();
		}
		return storage;
	}
	
	public boolean saveDatabase() {
		if(storage == null || storageFile == null) {
			return false;
		}
		try {
			getDatabase().save(storageFile);
			return true;
		} catch(IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save database to "+storageFile, e);
			return false;
		}
	}

	@Override
	public boolean exists(String playerName) {
		return this.getDatabase().contains(playerName);
	}

	@Override
	public int getAmount(String playerName) {
		if(!this.exists(playerName)) {
			return 0;
		}
		return this.getDatabase().getInt(playerName+".amount");
	}

	@Override
	public String getDisplayName(String playerName) {
		if(!this.exists(playerName)) {
			return null;
		}
		return this.getDatabase().getString(playerName+".displayname");
	}

	@Override
	public boolean setAmount(String playerName, int amount) {
		this.getDatabase().set(playerName+".amount", amount);
		if(this.getAmount(playerName) != amount) {
			return false;
		}
		return true;
	}

	@Override
	public boolean setDisplayName(String playerName, String playerDisplayName) {
		this.getDatabase().set(playerName+".playername", playerDisplayName);
		if(!this.getDisplayName(playerName).equals(playerDisplayName)) {
			return false;
		}
		return true;
	}
}

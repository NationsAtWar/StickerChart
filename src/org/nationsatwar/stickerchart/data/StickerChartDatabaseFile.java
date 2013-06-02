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
	
	public FileConfiguration getDatabase() {
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
}

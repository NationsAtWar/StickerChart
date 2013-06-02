package org.nationsatwar.stickerchart;

import org.bukkit.plugin.java.JavaPlugin;
import org.nationsatwar.stickerchart.data.StickerChartDatabase;

public class StickerChart extends JavaPlugin {
	private StickerChartDatabase data = null;
	
	public void onEnable() {
		this.data = new StickerChartDatabase(this);
		new StickerChartEventListener(this);
	}
	
	public void onDisable() {
		this.data.save();
	}

	public StickerChartDatabase getDatasource() {
		return this.data;
	}
}

package org.nationsatwar.stickerchart.data;

import org.nationsatwar.stickerchart.StickerChart;

public abstract class StickerChartDataSource {
	
	protected static StickerChart plugin = null;
	
	protected StickerChartDataSource(StickerChart instance) {
		
		StickerChartDataSource.plugin = instance;
	}
	
	public abstract boolean reloadDatabase();
	public abstract boolean saveDatabase();

	public abstract boolean exists(String playerName);
	
	public abstract int getAmount(String playerName);
	public abstract String getDisplayName(String playerName);
	
	public abstract boolean setAmount(String playerName, int amount);
	public abstract boolean setDisplayName(String playerName, String playerDisplayName);
}
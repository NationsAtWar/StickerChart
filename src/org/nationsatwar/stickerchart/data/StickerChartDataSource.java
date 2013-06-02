package org.nationsatwar.stickerchart.data;

import org.nationsatwar.stickerchart.StickerChart;

public abstract class StickerChartDataSource {
	protected static StickerChart plugin = null;
	
	protected StickerChartDataSource(StickerChart instance) {
		StickerChartDataSource.plugin = instance;
	}
	
	public abstract boolean reloadDatabase();
	public abstract boolean saveDatabase();

}

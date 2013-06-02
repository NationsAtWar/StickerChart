package org.nationsatwar.stickerchart;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class StickerChartEventListener implements Listener {
	private StickerChart plugin = null;
	
	public StickerChartEventListener(StickerChart stickerChart) {
		this.plugin = stickerChart;
	}

	@EventHandler
	public void onStickerChartEvent(StickerChartEvent event) {
		switch(event.getType()) {
		case INCREMENT:
			plugin.getDatasource().add(event.getPlayerName(), event.getPlayerDisplayName(), event.getAmount());
			break;
		case DECREMENT:
			plugin.getDatasource().subtract(event.getPlayerName(), event.getPlayerDisplayName(), event.getAmount());
			break;
		case RESET:
			plugin.getDatasource().set(event.getPlayerName(), event.getPlayerDisplayName(), 0);
			break;
		case SET:
			plugin.getDatasource().set(event.getPlayerName(), event.getPlayerDisplayName(), event.getAmount());
			break;
		default:
			break;
		}
	}
}

package org.nationsatwar.stickerchart;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StickerChartEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private String playerName;
	private String playerDisplayName;
	private StickerChartEventType type;
	private int amount;
	

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public StickerChartEvent(String eventPlayerName, String eventPlayerDisplayName, StickerChartEventType eventType, int eventAmount) {
		this.playerName = eventPlayerName;
		this.playerDisplayName = eventPlayerDisplayName;
		this.type = eventType;
		this.amount = eventAmount;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public String getPlayerDisplayName() {
		return playerDisplayName;
	}

	public StickerChartEventType getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public enum StickerChartEventType {
		INCREMENT, DECREMENT, RESET, SET
	}

}



package com.wowhead.database.constants;

import javafx.scene.paint.Color;

public enum ItemRarity
{
	POOR(0, Color.LIGHTGRAY, "Poor"),
	COMMON(1, Color.WHITE, "Common"),
	UNCOMMON(2, Color.LIMEGREEN, "Uncommon"),
	RARE(3, Color.BLUE, "Rare"),
	EPIC(4, Color.BLUEVIOLET, "Epic"),
	LEGENDARY(5, Color.DARKORANGE, "Legendary");
	
	private int code;
	private Color color;
	private String name;
	
	private ItemRarity(int code, Color color, String name)
	{
		this.code = code;
		this.color = color;
		this.name = name;
	}
	
	public static ItemRarity fromCode(int code)
	{
		return ItemRarity.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}

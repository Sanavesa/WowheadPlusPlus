package com.wowhead.database.constants;

public enum ItemRarity
{
	POOR(0),
	COMMON(1),
	UNCOMMON(2),
	RARE(3),
	EPIC(4),
	LEGENDARY(5);
	
	private int code;
	
	private ItemRarity(int code)
	{
		this.code = code;
	}
	
	public static ItemRarity fromCode(int code)
	{
		return ItemRarity.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

package com.wowhead.database.constants;

public enum Faction
{
	NEUTRAL(0),
	HORDE(1),
	ALLIANCE(2);
	
	private int code;
	
	private Faction(int code)
	{
		this.code = code;
	}
	
	public static Faction fromCode(int code)
	{
		return Faction.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

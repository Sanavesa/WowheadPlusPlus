package com.wowhead.database.constants;

public enum Faction
{
	NEUTRAL(0, "Neutral"),
	HORDE(1, "Horde"),
	ALLIANCE(2, "Alliance"),
	HOSTILE(3, "Hostile");
	
	private int code;
	private String name;
	
	private Faction(int code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public static Faction fromCode(int code)
	{
		return Faction.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}

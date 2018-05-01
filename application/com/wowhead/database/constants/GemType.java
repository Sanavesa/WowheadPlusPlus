package com.wowhead.database.constants;

public enum GemType
{
	RED(0),
	BLUE(1),
	YELLOW(2),
	ORANGE(3),
	GREEN(4),
	PURPLE(5),
	PRISMATIC(6),
	META(7);
	
	private int code;
	
	private GemType(int code)
	{
		this.code = code;
	}
	
	public static GemType fromCode(int code)
	{
		return GemType.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

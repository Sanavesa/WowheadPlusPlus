package com.wowhead.database.constants;

public enum GemType
{
	RED(0, "Red"),
	BLUE(1, "Blue"),
	YELLOW(2, "Yellow"),
	ORANGE(3, "Orange"),
	GREEN(4, "Green"),
	PURPLE(5, "Purple"),
	PRISMATIC(6, "Prismatic"),
	META(7, "Meta");
	
	private int code;
	private String name;
	
	private GemType(int code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public static GemType fromCode(int code)
	{
		return GemType.values()[code];
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

package com.wowhead.database.constants;

public enum ArmorType
{
	CLOTH(0, "Cloth"),
	LEATHER(1, "Leather"),
	MAIL(2, "Mail"),
	PLATE(3, "Plate");
	
	private int code;
	private String name;
	
	private ArmorType(int code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public static ArmorType fromCode(int code)
	{
		return ArmorType.values()[code];
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

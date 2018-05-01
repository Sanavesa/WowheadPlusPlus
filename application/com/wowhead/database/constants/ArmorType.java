package com.wowhead.database.constants;

public enum ArmorType
{
	CLOTH(0),
	LEATHER(1),
	MAIL(2),
	PLATE(3);
	
	private int code;
	
	private ArmorType(int code)
	{
		this.code = code;
	}
	
	public static ArmorType fromCode(int code)
	{
		return ArmorType.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

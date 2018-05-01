package com.wowhead.database.constants;

public enum MagicSchool
{
	PHYSICAL(0),
	ARCANE(1),
	FIRE(2),
	FROST(3),
	NATURE(4),
	SHADOW(5),
	HOLY(6);
	
	private int code;
	
	private MagicSchool(int code)
	{
		this.code = code;
	}
	
	public static MagicSchool fromCode(int code)
	{
		return MagicSchool.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

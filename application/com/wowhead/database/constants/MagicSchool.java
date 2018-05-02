package com.wowhead.database.constants;

public enum MagicSchool
{
	PHYSICAL(0, "Physical"),
	ARCANE(1, "Arcane"),
	FIRE(2, "Fire"),
	FROST(3, "Frost"),
	NATURE(4, "Nature"),
	SHADOW(5, "Shadow"),
	HOLY(6, "Holy");
	
	private int code;
	private String name;
	
	private MagicSchool(int code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public static MagicSchool fromCode(int code)
	{
		return MagicSchool.values()[code];
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

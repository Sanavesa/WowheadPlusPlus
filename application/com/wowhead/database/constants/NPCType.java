package com.wowhead.database.constants;

public enum NPCType
{
	VENDOR(0, "Vendor"),
	FLIGHT_MASTER(1, "Flight Master"),
	QUEST_GIVER(2, "Quest Giver"),
	TRAINER(3, "Trainer"),
	GUARD(4, "Guard"),
	INNKEEPER(5, "Innkeeper"),
	MOB(6, "Mob");
	
	private int code;
	private String name;
	
	private NPCType(int code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public static NPCType fromCode(int code)
	{
		return NPCType.values()[code];
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

package com.wowhead.database.constants;

public enum NPCType
{
	VENDOR(0),
	FLIGHT_MASTER(1),
	QUEST_GIVER(2),
	TRAINER(3),
	GUARD(4),
	INNKEEPER(5),
	MOB(6);
	
	private int code;
	
	private NPCType(int code)
	{
		this.code = code;
	}
	
	public static NPCType fromCode(int code)
	{
		return NPCType.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

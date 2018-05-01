package com.wowhead.database.tables;

import com.wowhead.database.constants.Faction;
import com.wowhead.database.constants.NPCType;

public class NPC
{
	private int id;
	private String name;
	private Faction faction;
	private NPCType npcType;
	private int health;

	public NPC(int id, String name, Faction faction, NPCType npcType, int health)
	{
		this.id = id;
		this.name = name;
		this.faction = faction;
		this.npcType = npcType;
		this.health = health;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Faction getFaction()
	{
		return faction;
	}

	public void setFaction(Faction faction)
	{
		this.faction = faction;
	}

	public NPCType getNpcType()
	{
		return npcType;
	}

	public void setNpcType(NPCType npcType)
	{
		this.npcType = npcType;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}
}
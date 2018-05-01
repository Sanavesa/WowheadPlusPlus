package com.wowhead.database.tables;

import com.wowhead.database.constants.GemType;
import com.wowhead.database.constants.ItemRarity;

public class Gem
{
	private int id;
	private String name;
	private ItemRarity rarity;
	private String description;
	private int levelReq;
	private GemType gemType;
	private int stamina, strength, spirit, intellect, agility;
	
	public Gem(int id, String name, ItemRarity rarity, String description, int levelReq, GemType gemType, int stamina,
			int strength, int spirit, int intellect, int agility)
	{
		super();
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.description = description;
		this.levelReq = levelReq;
		this.gemType = gemType;
		this.stamina = stamina;
		this.strength = strength;
		this.spirit = spirit;
		this.intellect = intellect;
		this.agility = agility;
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

	public ItemRarity getRarity()
	{
		return rarity;
	}

	public void setRarity(ItemRarity rarity)
	{
		this.rarity = rarity;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getLevelReq()
	{
		return levelReq;
	}

	public void setLevelReq(int levelReq)
	{
		this.levelReq = levelReq;
	}

	public GemType getGemType()
	{
		return gemType;
	}

	public void setGemType(GemType gemType)
	{
		this.gemType = gemType;
	}

	public int getStamina()
	{
		return stamina;
	}

	public void setStamina(int stamina)
	{
		this.stamina = stamina;
	}

	public int getStrength()
	{
		return strength;
	}

	public void setStrength(int strength)
	{
		this.strength = strength;
	}

	public int getSpirit()
	{
		return spirit;
	}

	public void setSpirit(int spirit)
	{
		this.spirit = spirit;
	}

	public int getIntellect()
	{
		return intellect;
	}

	public void setIntellect(int intellect)
	{
		this.intellect = intellect;
	}

	public int getAgility()
	{
		return agility;
	}

	public void setAgility(int agility)
	{
		this.agility = agility;
	}
}
package com.wowhead.database.tables;

import com.wowhead.database.constants.ItemRarity;

public class QuestItem
{
	private int id;
	private String name;
	private ItemRarity rarity;
	private String description;
	private int levelReq;
	private String questName;

	public QuestItem(int id, String name, ItemRarity rarity, String description, int levelReq, String questName)
	{
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.description = description;
		this.levelReq = levelReq;
		this.questName = questName;
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

	public String getQuestName()
	{
		return questName;
	}

	public void setQuestName(String questName)
	{
		this.questName = questName;
	}
}
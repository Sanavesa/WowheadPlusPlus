package com.wowhead.database.tables;

public class Quest
{
	private int id;
	private String name;
	private String description;
	private String questName;

	public Quest(int id, String name, String description, String questName)
	{
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
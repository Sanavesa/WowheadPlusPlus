package com.wowhead.database.tables;

public class Quest
{
	private int id;
	private String name;
	private String description;
	private String npcName;

	public Quest(int id, String name, String description, String npcName)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.npcName = npcName;
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

	public String getNpcName()
	{
		return npcName;
	}

	public void setNpcName(String npcName)
	{
		this.npcName = npcName;
	}
}
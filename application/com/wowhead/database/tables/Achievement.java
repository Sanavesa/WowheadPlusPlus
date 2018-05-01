package com.wowhead.database.tables;

public class Achievement
{
	private int id;
	private String name;
	private int pointReward;
	
	public Achievement(int id, String name, int pointReward)
	{
		this.id = id;
		this.name = name;
		this.pointReward = pointReward;
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

	public int getPointReward()
	{
		return pointReward;
	}

	public void setPointReward(int pointReward)
	{
		this.pointReward = pointReward;
	}
}
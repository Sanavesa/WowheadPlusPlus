package com.wowhead.database.tables;

import com.wowhead.database.constants.MagicSchool;

public class Spell
{
	private int id;
	private String name;
	private int range;
	private double duration;
	private String description;
	private MagicSchool magicSchool;
	
	public Spell(int id, String name, int range, double duration, String description, MagicSchool magicSchool)
	{
		this.id = id;
		this.name = name;
		this.range = range;
		this.duration = duration;
		this.description = description;
		this.magicSchool = magicSchool;
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

	public int getRange()
	{
		return range;
	}

	public void setRange(int range)
	{
		this.range = range;
	}

	public double getDuration()
	{
		return duration;
	}

	public void setDuration(double duration)
	{
		this.duration = duration;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public MagicSchool getMagicSchool()
	{
		return magicSchool;
	}

	public void setMagicSchool(MagicSchool magicSchool)
	{
		this.magicSchool = magicSchool;
	}
}
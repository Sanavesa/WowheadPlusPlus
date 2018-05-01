package com.wowhead.database.tables;

import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.constants.WeaponType;

public class Weapon
{
	private int id;
	private String name;
	private ItemRarity rarity;
	private String description;
	private int levelReq;
	private WeaponType weaponType;
	private double attackSpeed, attackDamage;
	private int stamina, strength, spirit, intellect, agility;

	public Weapon(int id, String name, ItemRarity rarity, String description, int levelReq, WeaponType weaponType,
			double attackSpeed, double attackDamage, int stamina, int strength, int spirit, int intellect, int agility)
	{
		this.id = id;
		this.name = name;
		this.rarity = rarity;
		this.description = description;
		this.levelReq = levelReq;
		this.weaponType = weaponType;
		this.attackSpeed = attackSpeed;
		this.attackDamage = attackDamage;
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

	public WeaponType getWeaponType()
	{
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType)
	{
		this.weaponType = weaponType;
	}

	public double getAttackSpeed()
	{
		return attackSpeed;
	}

	public void setAttackSpeed(double attackSpeed)
	{
		this.attackSpeed = attackSpeed;
	}

	public double getAttackDamage()
	{
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage)
	{
		this.attackDamage = attackDamage;
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

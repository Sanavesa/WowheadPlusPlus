package com.wowhead.database.constants;

public enum WeaponType
{
	FIST_WEAPON(0, "Fist Weapon"),
	DAGGER(1, "Dagger"),
	ONE_AXE(2, "One-Hand Axe"),
	TWO_AXE(3, "Two-Hand Axe"),
	ONE_MACE(4, "One-Hand Mace"),
	TWO_MACE(5, "Two-Hand Mace"),
	ONE_SWORD(6, "One-Hand Sword"),
	TWO_SWORD(7, "Two-Hand Sword"),
	POLEARM(8, "Polearm"),
	STAVE(9, "Stave"),
	THROWN(10, "Thrown"),
	FISHING_POLE(11, "Fishing Pole"),
	GUN(12, "Gun"),
	BOW(13, "Bow"),
	CROSSBOW(14, "Crossbow"),
	WAND(15, "Wand"),
	SHIELD(16, "Shield"),
	RELIC(17, "Relic"),
	TOTEM(18, "Totem"),
	LIBRAM(19, "Libram");
	
	private int code;
	private String name;
	
	private WeaponType(int code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public static WeaponType fromCode(int code)
	{
		return WeaponType.values()[code];
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

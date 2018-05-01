package com.wowhead.database.constants;

public enum WeaponType
{
	FIST_WEAPON(0),
	DAGGER(1),
	ONE_AXE(2),
	TWO_AXE(3),
	ONE_MACE(4),
	TWO_MACE(5),
	ONE_SWORD(6),
	TWO_SWORD(7),
	POLEARM(8),
	STAVE(9),
	THROWN(10),
	FISHING_POLE(11),
	GUN(12),
	BOW(13),
	CROSSBOW(14),
	WAND(15),
	SHIELD(16),
	RELIC(17),
	TOTEM(18),
	LIBRAM(19);
	
	private int code;
	
	private WeaponType(int code)
	{
		this.code = code;
	}
	
	public static WeaponType fromCode(int code)
	{
		return WeaponType.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

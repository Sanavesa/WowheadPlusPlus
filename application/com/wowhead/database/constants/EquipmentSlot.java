package com.wowhead.database.constants;

public enum EquipmentSlot
{
	AMMO(0),
	HEAD(1),
	NECK(2),
	SHOULDER(3),
	SHIRT(4),
	CHEST(5),
	BELT(6),
	LEGS(7),
	FEET(8),
	WRIST(9),
	GLOVES(10),
	FINGER_1(11),
	FINGER_2(12),
	TRINKET_1(13),
	TRINKET_2(14),
	BACK(15),
	MAIN_HAND(16),
	OFF_HAND(17),
	RANGED(18),
	TABARD(19);
	
	private int code;
	
	private EquipmentSlot(int code)
	{
		this.code = code;
	}
	
	public static EquipmentSlot fromCode(int code)
	{
		return EquipmentSlot.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}

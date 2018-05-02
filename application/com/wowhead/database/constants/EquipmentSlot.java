package com.wowhead.database.constants;

public enum EquipmentSlot
{
	AMMO(0, "Ammo"),
	HEAD(1, "Head"),
	NECK(2, "Neck"),
	SHOULDER(3, "Shoulder"),
	SHIRT(4, "Shirt"),
	CHEST(5, "Chest"),
	BELT(6, "Belt"),
	LEGS(7, "Legs"),
	FEET(8, "Feet"),
	WRIST(9, "Wrist"),
	GLOVES(10, "Gloves"),
	FINGER_1(11, "Ring 1"),
	FINGER_2(12, "Ring 2"),
	TRINKET_1(13, "Trinket 1"),
	TRINKET_2(14, "Trinket 2"),
	BACK(15, "Back"),
	MAIN_HAND(16, "Main-Hand"),
	OFF_HAND(17, "Off-Hand"),
	RANGED(18, "Ranged"),
	TABARD(19, "Tabard");
	
	private int code;
	private String name;
	
	private EquipmentSlot(int code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public static EquipmentSlot fromCode(int code)
	{
		return EquipmentSlot.values()[code];
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

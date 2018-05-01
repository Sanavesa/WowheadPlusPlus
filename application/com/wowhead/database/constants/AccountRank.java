package com.wowhead.database.constants;

public enum AccountRank
{
	LOGGED_OUT(-1),
	USER(0),
	MODERATOR(1);
	
	private int code;
	
	private AccountRank(int code)
	{
		this.code = code;
	}
	
	public static AccountRank fromCode(int code)
	{
		return AccountRank.values()[code];
	}
	
	public int getCode()
	{
		return code;
	}
}
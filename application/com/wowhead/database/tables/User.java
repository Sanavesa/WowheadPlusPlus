package com.wowhead.database.tables;

import com.wowhead.database.constants.AccountRank;

public class User
{
	private int id;
	private String username;
	private String password;
	private AccountRank accountRank;
	
	public User(int id, String username, String password, AccountRank accountRank)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.accountRank = accountRank;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public AccountRank getAccountRank()
	{
		return accountRank;
	}

	public void setAccountRank(AccountRank accountRank)
	{
		this.accountRank = accountRank;
	}
}
package com.wowhead.database.tables;

public class Comment
{
	private int id;
	private int userID;
	private String text;
	
	public Comment(int id, int userID, String text)
	{
		this.id = id;
		this.userID = userID;
		this.text = text;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUserID()
	{
		return userID;
	}

	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}
}

package com.wowhead.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
	private Connection connection = null;
	
	public Database(String url, String username, String password)
	{
		// VERY simplistic connection method
		try
		{
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Successfully connected to the database!");
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Failed to connect to the database!");
			System.err.println(e.getMessage());
		}
	}
}

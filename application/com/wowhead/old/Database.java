package com.wowhead.old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
	private Connection connection = null;
	
	public Database(String url, String username, String password)
	{
		try
		{
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Successfully connected to the database!");
		}
		catch (SQLException e)
		{
			System.out.println("Failed to connect to the database!");
			System.err.println(e.getMessage());
		}
	}
	
	public void disconnect()
	{
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addWeapon(String name, int rarityID, String description, int levelReq, int weaponTypeID, 
			double attackSpeed, double attackDamage, int stamina, int strength, int spirit, int intellect, 
			int agility)
	{
		try
		{
			connection.setAutoCommit(false);
			
			
			// add to items
			String sqlString1 = "INSERT INTO Item"
					+ "(name, rarityID, description, levelReq)"
					+ "VALUES"
					+ "(?, ?, ?, ?);";
			PreparedStatement ps1 = connection.prepareStatement(sqlString1, Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, name);
			ps1.setInt(2, rarityID);
			ps1.setString(3, description);
			ps1.setInt(4, levelReq);
			
			int rowsAffected = ps1.executeUpdate();
			if(rowsAffected != 1)
				System.out.println("ALERT: Insertion failed at Item!");
			
			ResultSet rs = ps1.getGeneratedKeys();
			int generatedKey = -1;
			if(rs.next())
				generatedKey = rs.getInt(1);
			
			// add to weapon
			String sqlString2 = "INSERT INTO Weapon"
					+ "(id, name, rarityID, description, levelReq, weaponTypeID, attackSpeed, attackDamage, stamina, strength, spirit, intellect, agility)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps2 = connection.prepareStatement(sqlString2);
			ps2.setInt(1, generatedKey);
			ps2.setString(2, name);
			ps2.setInt(3, rarityID);
			ps2.setString(4, description);
			ps2.setInt(5, levelReq);
			ps2.setInt(6, weaponTypeID);
			ps2.setDouble(7, attackSpeed);
			ps2.setDouble(8, attackDamage);
			ps2.setInt(9, stamina);
			ps2.setInt(10, strength);
			ps2.setInt(11, spirit);
			ps2.setInt(12, intellect);
			ps2.setInt(13, agility);
			
			rowsAffected = ps2.executeUpdate();
			if(rowsAffected != 1)
				System.out.println("ALERT: Insertion failed at Weapon!");
			
			connection.commit();
			
			System.out.println("commit good");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			try
			{
				connection.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			
			System.out.println("rollback");
		}
		finally
		{
			try
			{
				connection.setAutoCommit(true);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void addArmor(String name, int rarityID, String description, int levelReq, int armorTypeID, 
			int equipmentSlotID, int stamina, int strength, int spirit, int intellect, int agility)
	{
		try
		{
			connection.setAutoCommit(false);
			
			
			// add to items
			String sqlString1 = "INSERT INTO Item"
					+ "(name, rarityID, description, levelReq)"
					+ "VALUES"
					+ "(?, ?, ?, ?);";
			PreparedStatement ps1 = connection.prepareStatement(sqlString1, Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, name);
			ps1.setInt(2, rarityID);
			ps1.setString(3, description);
			ps1.setInt(4, levelReq);
			
			int rowsAffected = ps1.executeUpdate();
			if(rowsAffected != 1)
				System.out.println("ALERT: Insertion failed at Item!");
			
			ResultSet rs = ps1.getGeneratedKeys();
			int generatedKey = -1;
			if(rs.next())
				generatedKey = rs.getInt(1);
			
			// add to weapon
			String sqlString2 = "INSERT INTO Armor"
					+ "(id, name, rarityID, description, levelReq, armorTypeID, equipmentSlotID, stamina, strength, spirit, intellect, agility)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps2 = connection.prepareStatement(sqlString2);
			ps2.setInt(1, generatedKey);
			ps2.setString(2, name);
			ps2.setInt(3, rarityID);
			ps2.setString(4, description);
			ps2.setInt(5, levelReq);
			ps2.setInt(6, armorTypeID);
			ps2.setInt(7, equipmentSlotID);
			ps2.setInt(8, stamina);
			ps2.setInt(9, strength);
			ps2.setInt(10, spirit);
			ps2.setInt(11, intellect);
			ps2.setInt(12, agility);
			
			rowsAffected = ps2.executeUpdate();
			if(rowsAffected != 1)
				System.out.println("ALERT: Insertion failed at Armor!");
			
			connection.commit();
			
			System.out.println("commit good");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			
			try
			{
				connection.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			
			System.out.println("rollback");
		}
		finally
		{
			try
			{
				connection.setAutoCommit(true);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void lookupFaction(int factionID)
	{
		String sql = "SELECT name FROM Faction WHERE id=?;";
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setInt(1, factionID);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				System.out.println("Faction " + rs.getString("name") + " has an index of " + factionID);
			}
			else
			{
				System.out.println("No faction match for index of " + factionID);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

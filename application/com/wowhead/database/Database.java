package com.wowhead.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wowhead.constant.Constants;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.ArmorType;
import com.wowhead.database.constants.EquipmentSlot;
import com.wowhead.database.constants.Faction;
import com.wowhead.database.constants.GemType;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.constants.MagicSchool;
import com.wowhead.database.constants.NPCType;
import com.wowhead.database.constants.WeaponType;
import com.wowhead.database.tables.Achievement;
import com.wowhead.database.tables.Armor;
import com.wowhead.database.tables.Elixir;
import com.wowhead.database.tables.Food;
import com.wowhead.database.tables.Gem;
import com.wowhead.database.tables.NPC;
import com.wowhead.database.tables.Quest;
import com.wowhead.database.tables.QuestItem;
import com.wowhead.database.tables.Spell;
import com.wowhead.database.tables.Weapon;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class Database
{
	private Connection connection = null;
	private static Database database = null;
	private ReadOnlyObjectWrapper<AccountRank> accountRank = new ReadOnlyObjectWrapper<AccountRank>(AccountRank.LOGGED_OUT);
	
	public static Database getInstance()
	{
		if(database == null)
		{
			database = new Database(Constants.DATABASE_URL, Constants.DATABASE_USERNAME, Constants.DATABASE_PASSWORD);
		}
		
		return database;
	}
	
	private Database(String url, String username, String password)
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
	
	public boolean login(String username, String password)
	{
		if(accountRank.getValue() != AccountRank.LOGGED_OUT)
		{
			return false;
		}
		
		String sql = "SELECT accountRankID FROM User WHERE username=? AND password=?;";
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				System.out.println("Successful login: " + username + " " + password);
				switch(rs.getInt(1))
				{
				case 0:
					accountRank.setValue(AccountRank.USER);
					break;
				case 1:
					accountRank.setValue(AccountRank.MODERATOR);
					break;
				}
				return true;
			}
			else
			{
				System.out.println("Failure login: " + username + " " + password);
				return false;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Failure login: " + username + " " + password);
			return false;
		}
	}
	
	public boolean register(String username, String password)
	{
		if(accountRank.getValue() != AccountRank.LOGGED_OUT)
		{
			return false;
		}
		
		// Check if username exists
		String sql = "SELECT accountRankID FROM User WHERE username=?;";
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) // account exists
			{
				System.out.println("Failure registration: " + username + " account exists");
				return false;
			}
			else // account doesnt exist -> create it
			{
				connection.setAutoCommit(false);
				// add new user
				String sql2 = "INSERT INTO User"
						+ "(username, password, accountRankID)"
						+ "VALUES"
						+ "(?, ?, ?);";
				PreparedStatement ps1 = connection.prepareStatement(sql2);
				ps1.setString(1, username);
				ps1.setString(2, password);
				ps1.setInt(3, 0); // user by default
				
				int rowsAffected = ps1.executeUpdate();
				if(rowsAffected != 1)
				{
					System.out.println("Failure registration: " + username + " " + password);
					connection.rollback();
					return false;
				}
				else
				{
					System.out.println("Successful registration: " + username + " " + password);
					connection.commit();
					accountRank.setValue(AccountRank.USER);
					return true;
				}
				
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Failure registration: " + username + " " + password);
			
			try
			{
				connection.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			
			System.out.println("rollback");
			return false;
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
	
	public void logout()
	{
		accountRank.setValue(AccountRank.LOGGED_OUT);
	}
	
	public AccountRank getAccountRank()
	{
		return accountRank.getValue();
	}
	
	public ReadOnlyObjectProperty<AccountRank> accountRankProperty()
	{
		return accountRank.getReadOnlyProperty();
	}

	public Weapon[] searchWeapon(String weaponName)
	{
		String sql = "SELECT * FROM Item,Weapon WHERE name LIKE ? AND Item.id = Weapon.id;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + weaponName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Weapon> weapons = new ArrayList<>();
			while(rs.next())
			{
				Weapon wep = new Weapon(
						rs.getInt("Item.id"), 
						rs.getString("Item.name"), 
						ItemRarity.fromCode(rs.getInt("Item.rarityID")), 
						rs.getString("Item.description"),
						rs.getInt("Item.levelReq"),
						WeaponType.fromCode(rs.getInt("Weapon.weaponTypeID")),
						rs.getDouble("Weapon.attackSpeed"),
						rs.getDouble("Weapon.attackDamage"),
						rs.getInt("Weapon.stamina"),
						rs.getInt("Weapon.strength"),
						rs.getInt("Weapon.spirit"),
						rs.getInt("Weapon.intellect"),
						rs.getInt("Weapon.agility"));
				weapons.add(wep);
			}
			
			return weapons.toArray(new Weapon[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Weapon[0];
		}
	}
	
	public Armor[] searchArmor(String armorName)
	{
		String sql = "SELECT * FROM Item,Armor WHERE name LIKE ? AND Item.id = Armor.id;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + armorName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Armor> armors = new ArrayList<>();
			while(rs.next())
			{
				Armor armor = new Armor(
						rs.getInt("Item.id"), 
						rs.getString("Item.name"), 
						ItemRarity.fromCode(rs.getInt("Item.rarityID")), 
						rs.getString("Item.description"),
						rs.getInt("Item.levelReq"),
						ArmorType.fromCode(rs.getInt("Armor.armorTypeID")),
						EquipmentSlot.fromCode(rs.getInt("Armor.equipmentSlotID")),
						rs.getInt("Armor.stamina"),
						rs.getInt("Armor.strength"),
						rs.getInt("Armor.spirit"),
						rs.getInt("Armor.intellect"),
						rs.getInt("Armor.agility"));
				armors.add(armor);
			}
			
			return armors.toArray(new Armor[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Armor[0];
		}
	}
	
	public Gem[] searchGem(String gemName)
	{
		String sql = "SELECT * FROM Item,Gem WHERE name LIKE ? AND Item.id = Gem.id;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + gemName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Gem> gems = new ArrayList<>();
			while(rs.next())
			{
				Gem gem = new Gem(
						rs.getInt("Item.id"), 
						rs.getString("Item.name"), 
						ItemRarity.fromCode(rs.getInt("Item.rarityID")), 
						rs.getString("Item.description"),
						rs.getInt("Item.levelReq"),
						GemType.fromCode(rs.getInt("Gem.gemTypeID")),
						rs.getInt("Gem.stamina"),
						rs.getInt("Gem.strength"),
						rs.getInt("Gem.spirit"),
						rs.getInt("Gem.intellect"),
						rs.getInt("Gem.agility"));
				gems.add(gem);
			}
			
			return gems.toArray(new Gem[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Gem[0];
		}
	}
	
	public Food[] searchFood(String foodName)
	{
		String sql = "SELECT * FROM Item,Food WHERE name LIKE ? AND Item.id = Food.id;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + foodName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Food> foods = new ArrayList<>();
			while(rs.next())
			{
				Food food = new Food(
						rs.getInt("Item.id"), 
						rs.getString("Item.name"), 
						ItemRarity.fromCode(rs.getInt("Item.rarityID")), 
						rs.getString("Item.description"),
						rs.getInt("Item.levelReq"),
						rs.getDouble("Food.regenSpeed"));
				foods.add(food);
			}
			
			return foods.toArray(new Food[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Food[0];
		}
	}
	
	public Elixir[] searchElixir(String elixirName)
	{
		String sql = "SELECT * FROM Item,Elixir WHERE name LIKE ? AND Item.id = Elixir.id;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + elixirName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Elixir> elixirs = new ArrayList<>();
			while(rs.next())
			{
				Elixir elixir = new Elixir(
						rs.getInt("Item.id"), 
						rs.getString("Item.name"), 
						ItemRarity.fromCode(rs.getInt("Item.rarityID")), 
						rs.getString("Item.description"),
						rs.getInt("Item.levelReq"),
						rs.getDouble("Elixir.duration"),
						rs.getInt("Elixir.stamina"),
						rs.getInt("Elixir.strength"),
						rs.getInt("Elixir.spirit"),
						rs.getInt("Elixir.intellect"),
						rs.getInt("Elixir.agility"));
				elixirs.add(elixir);
			}
			
			return elixirs.toArray(new Elixir[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Elixir[0];
		}
	}
	
	public QuestItem[] searchQuestItem(String questItemName)
	{
		String sql = "SELECT * FROM Item,QuestItem,NPC WHERE name LIKE ? AND Item.id = QuestItem.id AND NPC.id = QuestItem.usedForQuestID;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + questItemName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<QuestItem> questItems = new ArrayList<>();
			while(rs.next())
			{
				QuestItem questItem = new QuestItem(
						rs.getInt("Item.id"), 
						rs.getString("Item.name"), 
						ItemRarity.fromCode(rs.getInt("Item.rarityID")), 
						rs.getString("Item.description"),
						rs.getInt("Item.levelReq"),
						rs.getString("NPC.name"));
				questItems.add(questItem);
			}
			
			return questItems.toArray(new QuestItem[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new QuestItem[0];
		}
	}
	
	public Spell[] searchSpell(String spellName)
	{
		String sql = "SELECT * FROM Spell WHERE name LIKE ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + spellName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Spell> spells = new ArrayList<>();
			while(rs.next())
			{
				Spell spell = new Spell(
						rs.getInt("Spell.id"), 
						rs.getString("Spell.name"),
						rs.getInt("Spell.range"),
						rs.getDouble("Spell.duration"),
						rs.getString("Spell.description"),
						MagicSchool.fromCode(rs.getInt("Spell.magicSchoolID")));
				spells.add(spell);
			}
			
			return spells.toArray(new Spell[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Spell[0];
		}
	}
	
	public NPC[] searchNPC(String npcName)
	{
		String sql = "SELECT * FROM NPC WHERE name LIKE ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + npcName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<NPC> npcs = new ArrayList<>();
			while(rs.next())
			{
				NPC npc = new NPC(
						rs.getInt("NPC.id"), 
						rs.getString("NPC.name"),
						Faction.fromCode(rs.getInt("NPC.factionID")),
						NPCType.fromCode(rs.getInt("NPC.npcTypeID")),
						rs.getInt("NPC.health"));
				npcs.add(npc);
			}
			
			return npcs.toArray(new NPC[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new NPC[0];
		}
	}
	
	public Quest[] searchQuest(String questName)
	{
		String sql = "SELECT * FROM Quest,NPC WHERE Quest.name LIKE ? AND Quest.questGiverNPCID = NPC.id;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + questName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Quest> quests = new ArrayList<>();
			while(rs.next())
			{
				Quest quest = new Quest(
						rs.getInt("Quest.id"), 
						rs.getString("Quest.name"), 
						rs.getString("Quest.description"),
						rs.getString("NPC.name"));
				quests.add(quest);
			}
			
			return quests.toArray(new Quest[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Quest[0];
		}
	}
	
	public Achievement[] searchAchievement(String achievementName)
	{
		String sql = "SELECT * FROM Achievement WHERE name LIKE ?;";
		
		try(PreparedStatement ps = connection.prepareStatement(sql))
		{
			ps.setString(1, "%" + achievementName + "%");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Achievement> achievements = new ArrayList<>();
			while(rs.next())
			{
				Achievement achievement = new Achievement(
						rs.getInt("Achievement.id"), 
						rs.getString("Achievement.name"),
						rs.getInt("Achievement.pointReward"));
				achievements.add(achievement);
			}
			
			return achievements.toArray(new Achievement[0]);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return new Achievement[0];
		}
	}
	
	public void deleteEntry(int iD, String tableName)
	{
		
	}
}

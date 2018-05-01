package com.wowhead.constant;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.scene.image.Image;

public final class Resources
{
	// Private constructor to prevent instantiation
	private Resources() {}
	
	private static ReadOnlyDoubleWrapper loadProgress = new ReadOnlyDoubleWrapper(0);
	public static ReadOnlyDoubleProperty loadProgressProperty()
	{
		return loadProgress.getReadOnlyProperty();
	}
	
	private static int numOfImages = 12;
	public static final Image IMG_WOW_LOGO;
	public static final Image IMG_WOWHEAD_LOGO;
	public static final Image IMG_SEARCH;
	public static final Image IMG_ICON_WEAPON;
	public static final Image IMG_ICON_ARMOR;
	public static final Image IMG_ICON_QUEST;
	public static final Image IMG_ICON_ACHIEVEMENT;
	public static final Image IMG_ICON_NPC;
	public static final Image IMG_ICON_GEM;
	public static final Image IMG_ICON_ELIXIR;
	public static final Image IMG_ICON_FOOD;
	public static final Image IMG_ICON_QUEST_ITEM;
	public static final Image IMG_ICON_SPELL;
	
	static
	{
		IMG_WOW_LOGO = new Image("res/wow_logo.png", true);
		IMG_WOW_LOGO.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_WOWHEAD_LOGO = new Image("res/wowhead_logo2.png", true);
		IMG_WOWHEAD_LOGO.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_SEARCH = new Image("res/search.png");
		IMG_SEARCH.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_WEAPON = new Image("res/icon_weapon.jpg", true);
		IMG_ICON_WEAPON.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_ARMOR = new Image("res/icon_armor.jpg", true);
		IMG_ICON_ARMOR.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_QUEST = new Image("res/icon_quest.jpg", true);
		IMG_ICON_QUEST.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_ACHIEVEMENT = new Image("res/icon_achievement.jpg", true);
		IMG_ICON_ACHIEVEMENT.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_NPC = new Image("res/icon_npc.jpg", true);
		IMG_ICON_NPC.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_ELIXIR = new Image("res/icon_elixir.jpg", true);
		IMG_ICON_ELIXIR.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_FOOD = new Image("res/icon_food.jpg", true);
		IMG_ICON_FOOD.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_GEM = new Image("res/icon_gem.jpg", true);
		IMG_ICON_GEM.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_QUEST_ITEM= new Image("res/icon_questitem.jpg", true);
		IMG_ICON_QUEST_ITEM.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
		
		IMG_ICON_SPELL = new Image("res/icon_spell.jpg", true);
		IMG_ICON_SPELL.progressProperty().addListener((args, oldValue, newValue) ->
		{
			double difference = newValue.doubleValue() - oldValue.doubleValue();
			loadProgress.set(loadProgress.get() + difference/numOfImages);
		});
	}
}

package com.wowhead.gui.page.view;

import java.util.HashMap;

import com.wowhead.constant.Resources;
import com.wowhead.core.DatabaseType;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;

public class DatabaseSelectionView
{
	private Button btnWeapon, btnArmor, btnNPC, btnQuest, btnAchievement;
	private ToolBar toolBarDatabaseSelection;
	
	
	private ReadOnlyObjectWrapper<DatabaseType> selectedDatabase = new ReadOnlyObjectWrapper<DatabaseType>();
	private final int imageWidth = 72;
	private final int buttonWidth = 120;
	private final Insets padding = new Insets(10);
	private final Glow glowEffect = new Glow(0.4f);
	private final String selectedStyleClass = "databaseSelectionButton-selected";
	private final String unselectedStyleClass = "databaseSelectionButton";
	private HashMap<DatabaseType, Button> buttonDatabaseHashmap = new HashMap();
	
	public DatabaseSelectionView()
	{
		ImageView imgWeapon = new ImageView(Resources.IMG_ICON_WEAPON);
		ImageView imgArmor= new ImageView(Resources.IMG_ICON_ARMOR);
		ImageView imgNPC = new ImageView(Resources.IMG_ICON_NPC);
		ImageView imgQuest = new ImageView(Resources.IMG_ICON_QUEST);
		ImageView imgAchievement = new ImageView(Resources.IMG_ICON_ACHIEVEMENT);
		
		setupImageView(imgWeapon);
		setupImageView(imgArmor);		
		setupImageView(imgNPC);
		setupImageView(imgQuest);
		setupImageView(imgAchievement);
		
		btnWeapon = new Button("Weapon DB", imgWeapon);
		btnArmor= new Button("Armor DB", imgArmor);
		btnNPC = new Button("NPC DB", imgNPC);
		btnQuest = new Button("Quest DB", imgQuest);
		btnAchievement= new Button("Achievement DB", imgAchievement);
		
		setupButton(btnWeapon, DatabaseType.WEAPON);
		setupButton(btnArmor, DatabaseType.ARMOR);
		setupButton(btnNPC, DatabaseType.NPC);
		setupButton(btnQuest, DatabaseType.QUEST);
		setupButton(btnAchievement, DatabaseType.ACHIEVEMENT);
		
		toolBarDatabaseSelection = new ToolBar(btnWeapon, btnArmor, btnNPC, btnQuest, btnAchievement);
		setupToolbar(toolBarDatabaseSelection);
		
		selectedDatabase.addListener((args, oldDB, newDB) ->
		{
			btnWeapon.getStyleClass().set(1, unselectedStyleClass);
			btnArmor.getStyleClass().set(1, unselectedStyleClass);
			btnNPC.getStyleClass().set(1, unselectedStyleClass);
			btnQuest.getStyleClass().set(1, unselectedStyleClass);
			btnAchievement.getStyleClass().set(1, unselectedStyleClass);
			
			buttonDatabaseHashmap.get(newDB).getStyleClass().set(1, selectedStyleClass);
		});
		
		selectedDatabase.setValue(DatabaseType.WEAPON);
	}
	
	private void setupImageView(ImageView imageView)
	{
		imageView.setFitWidth(imageWidth);
		imageView.setPreserveRatio(true);
	}
	
	private void setupButton(Button button, DatabaseType buttonDatabaseType)
	{
		buttonDatabaseHashmap.put(buttonDatabaseType, button);
		button.setContentDisplay(ContentDisplay.TOP);
		button.setPadding(padding);
		button.setPrefWidth(buttonWidth);
		button.getStyleClass().add(unselectedStyleClass);
		button.setOnAction(e -> onMouseClickButton(buttonDatabaseType));
		button.setOnMouseEntered(e ->
		{
			button.setEffect(glowEffect);
			button.getGraphic().setScaleX(1.03);
			button.getGraphic().setScaleY(1.03);
		});
		button.setOnMouseExited(e ->
		{
			button.setEffect(null);
			button.getGraphic().setScaleX(1.0);
			button.getGraphic().setScaleY(1.0);
		});
	}
	
	private void setupToolbar(ToolBar toolBar)
	{
		toolBar.setOrientation(Orientation.VERTICAL);
		toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
	}
	
	private void onMouseClickButton(DatabaseType newType)
	{
		selectedDatabase.setValue(newType);
	}

	public ToolBar getToolBarDatabase()
	{
		return toolBarDatabaseSelection;
	}

	public DatabaseType getSelectedDatabase()
	{
		return selectedDatabase.getValue();
	}
	
	public ReadOnlyObjectProperty<DatabaseType> selectedDatabaseProperty()
	{
		return selectedDatabase.getReadOnlyProperty();
	}
}

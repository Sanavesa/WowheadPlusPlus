package com.wowhead.gui.page;

import com.wowhead.constant.Resources;
import com.wowhead.gui.PageManager;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HomePage extends Page
{
	public HomePage(PageManager sceneManager)
	{
		super(sceneManager);
	}

	@Override
	protected void initializePage()
	{
		root = new BorderPane();
		
		root.setStyle("-fx-background: #000000");
		
		// Setup left
		final int imageWidth = 72;
		
		ImageView imgWeapon = new ImageView(Resources.IMG_ICON_WEAPON);
		imgWeapon.setFitWidth(imageWidth);
		imgWeapon.setPreserveRatio(true);
		ImageView imgArmor= new ImageView(Resources.IMG_ICON_ARMOR);
		imgArmor.setFitWidth(imageWidth);
		imgArmor.setPreserveRatio(true);		
		ImageView imgNPC = new ImageView(Resources.IMG_ICON_NPC);
		imgNPC.setFitWidth(imageWidth);
		imgNPC.setPreserveRatio(true);
		ImageView imgQuest = new ImageView(Resources.IMG_ICON_QUEST);
		imgQuest.setFitWidth(imageWidth);
		imgQuest.setPreserveRatio(true);
		ImageView imgAchievement = new ImageView(Resources.IMG_ICON_ACHIEVEMENT);
		imgAchievement.setFitWidth(imageWidth);
		imgAchievement.setPreserveRatio(true);
		
		final int buttonWidth = 120;
		final Insets padding = new Insets(10);
		Button btnWeapon = new Button("Weapon DB", imgWeapon);
		btnWeapon.setContentDisplay(ContentDisplay.TOP);
		btnWeapon.setPadding(padding);
		btnWeapon.setPrefWidth(buttonWidth);
		Button btnArmor= new Button("Armor DB", imgArmor);
		btnArmor.setContentDisplay(ContentDisplay.TOP);
		btnArmor.setPadding(padding);
		btnArmor.setPrefWidth(buttonWidth);
		Button btnNPC = new Button("NPC DB", imgNPC);
		btnNPC.setContentDisplay(ContentDisplay.TOP);
		btnNPC.setPadding(padding);
		btnNPC.setPrefWidth(buttonWidth);
		Button btnQuest = new Button("Quest DB", imgQuest);
		btnQuest.setContentDisplay(ContentDisplay.TOP);
		btnQuest.setPadding(padding);
		btnQuest.setPrefWidth(buttonWidth);
		Button btnAchievement= new Button("Achievement DB", imgAchievement);
		btnAchievement.setContentDisplay(ContentDisplay.TOP);
		btnAchievement.setPadding(padding);
		btnAchievement.setPrefWidth(buttonWidth);
		
		ToolBar toolBarDatabase = new ToolBar(btnWeapon, btnArmor, btnNPC, btnQuest, btnAchievement);
		toolBarDatabase.setOrientation(Orientation.VERTICAL);
		toolBarDatabase.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		root.setLeft(toolBarDatabase);
		
		// Setup top
		ImageView imgLogo = new ImageView(Resources.IMG_WOWHEAD_LOGO);
		
		Button btnLogin = new Button("Login");
		btnLogin.setFocusTraversable(false);
		btnLogin.setPrefSize(80, 30);
		Button btnRegister = new Button("Register");
		btnRegister.setFocusTraversable(false);
		btnRegister.setPrefSize(80, 30);
		VBox accountManagement = new VBox(5, btnLogin, btnRegister);
		accountManagement.setPadding(new Insets(10));
		
		Region region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		Region region2 = new Region();
		HBox.setHgrow(region2, Priority.ALWAYS);
		HBox top = new HBox(region, imgLogo, region2, accountManagement);
		
		root.setTop(top);
		
		// Setup center of the rest
		Region region3 = new Region();
		HBox.setHgrow(region3, Priority.ALWAYS);
		Region region4 = new Region();
		HBox.setHgrow(region4, Priority.ALWAYS);
		TextField searchBar = new TextField("");
		searchBar.setPrefSize(300, 30);
		searchBar.setPromptText("Enter search criteria");
		ImageView imgSearch = new ImageView(Resources.IMG_SEARCH);
		imgSearch.setFitHeight(16);
		imgSearch.setPreserveRatio(true);
		Button btnSearch = new Button("", imgSearch);
		HBox center = new HBox(10, region3, searchBar, btnSearch, region4);
		BorderPane.setAlignment(center, Pos.CENTER_RIGHT);
		Region region5 = new Region();
		VBox.setVgrow(region5, Priority.ALWAYS);
		Region region6 = new Region();
		VBox.setVgrow(region6, Priority.ALWAYS);
		VBox centerVbox = new VBox(region5, center, region6);
		root.setCenter(centerVbox);
	}
}

// Mohammad
package com.wowhead.gui.page;

import com.wowhead.constant.Resources;
import com.wowhead.database.Database;
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
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.AchievementTableResultView;
import com.wowhead.gui.page.view.ArmorTableResultView;
import com.wowhead.gui.page.view.DatabaseSelectionView;
import com.wowhead.gui.page.view.ElixirTableResultView;
import com.wowhead.gui.page.view.FoodTableResultView;
import com.wowhead.gui.page.view.GemTableResultView;
import com.wowhead.gui.page.view.NPCTableResultView;
import com.wowhead.gui.page.view.QuestItemTableResultView;
import com.wowhead.gui.page.view.QuestTableResultView;
import com.wowhead.gui.page.view.SpellTableResultView;
import com.wowhead.gui.page.view.TopView;
import com.wowhead.gui.page.view.WeaponTableResultView;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HomePage extends Page
{
	private DatabaseSelectionView databaseSelectionView;
	private TopView topView;
	private VBox centerVbox; 
	
	private WeaponTableResultView weaponView;
	private ArmorTableResultView armorView;
	private GemTableResultView gemView;
	private FoodTableResultView foodView;
	private ElixirTableResultView elixirView;
	private QuestItemTableResultView questItemView;
	private SpellTableResultView spellView;
	private NPCTableResultView npcView;
	private QuestTableResultView questView;
	private AchievementTableResultView achievementView;
	
	public HomePage(PageManager sceneManager)
	{
		super(sceneManager);
	}

	@Override
	protected void initializePage()
	{
		root.setStyle("-fx-background: #000000");
		
		weaponView = new WeaponTableResultView(pageManager);
		armorView = new ArmorTableResultView(pageManager);
		gemView = new GemTableResultView(pageManager);
		foodView = new FoodTableResultView(pageManager);
		elixirView = new ElixirTableResultView(pageManager);
		questItemView = new QuestItemTableResultView(pageManager);
		spellView = new SpellTableResultView(pageManager);
		npcView = new NPCTableResultView(pageManager);
		questView = new QuestTableResultView(pageManager);
		achievementView = new AchievementTableResultView(pageManager);
		
		// Setup left
		databaseSelectionView = new DatabaseSelectionView();
		root.setLeft(databaseSelectionView.getToolBarDatabase());
		
		// Setup top
		topView = new TopView(pageManager);
		root.setTop(topView.getRoot());

		// Setup center of the rest
		Region region3 = new Region();
		HBox.setHgrow(region3, Priority.ALWAYS);
		Region region4 = new Region();
		HBox.setHgrow(region4, Priority.ALWAYS);
		TextField searchBar = new TextField("");
		searchBar.setPrefSize(400, 40);
		searchBar.setPromptText("Enter search criteria");
		searchBar.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				searchDatabase(searchBar.getText());
			}
		});
		ImageView imgSearch = new ImageView(Resources.IMG_SEARCH);
		imgSearch.setFitHeight(30);
		imgSearch.setPreserveRatio(true);
		Button btnSearch = new Button("", imgSearch);
		btnSearch.setOnAction(e -> searchDatabase(searchBar.getText()));
		HBox center = new HBox(10, region3, searchBar, btnSearch, region4);
		BorderPane.setAlignment(center, Pos.CENTER_RIGHT);
		Region region5 = new Region();
		VBox.setVgrow(region5, Priority.ALWAYS);
		Region region6 = new Region();
		VBox.setVgrow(region6, Priority.ALWAYS);
		
		centerVbox = new VBox(20, region5, center, weaponView.getRoot(), region6);
		root.setCenter(centerVbox);
		
		databaseSelectionView.selectedDatabaseProperty().addListener((args, oldDB, newDB) ->
		{
			// if changed, hide tables.
			if(!oldDB.equals(newDB))
			{
				weaponView.getTable().getItems().clear();
				weaponView.getTable().refresh();
				
				armorView.getTable().getItems().clear();
				armorView.getTable().refresh();
				
				gemView.getTable().getItems().clear();
				gemView.getTable().refresh();
				
				foodView.getTable().getItems().clear();
				foodView.getTable().refresh();
				
				elixirView.getTable().getItems().clear();
				elixirView.getTable().refresh();
				
				questItemView.getTable().getItems().clear();
				questItemView.getTable().refresh();
				
				spellView.getTable().getItems().clear();
				spellView.getTable().refresh();
				
				npcView.getTable().getItems().clear();
				npcView.getTable().refresh();
				
				questView.getTable().getItems().clear();
				questView.getTable().refresh();
				
				achievementView.getTable().getItems().clear();
				achievementView.getTable().refresh();
				
				searchBar.setText("");
			}
			
			switch(newDB)
			{
			case WEAPON:
				centerVbox.getChildren().set(2, weaponView.getRoot());
				break;
			case ARMOR:
				centerVbox.getChildren().set(2, armorView.getRoot());
				break;
			case GEM:
				centerVbox.getChildren().set(2, gemView.getRoot());
				break;
			case FOOD:
				centerVbox.getChildren().set(2, foodView.getRoot());
				break;
			case ELIXIR:
				centerVbox.getChildren().set(2, elixirView.getRoot());
				break;
			case QUEST_ITEM:
				centerVbox.getChildren().set(2, questItemView.getRoot());
				break;
			case SPELL:
				centerVbox.getChildren().set(2, spellView.getRoot());
				break;
			case NPC:
				centerVbox.getChildren().set(2, npcView.getRoot());
				break;
			case QUEST:
				centerVbox.getChildren().set(2, questView.getRoot());
				break;
			case ACHIEVEMENT:
				centerVbox.getChildren().set(2, achievementView.getRoot());
				break;
			default:
				System.err.println("Database not implemented: " + newDB);
				break;
			}
		});
	}
	
	private void searchDatabase(String searchCriteria)
	{
		int resultLength = 0;
		switch(databaseSelectionView.getSelectedDatabase())
		{
		case WEAPON:
			{
				Weapon[] searchResult = Database.getInstance().searchWeapon(searchCriteria);
				weaponView.getTable().getItems().clear();
				weaponView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				weaponView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case ARMOR:
			{
				Armor[] searchResult = Database.getInstance().searchArmor(searchCriteria);
				armorView.getTable().getItems().clear();
				armorView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				armorView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case GEM:
			{
				Gem[] searchResult = Database.getInstance().searchGem(searchCriteria);
				gemView.getTable().getItems().clear();
				gemView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				gemView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case FOOD:
			{
				Food[] searchResult = Database.getInstance().searchFood(searchCriteria);
				foodView.getTable().getItems().clear();
				foodView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				foodView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case ELIXIR:
			{
				Elixir[] searchResult = Database.getInstance().searchElixir(searchCriteria);
				elixirView.getTable().getItems().clear();
				elixirView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				elixirView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case QUEST_ITEM:
			{
				QuestItem[] searchResult = Database.getInstance().searchQuestItem(searchCriteria);
				questItemView.getTable().getItems().clear();
				questItemView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				questItemView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case SPELL:
			{
				Spell[] searchResult = Database.getInstance().searchSpell(searchCriteria);
				spellView.getTable().getItems().clear();
				spellView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				spellView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case NPC:
			{
				NPC[] searchResult = Database.getInstance().searchNPC(searchCriteria);
				npcView.getTable().getItems().clear();
				npcView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				npcView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case QUEST:
		{
				Quest[] searchResult = Database.getInstance().searchQuest(searchCriteria);
				questView.getTable().getItems().clear();
				questView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				questView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		case ACHIEVEMENT:
			{
				Achievement[] searchResult = Database.getInstance().searchAchievement(searchCriteria);
				achievementView.getTable().getItems().clear();
				achievementView.getTable().getItems().addAll(FXCollections.observableArrayList(searchResult));
				achievementView.getTable().refresh();
				resultLength = searchResult.length;
			}
			break;
		default:
			System.err.println("No search implemented.");
			break;
		}
		
		if(resultLength == 0)
		{
			Alert alert = new Alert(AlertType.ERROR, "No matches for " + searchCriteria, ButtonType.OK);
			alert.show();
		}
	}
}

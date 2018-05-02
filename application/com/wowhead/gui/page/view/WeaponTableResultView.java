package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.constants.WeaponType;
import com.wowhead.database.tables.Weapon;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.AddWeaponPage;
import com.wowhead.gui.page.UpdateWeaponPage;
import com.wowhead.gui.page.WeaponDisplayPage;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class WeaponTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Weapon> table = new TableView<Weapon>();
	private final PageManager pageManager;
	
	@SuppressWarnings("unchecked")
	public WeaponTableResultView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		TableColumn<Weapon, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Weapon, String> nameCol = new TableColumn<>("Name");
		TableColumn<Weapon, ItemRarity> rarityCol = new TableColumn<>("Rarity");
		TableColumn<Weapon, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<Weapon, Integer> levelReqCol = new TableColumn<>("Level Req");
		TableColumn<Weapon, WeaponType> weaponTypeCol = new TableColumn<>("Weapon Type");
		TableColumn<Weapon, Double> attackSpeedCol = new TableColumn<>("Attack Speed");
		TableColumn<Weapon, Double> attackDamageCol = new TableColumn<>("Attack Damage");
		TableColumn<Weapon, Integer> statsCol = new TableColumn<>("Stats");
		TableColumn<Weapon, Integer> staminaCol = new TableColumn<>("Stamina");
		TableColumn<Weapon, Integer> strengthCol = new TableColumn<>("Strength");
		TableColumn<Weapon, Integer> spiritCol = new TableColumn<>("Spirit");
		TableColumn<Weapon, Integer> intellectCol = new TableColumn<>("Intellect");
		TableColumn<Weapon, Integer> agilityCol = new TableColumn<>("Agility");
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		statsCol.getColumns().addAll(staminaCol, strengthCol, spiritCol, intellectCol, agilityCol);
		table.getColumns().addAll(idCol, nameCol, rarityCol, descriptionCol, levelReqCol, weaponTypeCol, attackSpeedCol, attackDamageCol, statsCol);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rarityCol.setCellValueFactory(new PropertyValueFactory<>("rarity"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		levelReqCol.setCellValueFactory(new PropertyValueFactory<>("levelReq"));
		weaponTypeCol.setCellValueFactory(new PropertyValueFactory<>("weaponType"));
		attackSpeedCol.setCellValueFactory(new PropertyValueFactory<>("attackSpeed"));
		attackDamageCol.setCellValueFactory(new PropertyValueFactory<>("attackDamage"));
		staminaCol.setCellValueFactory(new PropertyValueFactory<>("stamina"));
		strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));
		spiritCol.setCellValueFactory(new PropertyValueFactory<>("spirit"));
		intellectCol.setCellValueFactory(new PropertyValueFactory<>("intellect"));
		agilityCol.setCellValueFactory(new PropertyValueFactory<>("agility"));
		
		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());
		
		table.setRowFactory(tv ->
		{
			TableRow<Weapon> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if(e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Weapon rowData = row.getItem();
					viewWeapon(rowData);
				}
			});
			return row;
		});
		
		table.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(table.getSelectionModel().getSelectedItem() == null)
				return;
			
			viewWeapon(table.getSelectionModel().getSelectedItem());
		});
		
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(e -> onPressedAdd());
		btnAdd.setPrefSize(90, 30);
		Button btnDelete = new Button("Delete");
		btnDelete.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
		btnDelete.setOnAction(e -> onPressedDelete());
		btnDelete.setPrefSize(90, 30);
		Region r1 = new Region();
		Region r2 = new Region();
		HBox.setHgrow(r1, Priority.ALWAYS);
		HBox.setHgrow(r2, Priority.ALWAYS);
		HBox buttonsLayout = new HBox(20, r1, btnAdd, btnDelete, r2);
		
		buttonsLayout.managedProperty().bind(buttonsLayout.visibleProperty());
		buttonsLayout.visibleProperty().bind(Bindings.equal(Database.getInstance().accountRankProperty(), AccountRank.MODERATOR).and(table.visibleProperty()));
		
		root.getChildren().addAll(table, buttonsLayout);
	}
	
	private void onPressedDelete()
	{
		Database.getInstance().deleteEntry("Item", table.getSelectionModel().getSelectedItem().getId());
		table.getItems().remove(table.getSelectionModel().getSelectedIndex());
		table.refresh();
	}

	private void onPressedAdd()
	{
		AddWeaponPage page = (AddWeaponPage) pageManager.addPage(AddWeaponPage.class);
		page.resetToDefault();
	}

	public TableView<Weapon> getTable()
	{
		return table;
	}
	
	public VBox getRoot()
	{
		return root;
	}

	public void viewWeapon(Weapon weapon)
	{
		if(Database.getInstance().getAccountRank() == AccountRank.MODERATOR)
		{
			UpdateWeaponPage page = (UpdateWeaponPage) pageManager.addPage(UpdateWeaponPage.class);
			page.loadFromWeapon(weapon);
		}
		else
		{
			WeaponDisplayPage page = (WeaponDisplayPage) pageManager.addPage(WeaponDisplayPage.class);
			page.loadDisplay(weapon);
		}
	}
}
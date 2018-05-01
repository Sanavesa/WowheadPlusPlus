package com.wowhead.gui.page.view;

import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.ArmorType;
import com.wowhead.database.constants.EquipmentSlot;
import com.wowhead.database.tables.Armor;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ArmorTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Armor> table = new TableView<Armor>();
	
	@SuppressWarnings("unchecked")
	public ArmorTableResultView()
	{
		TableColumn<Armor, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Armor, String> nameCol = new TableColumn<>("Name");
		TableColumn<Armor, ItemRarity> rarityCol = new TableColumn<>("Rarity");
		TableColumn<Armor, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<Armor, Integer> levelReqCol = new TableColumn<>("Level Req");
		TableColumn<Armor, ArmorType> armorTypeCol = new TableColumn<>("Armor Type");
		TableColumn<Armor, EquipmentSlot> equipmentSlotCol = new TableColumn<>("Equipment Slot");
		TableColumn<Armor, Integer> statsCol = new TableColumn<>("Stats");
		TableColumn<Armor, Integer> staminaCol = new TableColumn<>("Stamina");
		TableColumn<Armor, Integer> strengthCol = new TableColumn<>("Strength");
		TableColumn<Armor, Integer> spiritCol = new TableColumn<>("Spirit");
		TableColumn<Armor, Integer> intellectCol = new TableColumn<>("Intellect");
		TableColumn<Armor, Integer> agilityCol = new TableColumn<>("Agility");
		
		statsCol.getColumns().addAll(staminaCol, strengthCol, spiritCol, intellectCol, agilityCol);
		table.getColumns().addAll(idCol, nameCol, rarityCol, descriptionCol, levelReqCol, armorTypeCol, equipmentSlotCol, statsCol);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rarityCol.setCellValueFactory(new PropertyValueFactory<>("rarity"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		levelReqCol.setCellValueFactory(new PropertyValueFactory<>("levelReq"));
		armorTypeCol.setCellValueFactory(new PropertyValueFactory<>("armorType"));
		equipmentSlotCol.setCellValueFactory(new PropertyValueFactory<>("equipmentSlot"));
		staminaCol.setCellValueFactory(new PropertyValueFactory<>("stamina"));
		strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));
		spiritCol.setCellValueFactory(new PropertyValueFactory<>("spirit"));
		intellectCol.setCellValueFactory(new PropertyValueFactory<>("intellect"));
		agilityCol.setCellValueFactory(new PropertyValueFactory<>("agility"));
		
		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());
		
		table.setRowFactory(tv ->
		{
			TableRow<Armor> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if(e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Armor rowData = row.getItem();
					System.out.println("Double clicked " + rowData.getId());
					viewArmor(rowData);
				}
			});
			return row;
		});
		
		Button btnAdd = new Button("Add");
		Button btnDelete = new Button("Delete");
		HBox buttonsLayout = new HBox(20, btnAdd, btnDelete);
		
		buttonsLayout.managedProperty().bind(table.visibleProperty());
		buttonsLayout.visibleProperty().bind(Bindings.equal(Database.getInstance().accountRankProperty(), AccountRank.MODERATOR));
		
		root.getChildren().addAll(table, buttonsLayout);
	}
	
	public TableView<Armor> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}
	
	public void viewArmor(Armor Armor)
	{
		table.getItems().remove(Armor);
	}
}
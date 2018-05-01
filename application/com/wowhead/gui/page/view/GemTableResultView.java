package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.GemType;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.tables.Gem;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GemTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Gem> table = new TableView<Gem>();

	@SuppressWarnings("unchecked")
	public GemTableResultView()
	{
		TableColumn<Gem, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Gem, String> nameCol = new TableColumn<>("Name");
		TableColumn<Gem, ItemRarity> rarityCol = new TableColumn<>("Rarity");
		TableColumn<Gem, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<Gem, Integer> levelReqCol = new TableColumn<>("Level Req");
		TableColumn<Gem, GemType> gemTypeCol = new TableColumn<>("Gem Type");
		TableColumn<Gem, Integer> statsCol = new TableColumn<>("Stats");
		TableColumn<Gem, Integer> staminaCol = new TableColumn<>("Stamina");
		TableColumn<Gem, Integer> strengthCol = new TableColumn<>("Strength");
		TableColumn<Gem, Integer> spiritCol = new TableColumn<>("Spirit");
		TableColumn<Gem, Integer> intellectCol = new TableColumn<>("Intellect");
		TableColumn<Gem, Integer> agilityCol = new TableColumn<>("Agility");

		statsCol.getColumns().addAll(staminaCol, strengthCol, spiritCol, intellectCol, agilityCol);
		table.getColumns().addAll(idCol, nameCol, rarityCol, descriptionCol, levelReqCol, gemTypeCol, statsCol);

		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);

		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rarityCol.setCellValueFactory(new PropertyValueFactory<>("rarity"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		levelReqCol.setCellValueFactory(new PropertyValueFactory<>("levelReq"));
		gemTypeCol.setCellValueFactory(new PropertyValueFactory<>("gemType"));
		staminaCol.setCellValueFactory(new PropertyValueFactory<>("stamina"));
		strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));
		spiritCol.setCellValueFactory(new PropertyValueFactory<>("spirit"));
		intellectCol.setCellValueFactory(new PropertyValueFactory<>("intellect"));
		agilityCol.setCellValueFactory(new PropertyValueFactory<>("agility"));

		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());

		table.setRowFactory(tv ->
		{
			TableRow<Gem> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if (e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Gem rowData = row.getItem();
					System.out.println("Double clicked " + rowData.getId());
					viewGem(rowData);
				}
			});
			return row;
		});

		Button btnAdd = new Button("Add");
		Button btnDelete = new Button("Delete");
		HBox buttonsLayout = new HBox(20, btnAdd, btnDelete);

		buttonsLayout.managedProperty().bind(table.visibleProperty());
		buttonsLayout.visibleProperty()
				.bind(Bindings.equal(Database.getInstance().accountRankProperty(), AccountRank.MODERATOR));

		root.getChildren().addAll(table, buttonsLayout);
	}

	public TableView<Gem> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewGem(Gem Gem)
	{
		table.getItems().remove(Gem);
	}
}
package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.Faction;
import com.wowhead.database.constants.NPCType;
import com.wowhead.database.tables.NPC;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NPCTableResultView
{
	private TableView<NPC> table = new TableView<NPC>();
	private VBox root = new VBox(20);

	@SuppressWarnings("unchecked")
	public NPCTableResultView()
	{
		TableColumn<NPC, Integer> idCol = new TableColumn<>("ID");
		TableColumn<NPC, String> nameCol = new TableColumn<>("Name");
		TableColumn<NPC, Faction> factionCol = new TableColumn<>("Faction");
		TableColumn<NPC, NPCType> npcTypeCol = new TableColumn<>("Type");
		TableColumn<NPC, Integer> healthCol = new TableColumn<>("Health");

		table.getColumns().addAll(idCol, nameCol, factionCol, npcTypeCol, healthCol);

		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);

		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		factionCol.setCellValueFactory(new PropertyValueFactory<>("faction"));
		npcTypeCol.setCellValueFactory(new PropertyValueFactory<>("npcType"));
		healthCol.setCellValueFactory(new PropertyValueFactory<>("health"));

		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());

		table.setRowFactory(tv ->
		{
			TableRow<NPC> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if (e.getClickCount() == 2 && (!row.isEmpty()))
				{
					NPC rowData = row.getItem();
					System.out.println("Double clicked " + rowData.getId());
					viewNPC(rowData);
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

	public TableView<NPC> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewNPC(NPC NPC)
	{
		table.getItems().remove(NPC);
	}
}
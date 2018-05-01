package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.tables.Elixir;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ElixirTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Elixir> table = new TableView<Elixir>();
	
	
	@SuppressWarnings("unchecked")
	public ElixirTableResultView()
	{
		TableColumn<Elixir, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Elixir, String> nameCol = new TableColumn<>("Name");
		TableColumn<Elixir, ItemRarity> rarityCol = new TableColumn<>("Rarity");
		TableColumn<Elixir, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<Elixir, Integer> levelReqCol = new TableColumn<>("Level Req");
		TableColumn<Elixir, Double> durationCol = new TableColumn<>("Duration");
		TableColumn<Elixir, Integer> statsCol = new TableColumn<>("Stats");
		TableColumn<Elixir, Integer> staminaCol = new TableColumn<>("Stamina");
		TableColumn<Elixir, Integer> strengthCol = new TableColumn<>("Strength");
		TableColumn<Elixir, Integer> spiritCol = new TableColumn<>("Spirit");
		TableColumn<Elixir, Integer> intellectCol = new TableColumn<>("Intellect");
		TableColumn<Elixir, Integer> agilityCol = new TableColumn<>("Agility");
		
		statsCol.getColumns().addAll(staminaCol, strengthCol, spiritCol, intellectCol, agilityCol);
		table.getColumns().addAll(idCol, nameCol, rarityCol, descriptionCol, levelReqCol, durationCol, statsCol);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rarityCol.setCellValueFactory(new PropertyValueFactory<>("rarity"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		levelReqCol.setCellValueFactory(new PropertyValueFactory<>("levelReq"));
		durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
		staminaCol.setCellValueFactory(new PropertyValueFactory<>("stamina"));
		strengthCol.setCellValueFactory(new PropertyValueFactory<>("strength"));
		spiritCol.setCellValueFactory(new PropertyValueFactory<>("spirit"));
		intellectCol.setCellValueFactory(new PropertyValueFactory<>("intellect"));
		agilityCol.setCellValueFactory(new PropertyValueFactory<>("agility"));
		
		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());
		
		table.setRowFactory(tv ->
		{
			TableRow<Elixir> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if(e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Elixir rowData = row.getItem();
					System.out.println("Double clicked " + rowData.getId());
					viewElixir(rowData);
				}
			});
			return row;
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
		
	}
	
	public TableView<Elixir> getTable()
	{
		return table;
	}
	
	public VBox getRoot()
	{
		return root;
	}
	
	public void viewElixir(Elixir Elixir)
	{
		table.getItems().remove(Elixir);
	}
}
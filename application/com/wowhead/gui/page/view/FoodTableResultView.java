package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.tables.Food;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.FoodDisplayPage;

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

public class FoodTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Food> table = new TableView<Food>();
	private final PageManager pageManager;
	
	@SuppressWarnings("unchecked")
	public FoodTableResultView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		TableColumn<Food, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Food, String> nameCol = new TableColumn<>("Name");
		TableColumn<Food, ItemRarity> rarityCol = new TableColumn<>("Rarity");
		TableColumn<Food, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<Food, Integer> levelReqCol = new TableColumn<>("Level Req");
		TableColumn<Food, Double> regenSpeedCol = new TableColumn<>("Regen Speed");

		table.getColumns().addAll(idCol, nameCol, rarityCol, descriptionCol, levelReqCol, regenSpeedCol);

		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);

		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rarityCol.setCellValueFactory(new PropertyValueFactory<>("rarity"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		levelReqCol.setCellValueFactory(new PropertyValueFactory<>("levelReq"));
		regenSpeedCol.setCellValueFactory(new PropertyValueFactory<>("regenSpeed"));

		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());

		table.setRowFactory(tv ->
		{
			TableRow<Food> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if (e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Food rowData = row.getItem();
					viewFood(rowData);
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

	public TableView<Food> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewFood(Food food)
	{
		FoodDisplayPage page = (FoodDisplayPage) pageManager.addPage(FoodDisplayPage.class);
		page.loadDisplay(food);
	}
}
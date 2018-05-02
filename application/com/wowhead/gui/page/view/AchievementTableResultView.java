package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.tables.Achievement;
import com.wowhead.gui.PageManager;

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

public class AchievementTableResultView
{
	private VBox root = new VBox();
	private TableView<Achievement> table = new TableView<Achievement>();
	private final PageManager pageManager;
	
	@SuppressWarnings("unchecked")
	public AchievementTableResultView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		TableColumn<Achievement, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Achievement, String> nameCol = new TableColumn<>("Name");
		TableColumn<Achievement, Integer> pointRewardCol = new TableColumn<>("Point Reward");
		
		table.getColumns().addAll(idCol, nameCol, pointRewardCol);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		pointRewardCol.setCellValueFactory(new PropertyValueFactory<>("pointReward"));
		
		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());
		
		table.setRowFactory(tv ->
		{
			TableRow<Achievement> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if(e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Achievement rowData = row.getItem();
					System.out.println("Double clicked " + rowData.getId());
					viewAchievement(rowData);
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
		Database.getInstance().deleteEntry("Achievement", table.getSelectionModel().getSelectedItem().getId());
		table.getItems().remove(table.getSelectionModel().getSelectedIndex());
		table.refresh();
	}

	private void onPressedAdd()
	{
		
	}
	
	public TableView<Achievement> getTable()
	{
		return table;
	}
	
	public VBox getRoot()
	{
		return root;
	}
	
	public void viewAchievement(Achievement Achievement)
	{
		table.getItems().remove(Achievement);
	}
}
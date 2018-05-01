package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.tables.Achievement;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AchievementTableResultView
{
	private VBox root = new VBox();
	private TableView<Achievement> table = new TableView<Achievement>();
	
	@SuppressWarnings("unchecked")
	public AchievementTableResultView()
	{
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
		Button btnDelete = new Button("Delete");
		HBox buttonsLayout = new HBox(20, btnAdd, btnDelete);
		
		buttonsLayout.managedProperty().bind(table.visibleProperty());
		buttonsLayout.visibleProperty().bind(Bindings.equal(Database.getInstance().accountRankProperty(), AccountRank.MODERATOR));
		
		root.getChildren().addAll(table, buttonsLayout);
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
package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.tables.QuestItem;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.QuestItemDisplayPage;

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

public class QuestItemTableResultView
{
	private VBox root = new VBox(20);
	private TableView<QuestItem> table = new TableView<QuestItem>();
	private final PageManager pageManager;

	@SuppressWarnings("unchecked")
	public QuestItemTableResultView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		TableColumn<QuestItem, Integer> idCol = new TableColumn<>("ID");
		TableColumn<QuestItem, String> nameCol = new TableColumn<>("Name");
		TableColumn<QuestItem, ItemRarity> rarityCol = new TableColumn<>("Rarity");
		TableColumn<QuestItem, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<QuestItem, Integer> levelReqCol = new TableColumn<>("Level Req");
		TableColumn<QuestItem, String> questNameCol = new TableColumn<>("Quest Name");

		table.getColumns().addAll(idCol, nameCol, rarityCol, descriptionCol, levelReqCol, questNameCol);

		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);

		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rarityCol.setCellValueFactory(new PropertyValueFactory<>("rarity"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		levelReqCol.setCellValueFactory(new PropertyValueFactory<>("levelReq"));
		questNameCol.setCellValueFactory(new PropertyValueFactory<>("questName"));

		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());

		table.setRowFactory(tv ->
		{
			TableRow<QuestItem> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if (e.getClickCount() == 2 && (!row.isEmpty()))
				{
					QuestItem rowData = row.getItem();
					viewQuestItem(rowData);
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

	public TableView<QuestItem> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewQuestItem(QuestItem questItem)
	{
		QuestItemDisplayPage page = (QuestItemDisplayPage) pageManager.addPage(QuestItemDisplayPage.class);
		page.loadDisplay(questItem);
	}
}
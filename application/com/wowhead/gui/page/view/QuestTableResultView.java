package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.Faction;
import com.wowhead.database.tables.Quest;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.QuestDisplayPage;

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

public class QuestTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Quest> table = new TableView<Quest>();
	private final PageManager pageManager;
	
	@SuppressWarnings("unchecked")
	public QuestTableResultView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		TableColumn<Quest, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Quest, String> nameCol = new TableColumn<>("Name");
		TableColumn<Quest, Faction> descriptionCol = new TableColumn<>("Description");
		TableColumn<Quest, String> questGiverCol = new TableColumn<>("Quest Giver");
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table.getColumns().addAll(idCol, nameCol, descriptionCol, questGiverCol);

		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);

		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		questGiverCol.setCellValueFactory(new PropertyValueFactory<>("npcName"));

		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());

		table.setRowFactory(tv ->
		{
			TableRow<Quest> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if (e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Quest rowData = row.getItem();
					viewQuest(rowData);
				}
			});
			return row;
		});
		
		table.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(table.getSelectionModel().getSelectedItem() == null)
				return;
			
			viewQuest(table.getSelectionModel().getSelectedItem());
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
		Database.getInstance().deleteEntry("Quest", table.getSelectionModel().getSelectedItem().getId());
		table.getItems().remove(table.getSelectionModel().getSelectedIndex());
		table.refresh();
	}

	private void onPressedAdd()
	{
		
	}

	public TableView<Quest> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewQuest(Quest quest)
	{
		QuestDisplayPage page = (QuestDisplayPage) pageManager.addPage(QuestDisplayPage.class);
		page.loadDisplay(quest);
	}
}
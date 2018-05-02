package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.Faction;
import com.wowhead.database.constants.NPCType;
import com.wowhead.database.tables.NPC;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.AddNpcPage;
import com.wowhead.gui.page.NpcDisplayPage;

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

public class NPCTableResultView
{
	private TableView<NPC> table = new TableView<NPC>();
	private VBox root = new VBox(20);
	private final PageManager pageManager;

	@SuppressWarnings("unchecked")
	public NPCTableResultView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		TableColumn<NPC, Integer> idCol = new TableColumn<>("ID");
		TableColumn<NPC, String> nameCol = new TableColumn<>("Name");
		TableColumn<NPC, Faction> factionCol = new TableColumn<>("Faction");
		TableColumn<NPC, NPCType> npcTypeCol = new TableColumn<>("Type");
		TableColumn<NPC, Integer> healthCol = new TableColumn<>("Health");
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
					viewNPC(rowData);
				}
			});
			return row;
		});
		
		table.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(table.getSelectionModel().getSelectedItem() == null)
				return;
			
			viewNPC(table.getSelectionModel().getSelectedItem());
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
		Database.getInstance().deleteEntry("NPC", table.getSelectionModel().getSelectedItem().getId());
		table.getItems().remove(table.getSelectionModel().getSelectedIndex());
		table.refresh();
	}

	private void onPressedAdd()
	{
		AddNpcPage page = (AddNpcPage) pageManager.addPage(AddNpcPage.class);
		page.resetToDefault();
	}

	public TableView<NPC> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewNPC(NPC npc)
	{
		NpcDisplayPage page = (NpcDisplayPage) pageManager.addPage(NpcDisplayPage.class);
		page.loadDisplay(npc);
	}
}
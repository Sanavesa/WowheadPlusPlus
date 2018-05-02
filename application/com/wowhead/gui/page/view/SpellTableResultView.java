package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.MagicSchool;
import com.wowhead.database.tables.Spell;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.AddSpellPage;
import com.wowhead.gui.page.SpellDisplayPage;

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

public class SpellTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Spell> table = new TableView<Spell>();
	private final PageManager pageManager;
	
	@SuppressWarnings("unchecked")
	public SpellTableResultView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		TableColumn<Spell, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Spell, String> nameCol = new TableColumn<>("Name");
		TableColumn<Spell, Integer> rangeCol = new TableColumn<>("Range");
		TableColumn<Spell, Double> durationCol = new TableColumn<>("Duration");
		TableColumn<Spell, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<Spell, MagicSchool> magicSchoolCol = new TableColumn<>("Magic School");
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table.getColumns().addAll(idCol, nameCol, rangeCol, durationCol, descriptionCol, magicSchoolCol);

		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		idCol.setSortType(SortType.ASCENDING);

		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rangeCol.setCellValueFactory(new PropertyValueFactory<>("range"));
		durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		magicSchoolCol.setCellValueFactory(new PropertyValueFactory<>("magicSchool"));

		table.managedProperty().bind(table.visibleProperty());
		table.visibleProperty().bind(Bindings.isEmpty(table.getItems()).not());

		table.setRowFactory(tv ->
		{
			TableRow<Spell> row = new TableRow<>();
			row.setOnMouseClicked(e ->
			{
				if (e.getClickCount() == 2 && (!row.isEmpty()))
				{
					Spell rowData = row.getItem();
					viewSpell(rowData);
				}
			});
			return row;
		});
		
		table.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(table.getSelectionModel().getSelectedItem() == null)
				return;
			
			viewSpell(table.getSelectionModel().getSelectedItem());
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
		Database.getInstance().deleteEntry("Spell", table.getSelectionModel().getSelectedItem().getId());
		table.getItems().remove(table.getSelectionModel().getSelectedIndex());
		table.refresh();
	}

	private void onPressedAdd()
	{
		AddSpellPage page = (AddSpellPage) pageManager.addPage(AddSpellPage.class);
		page.resetToDefault();
	}

	public TableView<Spell> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewSpell(Spell spell)
	{
		SpellDisplayPage page = (SpellDisplayPage) pageManager.addPage(SpellDisplayPage.class);
		page.loadDisplay(spell);
	}
}
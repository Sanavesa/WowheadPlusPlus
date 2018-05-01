package com.wowhead.gui.page.view;

import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.database.constants.MagicSchool;
import com.wowhead.database.tables.Spell;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SpellTableResultView
{
	private VBox root = new VBox(20);
	private TableView<Spell> table = new TableView<Spell>();

	@SuppressWarnings("unchecked")
	public SpellTableResultView()
	{
		TableColumn<Spell, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Spell, String> nameCol = new TableColumn<>("Name");
		TableColumn<Spell, Integer> rangeCol = new TableColumn<>("Range");
		TableColumn<Spell, Double> durationCol = new TableColumn<>("Duration");
		TableColumn<Spell, String> descriptionCol = new TableColumn<>("Description");
		TableColumn<Spell, MagicSchool> magicSchoolCol = new TableColumn<>("Magic School");

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
					System.out.println("Double clicked " + rowData.getId());
					viewSpell(rowData);
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

	public TableView<Spell> getTable()
	{
		return table;
	}

	public VBox getRoot()
	{
		return root;
	}

	public void viewSpell(Spell Spell)
	{
		table.getItems().remove(Spell);
	}
}
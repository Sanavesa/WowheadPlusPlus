// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Elixir;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ElixirDisplayPage extends Page
{
	private TopView topView;
	private Label itemName, itemDescription, itemLevel, itemDuration, itemStamina, itemStrength, itemSpirit,
			itemIntellect, itemAgility;

	public ElixirDisplayPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		itemName = new Label("");
		itemDescription = new Label("");
		itemLevel = new Label("");
		itemDuration = new Label("");
		itemStamina = new Label("");
		itemStrength = new Label("");
		itemSpirit = new Label("");
		itemIntellect = new Label("");
		itemAgility = new Label("");

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Elixir elixir)
	{
		GridPane layout = new GridPane();
		layout.setMaxWidth(400);
		ColumnConstraints col1 = new ColumnConstraints(300);
		col1.setHalignment(HPos.LEFT);
		layout.getColumnConstraints().add(col1);
		layout.setPadding(new Insets(10, 5, 10, 5));
		layout.setStyle("-fx-border-width: 2px; -fx-border-radius: 12px; -fx-border-color: #888888;");
		root.setCenter(layout);

		int rowIndex = 0;

		itemName.setText(elixir.getName());
		itemName.setStyle("-fx-font-size: 24px;");
		itemName.setTextFill(elixir.getRarity().getColor());
		layout.addRow(rowIndex++, itemName);

		itemDuration.setText((elixir.getDuration()/3600.0f) + " hour");
		itemDuration.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemDuration);

		if (elixir.getStamina() != 0)
		{
			itemStamina.setText("+" + elixir.getStamina() + " Stamina");
			itemStamina.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStamina);
		}

		if (elixir.getStrength() != 0)
		{
			itemStrength.setText("+" + elixir.getStrength() + " Strength");
			itemStrength.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStrength);
		}

		if (elixir.getSpirit() != 0)
		{
			itemSpirit.setText("+" + elixir.getSpirit() + " Spirit");
			itemSpirit.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemSpirit);
		}

		if (elixir.getIntellect() != 0)
		{
			itemIntellect.setText("+" + elixir.getIntellect() + " Intellect");
			itemIntellect.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemIntellect);
		}

		if (elixir.getAgility() != 0)
		{
			itemAgility.setText("+" + elixir.getAgility() + " Agility");
			itemAgility.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemAgility);
		}

		if (elixir.getDescription().length() > 0)
		{
			itemDescription.setText(elixir.getDescription());
			itemDescription.setStyle("-fx-font-size: 16px; -fx-text-fill: #11FF11;");
			itemDescription.setWrapText(true);
			GridPane.setVgrow(itemDescription, Priority.SOMETIMES);
			layout.addRow(rowIndex++, itemDescription);
		}

		itemLevel.setText("Requires Level " + elixir.getLevelReq());
		itemLevel.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemLevel);

		layout.setMaxHeight(24 + 16 * rowIndex + 20 + itemDescription.getHeight());
	}
}
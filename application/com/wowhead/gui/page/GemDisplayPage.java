// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Gem;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GemDisplayPage extends Page
{
	private TopView topView;
	private Label itemName, itemLevel, itemGemType, itemStamina, itemStrength, itemSpirit,
			itemIntellect, itemAgility;
	private Text itemDescription;

	public GemDisplayPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		itemName = new Label("");
		itemDescription = new Text("");
		itemDescription.setWrappingWidth(390);
		itemLevel = new Label("");
		itemGemType = new Label("");
		itemStamina = new Label("");
		itemStrength = new Label("");
		itemSpirit = new Label("");
		itemIntellect = new Label("");
		itemAgility = new Label("");

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Gem gem)
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

		itemName.setText(gem.getName());
		itemName.setStyle("-fx-font-size: 24px;");
		itemName.setTextFill(gem.getRarity().getColor());
		layout.addRow(rowIndex++, itemName);

		itemGemType.setText(gem.getGemType().toString());
		itemGemType.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemGemType);

		if (gem.getStamina() != 0)
		{
			itemStamina.setText("+" + gem.getStamina() + " Stamina");
			itemStamina.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStamina);
		}

		if (gem.getStrength() != 0)
		{
			itemStrength.setText("+" + gem.getStrength() + " Strength");
			itemStrength.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStrength);
		}

		if (gem.getSpirit() != 0)
		{
			itemSpirit.setText("+" + gem.getSpirit() + " Spirit");
			itemSpirit.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemSpirit);
		}

		if (gem.getIntellect() != 0)
		{
			itemIntellect.setText("+" + gem.getIntellect() + " Intellect");
			itemIntellect.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemIntellect);
		}

		if (gem.getAgility() != 0)
		{
			itemAgility.setText("+" + gem.getAgility() + " Agility");
			itemAgility.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemAgility);
		}

		if (gem.getDescription().length() > 0)
		{
			itemDescription.setText(gem.getDescription());
			itemDescription.setStyle("-fx-font-size: 16px;");
			itemDescription.setFill(Color.web("#11FF11"));
			layout.addRow(rowIndex++, itemDescription);
		}

		itemLevel.setText("Requires Level " + gem.getLevelReq());
		itemLevel.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemLevel);

		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}
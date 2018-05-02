// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Food;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FoodDisplayPage extends Page
{
	private TopView topView;
	private Label itemName, itemLevel, itemRegenSpeed;
	private Text itemDescription;
	
	public FoodDisplayPage(PageManager pageManager)
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
		itemRegenSpeed = new Label("");

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Food food)
	{
		GridPane layout = new GridPane();
		layout.setMaxWidth(400);
		layout.setPadding(new Insets(10));
		layout.setStyle("-fx-border-width: 2px; -fx-border-radius: 12px; -fx-border-color: #888888;");
		root.setCenter(layout);

		int rowIndex = 0;

		itemName.setText(food.getName());
		itemName.setStyle("-fx-font-size: 24px;");
		itemName.setTextFill(food.getRarity().getColor());
		layout.addRow(rowIndex++, itemName);

		itemRegenSpeed.setText("Generates " + (int)food.getRegenSpeed() + " health per second.");
		itemRegenSpeed.setStyle("-fx-font-size: 16px; -fx-text-fill: #11FF11;");
		layout.addRow(rowIndex++, itemRegenSpeed);

		if (food.getDescription().length() > 0)
		{
			itemDescription.setText(food.getDescription());
			itemDescription.setStyle("-fx-font-size: 16px;");
			itemDescription.setFill(Color.web("#11FF11"));
			layout.addRow(rowIndex++, itemDescription);
		}

		itemLevel.setText("Requires Level " + food.getLevelReq());
		itemLevel.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemLevel);

		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}
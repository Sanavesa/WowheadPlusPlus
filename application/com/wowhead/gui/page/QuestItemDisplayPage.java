// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.QuestItem;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class QuestItemDisplayPage extends Page
{
	private TopView topView;
	private Label itemName, itemDescription, itemLevel, itemQuestName;

	public QuestItemDisplayPage(PageManager pageManager)
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
		itemQuestName = new Label("");

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
	}

	public void loadDisplay(QuestItem questItem)
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

		itemName.setText(questItem.getName());
		itemName.setStyle("-fx-font-size: 24px;");
		itemName.setTextFill(questItem.getRarity().getColor());
		layout.addRow(rowIndex++, itemName);

		itemQuestName.setText("Used for " + questItem.getQuestName() + ".");
		itemQuestName.setStyle("-fx-font-size: 16px; -fx-text-fill: #11FF11;");
		layout.addRow(rowIndex++, itemQuestName);

		if (questItem.getDescription().length() > 0)
		{
			itemDescription.setText(questItem.getDescription());
			itemDescription.setStyle("-fx-font-size: 16px; -fx-text-fill: #FFEBCD;");
			itemDescription.setWrapText(true);
			GridPane.setVgrow(itemDescription, Priority.SOMETIMES);
			layout.addRow(rowIndex++, itemDescription);
		}

		itemLevel.setText("Requires Level " + questItem.getLevelReq());
		itemLevel.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemLevel);

		layout.setMaxHeight(24 + 16 * rowIndex + 20 + itemDescription.getHeight());
	}
}
// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Quest;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class QuestDisplayPage extends Page
{
	private TopView topView;
	private Label questName, npcName;
	private Text questDescription;

	public QuestDisplayPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		questName = new Label("");
		questDescription = new Text("");
		questDescription.setWrappingWidth(390);
		npcName = new Label("");

		root.setStyle("-fx-background: #000000");
		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Quest quest)
	{
		GridPane layout = new GridPane();
		layout.setMaxWidth(400);
		layout.setPadding(new Insets(10, 5, 10, 5));
		layout.setStyle("-fx-border-width: 2px; -fx-border-radius: 12px; -fx-border-color: #888888;");
		root.setCenter(layout);

		int rowIndex = 0;

		questName.setText(quest.getName());
		questName.setStyle("-fx-font-size: 24px;");
		layout.addRow(rowIndex++, questName);
		
		npcName.setText("Given by " + quest.getNpcName());
		npcName.setStyle("-fx-font-size: 16px; -fx-text-fill: #11FF11;");
		layout.addRow(rowIndex++, npcName);
		
		if(quest.getDescription().length() > 0)
		{
			questDescription.setText(quest.getDescription());
			questDescription.setStyle("-fx-font-size: 16px;");
			questDescription.setFill(Color.web("#FBFC03"));
			layout.addRow(rowIndex++, questDescription);
		}
		
		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}
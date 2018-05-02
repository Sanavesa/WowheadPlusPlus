// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Achievement;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AchievementDisplayPage extends Page
{
	private TopView topView;
	private Label achievementName, achievementPointReward;

	public AchievementDisplayPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		achievementName = new Label("");
		achievementPointReward = new Label("");

		root.setStyle("-fx-background: #000000");
		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Achievement achievement)
	{
		GridPane layout = new GridPane();
		layout.setMaxWidth(400);
		layout.setPadding(new Insets(10));
		layout.setStyle("-fx-border-width: 2px; -fx-border-radius: 12px; -fx-border-color: #888888;");
		root.setCenter(layout);

		int rowIndex = 0;

		achievementName.setText(achievement.getName());
		achievementName.setStyle("-fx-font-size: 24px;");
		layout.addRow(rowIndex++, achievementName);
		
		achievementPointReward.setText("Awards " + achievement.getPointReward() + " points");
		achievementPointReward.setStyle("-fx-font-size: 16px; -fx-text-fill: #11FF11;");
		layout.addRow(rowIndex++, achievementPointReward);
		
		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}
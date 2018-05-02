// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.Database;
import com.wowhead.database.tables.Achievement;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AddAchievementPage extends Page
{
	private TopView topView;
	private TextField achievementName, achievementPointReward;
	private GridPane gridPane;
	private Button btnAdd, btnCancel;

	public AddAchievementPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		gridPane = new GridPane();
		
		topView = new TopView(pageManager);
		achievementName = new TextField("");
		achievementPointReward = new TextField("");
		
		gridPane.setPrefWidth(500);
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		
		btnAdd = new Button("Add");
		btnAdd.setOnAction(e ->
		{
			Achievement achievement = new Achievement(
					0, 
					achievementName.getText(),
					Integer.parseInt(achievementName.getText()));
			
			Database.getInstance().addAchievement(achievement);
			resetToDefault();
			pageManager.addPage(HomePage.class);
		});
		
		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e ->
		{
			resetToDefault();
			pageManager.goBackOnePage();
		});
		
		gridPane.addRow(0, new Label("Name"), achievementName);
		gridPane.addRow(1, new Label("Point Reward"), achievementPointReward);
		gridPane.addRow(3, btnAdd, btnCancel);

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
		
		Region r1 = new Region();
		Region r2 = new Region();
		Region r3 = new Region();
		Region r4 = new Region();
		HBox.setHgrow(r1, Priority.ALWAYS);
		HBox.setHgrow(r2, Priority.ALWAYS);
		VBox.setVgrow(r3, Priority.ALWAYS);
		VBox.setVgrow(r4, Priority.ALWAYS);
		HBox centerH = new HBox(r1, gridPane, r2);
		VBox centered = new VBox(r3, centerH, r4);
		root.setCenter(centered);
	}

	public void resetToDefault()
	{
		achievementName.setText("");
		achievementPointReward.setText("");
	}
}
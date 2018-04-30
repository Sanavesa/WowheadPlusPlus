// Mohammad
package com.wowhead.gui.page;

import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ItemPage extends Page
{
	private TopView topView;
	
	public ItemPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		
		root.setStyle("-fx-background: #000000");
		
		VBox vbox = new VBox();
		Label itemName = new Label("Hand of Rag");
		Label itemStats = new Label("+1834 Strength");
		
		Button btnReturn = new Button("Go back");
		btnReturn.setOnAction(e ->
		{
			// what ever in here..
			pageManager.addPage(HomePage.class);
		});
		
//		vbox.getChildren().add(itemName);
//		vbox.getChildren().add(itemStats);
		vbox.getChildren().addAll(itemName, itemStats, btnReturn);
		
		root.setCenter(vbox);
		root.setTop(topView.getRoot());
	}
	
}
package com.wowhead.gui.page;

import com.wowhead.gui.PageManager;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HomePage3 extends Page
{
	public HomePage3(PageManager sceneManager)
	{
		super(sceneManager);
	}

	@Override
	protected void initializePage()
	{
		root = new BorderPane();
		
		VBox vbox = new VBox(10);
		root.setCenter(vbox);
		
		Label lblInfo = new Label("HomePage- 3");
		
		Button btnPrevious = new Button("Previous Page");
		btnPrevious.setOnAction(e -> sceneManager.goBackOnePage());

		vbox.getChildren().addAll(lblInfo, btnPrevious);
	}
}

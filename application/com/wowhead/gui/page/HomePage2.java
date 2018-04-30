package com.wowhead.gui.page;

import com.wowhead.gui.PageManager;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HomePage2 extends Page
{
	public HomePage2(PageManager sceneManager)
	{
		super(sceneManager);
	}

	@Override
	protected void initializePage()
	{
		root = new BorderPane();
		
		VBox vbox = new VBox(10);
		root.setCenter(vbox);
		
		Label lblInfo = new Label("HomePage- 2");
		
		TextField inp = new TextField("Empty");
		
		Button btnPrevious = new Button("Previous Page");
		btnPrevious.setOnAction(e -> sceneManager.goBackOnePage());
		
		Button btnNext = new Button("Next Page");
		btnNext.setOnAction(e -> sceneManager.addPage(HomePage3.class));

		vbox.getChildren().addAll(lblInfo, inp, btnPrevious, btnNext);
	}
}

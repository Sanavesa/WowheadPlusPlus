package com.wowhead.gui.page;

import com.wowhead.constant.Resources;
import com.wowhead.gui.PageManager;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class HomePage extends Page
{
	public HomePage(PageManager sceneManager)
	{
		super(sceneManager);
	}

	@Override
	protected void initializePage()
	{
		root = new BorderPane();
		VBox vbox = new VBox(10);
		root.setCenter(vbox);
		
		Label lblInfo = new Label("HomePage- 1");
		
		Button btnNext = new Button("Next Page");
		btnNext.setOnAction(e -> sceneManager.addPage(HomePage2.class));
		
		ImageView imgLogo = new ImageView(Resources.IMG_WOW_LOGO);

		vbox.getChildren().addAll(imgLogo, lblInfo, btnNext);
	}
}

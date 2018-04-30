// Mohammad
package com.wowhead.gui.page.view;

import com.wowhead.constant.Resources;
import com.wowhead.gui.PageManager;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TopView
{
	private final Button btnLogin, btnRegister;
	private final ImageView imgLogo;
	private final HBox root;
	private final PageManager pageManager;
	
	public TopView(PageManager pageManager)
	{
		this.pageManager = pageManager;
		imgLogo = new ImageView(Resources.IMG_WOWHEAD_LOGO);
		imgLogo.setOnMouseClicked(e -> {
			pageManager.goBackOnePage();
		});

		btnLogin = new Button("Login");
		btnRegister = new Button("Register");
		setupButton(btnLogin);
		setupButton(btnRegister);
		
		VBox accountManagement = new VBox(5, btnLogin, btnRegister);
		accountManagement.setPadding(new Insets(10));

		Region region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		Region region2 = new Region();
		HBox.setHgrow(region2, Priority.ALWAYS);
		root = new HBox(region, imgLogo, region2, accountManagement);
	}
	
	private void setupButton(Button button)
	{
		button.setFocusTraversable(false);
		button.setPrefSize(80, 30);
		button.getStyleClass().add("topViewButton");
	}
	
	public HBox getRoot()
	{
		return root;
	}
}

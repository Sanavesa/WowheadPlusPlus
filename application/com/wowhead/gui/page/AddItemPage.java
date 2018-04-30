package com.wowhead.gui.page;

import com.wowhead.constant.Resources;
import com.wowhead.gui.PageManager;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AddItemPage extends Page
{
	public AddItemPage(PageManager pageManager)
	{
		super(pageManager);
	}
	
	@Override
	protected void initializePage(){
		
		root.setStyle("-fx-background: #000000");
		
		final int imageWidth = 72;
		final int buttonWidth = 120;
		
		//Set Bottom
		Button btnReset = new Button("Reset");
		btnReset.setFocusTraversable(false);
		btnReset.setPrefSize(80, 30);					//Change location
		Button btnAdd = new Button("Add");
		btnAdd.setFocusTraversable(false);
		btnAdd.setPrefSize(80, 30);					//Change Location
		Button btnCancel = new Button("Cancel");
		btnCancel.setFocusTraversable(false);
		btnCancel.setPrefSize(80, 30); 				//Change Location
		VBox addItem = new VBox(5, btnReset, btnAdd, btnCancel);
		addItem.setPadding(new Insets(10));			//Change Location
		
		//Set Top
		ImageView imgLogo = new ImageView(Resources.IMG_WOWHEAD_LOGO);
		
		Button btnLogin = new Button("Login");
		btnLogin.setFocusTraversable(false);
		btnLogin.setPrefSize(80, 30);
		Button btnRegister = new Button("Register");
		btnRegister.setFocusTraversable(false);
		btnRegister.setPrefSize(80, 30);
		VBox accountManagement = new VBox(5, btnLogin, btnRegister);
		accountManagement.setPadding(new Insets(10));
		
		Region region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		Region region2 = new Region();
		HBox.setHgrow(region2, Priority.ALWAYS);
		HBox top = new HBox(region, imgLogo, region2, accountManagement);
		
		root.setTop(top);
		
	}
}
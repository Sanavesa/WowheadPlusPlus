// kevin
package com.wowhead.gui.page;

import com.wowhead.constant.Resources;
import com.wowhead.gui.PageManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class RegisterPage
{
	public RegisterPage(PageManager pageManager)
	{
		super(pageManager);
	}
	
	@Override
	protected void initializePage(){
		
		root.setStyle("-fx-background: #000000");
		
		final int imageWidth = 80;
		final int buttonWidth = 120;
		
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
		
		//Set Center
		Region region11 = new Region();
		HBox.setHgrow(region11, Priority.ALWAYS);
		Region region12 = new Region();
		HBox.setHgrow(region12, Priority.ALWAYS);
		ImageView imgSearch = new ImageView(Resources.IMG_SEARCH);
		imgSearch.setFitHeight(16);
		imgSearch.setPreserveRatio(true);
		TextField usernameBar = new TextField("");
		usernameBar.setPrefSize(300, 30);
		usernameBar.setPromptText("Enter username");
		TextField passwordBar = new TextField("");
		passwordBar.setPrefSize(300, 30);
		passwordBar.setPromptText("Enter password");
		Button btnCreate = new Button("", imgSearch);
		TextField passwordBar2 = new TextField("");
		passwordBar2.setPrefSize(300, 30);
		passwordBar2.setPromptText("Enter password again");
		Button btnRegister1 = new Button("", imgSearch);
		HBox centerRegister = new HBox(10, region11, usernameBar, passwordBar, passwordBar2, region12);
		BorderPane.setAlignment(centerRegister, Pos.CENTER_RIGHT);
		Region region13 = new Region();
		VBox.setVgrow(region11, Priority.ALWAYS);
		Region region14 = new Region();
		VBox.setVgrow(region13, Priority.ALWAYS);
		VBox centerVbox = new VBox(region11, centerRegister, region12);
		root.setCenter(centerVbox);
	}
}

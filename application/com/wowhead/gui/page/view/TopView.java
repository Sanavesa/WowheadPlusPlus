// Mohammad
package com.wowhead.gui.page.view;

import com.wowhead.constant.Resources;
import com.wowhead.database.Database;
import com.wowhead.database.constants.AccountRank;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.HomePage;
import com.wowhead.gui.page.LoginPage;
import com.wowhead.gui.page.RegisterPage;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TopView
{
	private final Button btnLogin, btnRegister, btnLogout;
	private final Label loggedInLabel;
	private final ImageView imgLogo;
	private final HBox root;
	private final VBox loggedOutLayout, loggedInLayout;
	private final Glow glowEffect = new Glow(1);
	
	public TopView(PageManager pageManager)
	{
		imgLogo = new ImageView(Resources.IMG_WOWHEAD_LOGO);
		
		imgLogo.setCursor(Cursor.OPEN_HAND);
		imgLogo.setPickOnBounds(true);
		imgLogo.setOnMouseEntered(e ->
		{
			imgLogo.setEffect(glowEffect);
		});
		imgLogo.setOnMouseExited(e ->
		{
			imgLogo.setEffect(null);
		});
		
		imgLogo.setOnMouseClicked(e ->
		{
			pageManager.addPage(HomePage.class);
		});

		btnLogin = new Button("Login");
		btnRegister = new Button("Register");
		btnLogout = new Button("Logout");
		setupButton(btnLogin);
		setupButton(btnRegister);
		setupButton(btnLogout);
		
		
		btnLogin.setOnAction(e ->
		{
			pageManager.addPage(LoginPage.class);
		});
		
		btnRegister.setOnAction(e ->
		{
			pageManager.addPage(RegisterPage.class);
		});
		
		btnLogout.setOnAction(e ->
		{
			Database.getInstance().logout();
		});
		
		loggedInLabel = new Label("");
		
		loggedOutLayout = new VBox(5, btnLogin, btnRegister);
		loggedOutLayout.setPadding(new Insets(10));
		
		loggedInLayout = new VBox(5, btnLogout, loggedInLabel);
		loggedInLayout.setPadding(new Insets(10));

		Region region = new Region();
		HBox.setHgrow(region, Priority.ALWAYS);
		Region region2 = new Region();
		HBox.setHgrow(region2, Priority.ALWAYS);
		root = new HBox(region, imgLogo, region2, loggedOutLayout);
		HBox.setMargin(imgLogo, new Insets(20, 0, 20, 0));
		
		Database.getInstance().accountRankProperty().addListener((args, oldRank, newRank) ->
		{
			if(newRank == AccountRank.LOGGED_OUT)
			{
				root.getChildren().set(3, loggedOutLayout);
			}
			else
			{
				root.getChildren().set(3, loggedInLayout);
				loggedInLabel.setText("Logged in as " + newRank);
			}
		});
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

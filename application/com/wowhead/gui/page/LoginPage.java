// kevin
package com.wowhead.gui.page;

import com.wowhead.database.Database;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class LoginPage extends Page
{
	private GridPane gridPane;
	private Button btnLogin;
	private TextField usernameField;
	private PasswordField passwordField;
	private Label usernameLabel, passwordLabel;
	private TopView topView;

	public LoginPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		root.setStyle("-fx-background: #000000");
		topView = new TopView(pageManager);
		
		HBox center = new HBox();
		Region r1 = new Region();
		Region r2 = new Region();
		HBox.setHgrow(r1, Priority.ALWAYS);
		HBox.setHgrow(r2, Priority.ALWAYS);
		
		VBox centeredVertically = new VBox();
		Region r3 = new Region();
		Region r4 = new Region();
		VBox.setVgrow(r3, Priority.ALWAYS);
		VBox.setVgrow(r4, Priority.ALWAYS);
		
		gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		btnLogin = new Button("Login");
		btnLogin.setPrefSize(100, 30);
		btnLogin.setOnAction(e -> login());
		btnLogin.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				login();
			}
		});
		
		usernameField = new TextField("");
		usernameField.setPrefSize(200, 30);
		usernameField.setPromptText("Enter username");
		usernameField.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				login();
			}
		});
		
		passwordField = new PasswordField();
		passwordField.setPrefSize(200, 30);
		passwordField.setPromptText("Enter password");
		passwordField.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				login();
			}
		});
		
		usernameLabel = new Label("Username:");
		usernameLabel.setStyle("-fx-font-size: 14px;");
		passwordLabel = new Label("Password:");
		passwordLabel.setStyle("-fx-font-size: 14px;");
		
		gridPane.addRow(0, usernameLabel, usernameField);
		gridPane.addRow(1, passwordLabel, passwordField);
		
		Region r5 = new Region();
		Region r6 = new Region();
		HBox.setHgrow(r5, Priority.ALWAYS);
		HBox.setHgrow(r6, Priority.ALWAYS);
		HBox loginLayout = new HBox(r5, btnLogin, r6);
		gridPane.add(loginLayout, 1, 2, 1, 1);
		
		gridPane.setPadding(new Insets(10));
		center.getChildren().addAll(r1, gridPane, r2);
		centeredVertically.getChildren().addAll(r3, center, r4);
		
		root.setCenter(centeredVertically);
		root.setTop(topView.getRoot());
	}
	
	private void login()
	{
		boolean success = Database.getInstance().login(usernameField.getText(), passwordField.getText());
		if(success)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION, "Successfully logged in!", ButtonType.OK);
			alert.showAndWait();
			usernameField.clear();
			passwordField.clear();
			pageManager.addPage(HomePage.class);
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR, "Failure logging in!", ButtonType.OK);
			alert.show();
			usernameField.clear();
			passwordField.clear();
			usernameField.requestFocus();
		}
	}
	
}

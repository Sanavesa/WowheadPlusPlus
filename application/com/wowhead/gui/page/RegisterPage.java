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

public class RegisterPage extends Page
{
	private GridPane gridPane;
	private Button btnRegister;
	private TextField usernameField;
	private PasswordField passwordField, passwordRepeatField;
	private Label usernameLabel, passwordLabel, passwordRepeatLabel;
	private TopView topView;

	public RegisterPage(PageManager pageManager)
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
		btnRegister = new Button("Register");
		btnRegister.setPrefSize(100, 30);
		btnRegister.setDisable(true);
		btnRegister.setOnAction(e -> register());
		btnRegister.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				register();
			}
		});

		usernameField = new TextField("");
		usernameField.setPrefSize(200, 30);
		usernameField.setPromptText("Enter username");
		usernameField.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				register();
			}
		});

		passwordField = new PasswordField();
		passwordField.setPrefSize(200, 30);
		passwordField.setPromptText("Enter password");
		passwordField.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				register();
			}
		});

		passwordRepeatField = new PasswordField();
		passwordRepeatField.setPrefSize(200, 30);
		passwordRepeatField.setPromptText("Enter password");
		passwordRepeatField.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				register();
			}
		});

		passwordField.textProperty().addListener((args, oldPass, newPass) ->
		{
			if (newPass.equals(passwordRepeatField.getText()))
			{
				btnRegister.setDisable(false);
			}
			else
			{
				btnRegister.setDisable(true);
			}
		});

		passwordRepeatField.textProperty().addListener((args, oldPass, newPass) ->
		{
			if (newPass.equals(passwordField.getText()))
			{
				btnRegister.setDisable(false);
			}
			else
			{
				btnRegister.setDisable(true);
			}
		});

		usernameLabel = new Label("Username:");
		usernameLabel.setStyle("-fx-font-size: 14px;");
		passwordLabel = new Label("Password:");
		passwordLabel.setStyle("-fx-font-size: 14px;");
		passwordRepeatLabel = new Label("Repeat Password:");
		passwordRepeatLabel.setStyle("-fx-font-size: 14px;");

		gridPane.addRow(0, usernameLabel, usernameField);
		gridPane.addRow(1, passwordLabel, passwordField);
		gridPane.addRow(2, passwordRepeatLabel, passwordRepeatField);
		
		Region r5 = new Region();
		Region r6 = new Region();
		HBox.setHgrow(r5, Priority.ALWAYS);
		HBox.setHgrow(r6, Priority.ALWAYS);
		HBox registerLayout = new HBox(r5, btnRegister, r6);
		gridPane.add(registerLayout, 1, 3, 1, 1);
//		gridPane.add(btnRegister, 1, 3, 1, 1);

		gridPane.setPadding(new Insets(10));
		center.getChildren().addAll(r1, gridPane, r2);
		centeredVertically.getChildren().addAll(r3, center, r4);

		root.setCenter(centeredVertically);
		root.setTop(topView.getRoot());
	}

	private void register()
	{
		boolean success = Database.getInstance().register(usernameField.getText(), passwordField.getText());
		if (success)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION, "Successfully registered and logged in!", ButtonType.OK);
			alert.showAndWait();
			usernameField.clear();
			passwordField.clear();
			passwordRepeatField.clear();
			pageManager.addPage(HomePage.class);
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR, "Failure registering!", ButtonType.OK);
			alert.show();
			usernameField.clear();
			passwordField.clear();
			passwordRepeatField.clear();
			usernameField.requestFocus();
		}
	}

}

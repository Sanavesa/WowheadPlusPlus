package com.wowhead.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// Test database connection; show display "Connected!" in console if all is good.
		// notice the ip.. its my local machine lol. DONT HAX ME OR U DED HOMIE ;)
		new Database("jdbc:mysql://142.197.65.86:3306/wowhead?useSSL=false", "cs317", "kevin&mohammad=awesome");
		
		// Load image on screen
		Image logo = new Image("res/wow_logo.png");
		ImageView logoView = new ImageView(logo);
		logoView.relocate(384, 232);
		
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 1024, 720);
		
		pane.getChildren().add(logoView);
		
		// Set caption & logo & display window
		primaryStage.setTitle("Wowhead++");
		primaryStage.getIcons().add(logo);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}

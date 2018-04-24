package com.wowhead.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	
//	@Override
//	public void start(Stage primaryStage)
//	{
//		try
//		{
//			Parent root = FXMLLoader.load(getClass().getResource("../../../fxml/mainWindow.fxml"));
//			
//			Scene scene = new Scene(root);
//			
//			System.out.println(scene.getStylesheets().size());
//			scene.getStylesheets().add(getClass().getResource("../../../fxml/ComplexApplication.css").toString());
//			System.out.println(scene.getStylesheets().size());
//			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Test Window!");
//			primaryStage.show();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
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
		
		// Test database connection; show display "Connected!" in console if all is good.
		// notice the ip.. its my local machine lol. DONT HAX ME OR U DED HOMIE ;)
		Database db = new Database("jdbc:mysql://142.197.65.86:3306/wowhead?useSSL=false", "cs317", "kevin&mohammad=awesome");
		
//		db.addWeapon("Frostmourne", 4, "chilly af", 80, 5, 3.4, 919.3, 14, 524, 32, 65, 33);
//		db.addWeapon("Hand of Ragnaros", 4, "AWESOME!", 60, 9, 3.2, 21345, 1243, 524, 653, 435, 832);
//		db.addArmor("Gladiator Breastplate", 4, "Bestest chest", 79, 3, 5, 100, 100, 50, 50, 50);
//		db.addArmor("Gladiator Breastplate22", 4, "Bestest chest", 79, 3, 5, 100, 100, 50, 50, 50);
//		db.lookupFaction(4);
		
		db.disconnect();
	}
	
}

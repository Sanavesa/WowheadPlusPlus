package com.wowhead.core;

import com.wowhead.gui.ResourceLoadPage;

import javafx.application.Application;
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
		
		new ResourceLoadPage(primaryStage);
	}
}

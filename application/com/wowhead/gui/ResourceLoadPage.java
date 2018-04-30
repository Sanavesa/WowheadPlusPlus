package com.wowhead.gui;

import com.wowhead.constant.Constants;
import com.wowhead.constant.Resources;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ResourceLoadPage
{
	public ResourceLoadPage(Stage primaryStage)
	{
		Stage loadStage = new Stage(StageStyle.UNDECORATED);
		AnchorPane root = new AnchorPane();
		
		Label progressText = new Label("0%");
		Label helperText = new Label("Loading...");
		ProgressBar progressBar = new ProgressBar();
		
		progressBar.setPrefSize(Constants.LOADING_WINDOW_WIDTH*0.75, 40);
		Resources.loadProgressProperty().addListener((args, oldVal, newVal) ->
		{
			progressBar.setProgress(newVal.doubleValue());
			int value = (int)Math.round(newVal.doubleValue()*100);
			progressText.setText(value + "%");
			
			if(value >= 100)
			{
				// done!
				loadStage.close();
				try
				{
					new PageManager(primaryStage);
				}
				catch (InstantiationException | IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		Platform.runLater(() ->
		{
			helperText.relocate((Constants.LOADING_WINDOW_WIDTH-helperText.getWidth())/2, (Constants.LOADING_WINDOW_HEIGHT-helperText.getHeight())/2 - 30);
			progressBar.relocate((Constants.LOADING_WINDOW_WIDTH-progressBar.getWidth())/2, (Constants.LOADING_WINDOW_HEIGHT-progressBar.getHeight())/2);
			progressText.relocate((Constants.LOADING_WINDOW_WIDTH-progressText.getWidth())/2, (Constants.LOADING_WINDOW_HEIGHT-progressText.getHeight())/2);
		});
		
		root.getChildren().addAll(progressBar, helperText, progressText);
		
		Scene scene = new Scene(root, Constants.LOADING_WINDOW_WIDTH, Constants.LOADING_WINDOW_HEIGHT);
		scene.getStylesheets().add(Constants.LOADING_WINDOW_STYLESHEET);
		loadStage.setResizable(false);
		loadStage.setScene(scene);
		loadStage.show();
	}
}

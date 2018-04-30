// Mohammad
package com.wowhead.gui.page;

import com.wowhead.constant.Resources;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.DatabaseSelectionView;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HomePage extends Page
{
	private DatabaseSelectionView databaseSelectionView;
	private TopView topView;
	
	public HomePage(PageManager sceneManager)
	{
		super(sceneManager);
	}

	@Override
	protected void initializePage()
	{
		root.setStyle("-fx-background: #000000");

		// Setup left
		databaseSelectionView = new DatabaseSelectionView();
		root.setLeft(databaseSelectionView.getToolBarDatabase());
		
		// Setup top
		topView = new TopView(pageManager);
		root.setTop(topView.getRoot());

		// Setup center of the rest
		Region region3 = new Region();
		HBox.setHgrow(region3, Priority.ALWAYS);
		Region region4 = new Region();
		HBox.setHgrow(region4, Priority.ALWAYS);
		TextField searchBar = new TextField("");
		searchBar.setPrefSize(300, 30);
		searchBar.setPromptText("Enter search criteria");
		ImageView imgSearch = new ImageView(Resources.IMG_SEARCH);
		imgSearch.setFitHeight(16);
		imgSearch.setPreserveRatio(true);
		Button btnSearch = new Button("", imgSearch);
		HBox center = new HBox(10, region3, searchBar, btnSearch, region4);
		BorderPane.setAlignment(center, Pos.CENTER_RIGHT);
		Region region5 = new Region();
		VBox.setVgrow(region5, Priority.ALWAYS);
		Region region6 = new Region();
		VBox.setVgrow(region6, Priority.ALWAYS);
		VBox centerVbox = new VBox(region5, center, region6);
		root.setCenter(centerVbox);
	}
}

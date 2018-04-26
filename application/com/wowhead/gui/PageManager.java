package com.wowhead.gui;

import java.util.HashMap;
import java.util.Stack;

import com.wowhead.constant.Constants;
import com.wowhead.constant.Resources;
import com.wowhead.gui.page.Page;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PageManager
{
	private final Stage stage;
	private final Scene scene;
	private Stack<Page> pageStack = new Stack<>();
	private HashMap<Class<? extends Page>, Page> cachedPages = new HashMap<>();
	
	public PageManager(Stage stage) throws InstantiationException, IllegalAccessException
	{
		this.stage = stage;
		
		// Load startup page and pass in this object as parameter for future reference
		Page startupPage = Page.createPage(Constants.WINDOW_STARTUP_PAGE, this);
		pageStack.push(startupPage);
		cachedPages.put(Constants.WINDOW_STARTUP_PAGE, startupPage);
		
		// Initialize scene with defaults
		scene = new Scene(startupPage.getRoot(), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, Constants.WINDOW_BACKGROUND_COLOR);
		scene.getStylesheets().add(Constants.WINDOW_STYLESHEET);
		
		// Set caption and logo
		stage.setTitle(Constants.WINDOW_CAPTION);
		stage.getIcons().add(Resources.IMG_WOW_LOGO);
		
		// Display window
		stage.setScene(scene);
		stage.show();
		
		// Hook ESC to close application
		scene.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ESCAPE)
				Platform.exit();
		});
	}

	public Stage getStage()
	{
		return stage;
	}

	public Scene getScene()
	{
		return scene;
	}
	
	public boolean goBackOnePage()
	{
		if(pageStack.size() <= 1)
			return false;
		
		pageStack.pop();
		Page newCurrentPage = pageStack.peek();
		scene.setRoot(newCurrentPage.getRoot());
		return true;
	}
	
	public Page getCurrentPage()
	{
		if(pageStack.isEmpty())
			return null;
		return pageStack.peek();
	}
	
	private void addPage(Page nextPage)
	{
		pageStack.add(nextPage);
		scene.setRoot(nextPage.getRoot());
	}
	
	public Page addPage(Class<? extends Page> clazz)
	{
		if(cachedPages.containsKey(clazz))
		{
			// Don't instantiate, we already have an old copy!
			Page nextPage = cachedPages.get(clazz);
			addPage(nextPage);
			return nextPage;
		}
		else
		{
			// Instantiate a new copy
			Page nextPage = Page.createPage(clazz, this);
			addPage(nextPage);
			cachedPages.put(clazz, nextPage);
			return nextPage;	
		}
	}
}

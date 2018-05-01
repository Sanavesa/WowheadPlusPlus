package com.wowhead.constant;

import com.wowhead.gui.page.AddItemPage;
import com.wowhead.gui.page.HomePage;
import com.wowhead.gui.page.Page;

import javafx.scene.paint.Color;

public final class Constants
{
	// Private constructor to prevent instantiation
	private Constants() { }
	
	// Loading Window Defaults
	public static final int LOADING_WINDOW_WIDTH = 240;
	public static final int LOADING_WINDOW_HEIGHT = 100;
	public static final String LOADING_WINDOW_STYLESHEET = "stylesheets/loadingPage.css";
	
	// Program Window Defaults
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 720;
	public static final Color WINDOW_BACKGROUND_COLOR = Color.RED;
	public static final String WINDOW_CAPTION = "Wowhead++";
	public static final Class<? extends Page> WINDOW_STARTUP_PAGE = ItemPage.class;
	public static final String WINDOW_STYLESHEET = "stylesheets/application.css";
}

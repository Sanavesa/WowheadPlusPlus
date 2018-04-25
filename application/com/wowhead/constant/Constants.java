package com.wowhead.constant;

import com.wowhead.gui.page.HomePage;
import com.wowhead.gui.page.Page;

import javafx.scene.paint.Color;

public final class Constants
{
	// Private constructor to prevent instantiation
	private Constants() { }
	
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 720;
	public static final Color WINDOW_BACKGROUND_COLOR = Color.WHITE;
	public static final String WINDOW_CAPTION = "Wowhead++";
	public static final Class<? extends Page> WINDOW_STARTUP_PAGE = HomePage.class;
}

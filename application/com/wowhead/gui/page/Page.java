package com.wowhead.gui.page;

import java.lang.reflect.InvocationTargetException;

import com.wowhead.gui.PageManager;

import javafx.scene.layout.BorderPane;

public abstract class Page
{
	protected BorderPane root = null;
	protected final PageManager pageManager;
	
	public Page(PageManager pageManager)
	{
		this.pageManager = pageManager;
		initializePage();
	}
	
	protected abstract void initializePage();
	
	public final BorderPane getRoot()
	{
		return root;
	}

	public final PageManager getPageManager()
	{
		return pageManager;
	}
	
	@SuppressWarnings("unchecked")
	public final static <T extends Page> T createPage(Class<? extends Page> clazz, Object... parameters)
	{
		Class<? extends Object>[] cArg = new Class[parameters.length];
		for(int i = 0; i < parameters.length; i++)
		{
			cArg[i] = parameters[i].getClass();
		}
		
		try
		{
			return (T) clazz.getDeclaredConstructor(cArg).newInstance(parameters);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}

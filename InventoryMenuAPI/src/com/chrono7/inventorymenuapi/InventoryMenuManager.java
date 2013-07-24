package com.chrono7.inventorymenuapi;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryMenuManager extends JavaPlugin
{
	private static HashMap<String, InventoryMenu> watchedMenus = new HashMap<String, InventoryMenu>(); 

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
	}

	protected static void watchMenu(InventoryMenu inventoryMenu, Player player) 
	{
		watchedMenus.put(player.getName(), inventoryMenu);
	}
	
	protected static boolean isPlayerWatched(Player player)
	{
		return watchedMenus.containsKey(player.getName());
	}
	
	protected static InventoryMenu getWatchedMenu(Player player)
	{
		return watchedMenus.get(player.getName());
	}

	protected static void unWatch(Player player) 
	{
		watchedMenus.remove(player.getName());
	}
}

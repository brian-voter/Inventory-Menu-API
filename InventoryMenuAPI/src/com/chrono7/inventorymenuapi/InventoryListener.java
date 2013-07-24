package com.chrono7.inventorymenuapi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener{

	@EventHandler
	private void onInventoryClickEvent(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();

		//Make sure that the player is being watched before continuing
		if (!InventoryMenuManager.isPlayerWatched(player))
			return;

		//Cancel the event to prevent players from adding/removing items to the menu
		event.setCancelled(true);

		//Get the menu we should be watching for
		InventoryMenu im = InventoryMenuManager.getWatchedMenu(player);

		//Make sure that the menu exists before continuing
		if (im == null)
			return;

		//Make sure that we have the right inventory before continuing
		if (!im.getTitle().equals(event.getInventory().getTitle()))
			return;

		if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
		{
			//Get the matching option for the event, via the clicked item
			Option o = im.matchOption(event.getCurrentItem());

			//Make sure the option exists before continuing
			if (o != null)
			{
				//Execute the option's code and close the menu
				o.execute(player);
				event.getWhoClicked().closeInventory();
			}
		}
	}
	
	@EventHandler
	private void onInventoryClose(InventoryCloseEvent event)
	{
		Player player = (Player) event.getPlayer();
		
		if (InventoryMenuManager.isPlayerWatched(player))
		{
			InventoryMenuManager.unWatch(player);
		}
	}
}

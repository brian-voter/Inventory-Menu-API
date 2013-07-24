package com.chrono7.inventorymenuapi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryMenu {
	
	private String title;
	private List<Option> options = new ArrayList<Option>();
	private Inventory inventory = null;
	
	/**Construct a new InventoryMenu with a title.
	 * 
	 * @param title The title of the InventoryMenu
	 */
	public InventoryMenu(String title)
	{
		this.title = title;
	}
	
	/**Adds an option to this InventoryMenu.
	 * 
	 * @param option The option
	 */
	public void addOption(Option option)
	{
		options.add(option);
		buildInventory();
	}
	
	/**Constructs the inventory itself. This method is called
	 * whenever an option is added, to update the menu when needed.
	 */
	private void buildInventory() 
	{
		inventory = null;
		
		int slots = 9;
		int needed = options.size();
		
		if (options.size() % 9 == 0)
		{
			slots = Math.max(9, options.size());
		}
		else
		{
			for (int x = 1; x <= 6; x++)
			{
				int attempt = x * 9;
				
				if (needed < attempt)
				{
					slots = attempt;
					break;
				}
			}
		}
		
		inventory = Bukkit.getServer().createInventory(null, slots, title);
		
		for (Option o : options)
		{
			inventory.addItem(o.getIcon().getItem());
		}
	}

	/**Gets the title of the menu
	 * 
	 * @return The title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**Selects an option to execute, from the ItemStack.
	 * The Option's ItemStack is compared with equals() to the
	 * passed in item, usually the item clicked in an event.
	 * 
	 * @param item The ItemStack to match
	 * @return The corresponding option
	 */
	protected Option matchOption(ItemStack item)
	{
		for (Option o : options)
		{
			if (o.getIcon().itemEquals(item))
			{
				return o;
			}
		}
		
		return null;
	}
	
	/**Shows the player the InventoryMenu, and listens for the player to
	 * click an option in the menu
	 * 
	 * @param player The player
	 */
	public void showInventory(Player player)
	{
		player.closeInventory();
		InventoryMenuManager.watchMenu(this, player);
		player.openInventory(inventory);
	}
}

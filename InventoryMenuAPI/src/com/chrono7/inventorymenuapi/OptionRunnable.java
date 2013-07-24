package com.chrono7.inventorymenuapi;

import org.bukkit.entity.Player;

public interface OptionRunnable {
	
	/**This code is executed when the option is selected
	 * by the player in an InventoryMenu
	 * 
	 * @param player
	 */
	public void run(Player player);
	
}

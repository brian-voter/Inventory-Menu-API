package com.chrono7.inventorymenuapi;
import org.bukkit.entity.Player;

import com.chrono7.inventorymenuapi.icon.Icon;

public class Option{
	
	private Icon icon;
	private OptionRunnable task;
	
	/**Construct a new Option with a name, a task, and an icon.
	 * 
	 * @param task The OptionRunnable that will be executed when a player selects this option
	 * @param icon The Icon for the option.
	 */
	public Option(OptionRunnable task, Icon icon)
	{
		this.task = task;
		this.icon = icon;
	}
	
	/**Returns the Icon for the Option.
	 * 
	 * @return The icon for the option
	 */
	public Icon getIcon()
	{
		return icon;
	}
	
	/**This code is executed when the option is selected by a player in the menu.
	 * 
	 * @param player The player who clicked the option
	 */
	protected void execute(Player player)
	{
		task.run(player);
	}
}

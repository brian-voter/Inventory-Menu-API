package com.chrono7.inventorymenuapi.icon;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IconBuilder {
	
	/**Constructs a new Icon for display with an Option in an InventoryMenu
	 * 
	 * @param name The item name of the icon
	 * @param material The Material the item
	 * @param damage The damage value of item, to be converted to a byte. Usually 0.
	 * @param data The data value of the item, to be converted to a byte. Usually 0.
	 * @return The new Icon
	 */
	@SuppressWarnings("deprecation")
	public static Icon buildIcon(String name, Material material, int data, int damage)
	{
		ItemStack item = new ItemStack(material, 1, (short) damage, (byte) data);
		
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(im);
		
		return new Icon(item);
	}
	
	/**Constructs a new Icon for display with an Option in an InventoryMenu
	 * 
	 * @param name The item name of the icon
	 * @param material The Material of the item
	 * @return The new Icon
	 */
	public static Icon buildIcon(String name, Material material)
	{
		return buildIcon(name, material, 0, 0);
	}
	
	/**Constructs a new Icon for display with an Option in an InventoryMenu. 
	 * The item material will in this case be Wool.
	 * 
	 * @param name The item name of the icon
	 * @param woolColor The color of the wool for the item.
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Icon buildIcon(String name, DyeColor woolColor)
	{
		return buildIcon(name, Material.WOOL, woolColor.getData(), 0);
	}
	
}

package com.chrono7.inventorymenuapi.icon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Icon {
	
	private ItemStack item;
	private String name;
	private String description;
	private String appendlessDescription;
	
	protected Icon(ItemStack item)
	{
		this.item = item;
		this.name = item.getItemMeta().getDisplayName();
	}
	
	/**Returns whether or not the ItemStack held in the icon is equal to the
	 * specified ItemStack, using equals()
	 * 
	 * @param item The ItemStack to compare
	 * @return Whether or not they are equal
	 */
	public boolean itemEquals(ItemStack item)
	{
		return this.item.equals(item);
	}
	
	/**Gets the name of the icon
	 * 
	 * @return The name
	 */
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(im);
		this.name = name;
	}
	
	/**Sets the description of the Icon, using item lore. This 
	 * description will be displayed to players when they hover
	 * their mouse over this option. The description is formatted into
	 * lines with a maximum length of 26 characters.
	 * @param description The new Description
	 * @return The same icon, for ease in construction.
	 */
	public Icon setDescription(String description)
	{
		this.description = description;
		this.appendlessDescription = description;
		
		ItemMeta im = item.getItemMeta();
		
		//Create an ArrayList to hold the lore that will be added to the item
		ArrayList<String> lore = new ArrayList<String>();
		
		//Split the description by spaces
		String[] split = description.split(" ");

		//Loop through the array, appending each word to the line if the resulting line
		//length is not greater than 26 characters
		String line = "";
		for (int x = 0; x < split.length; x++)
		{
			String next = split[x];
			
			//If the new line length is too long, add the old line to the lore
			//list and reiterate over the element.
			if (next.length() + line.length() > 26)
			{
				lore.add(line);
				line = "";
				x--;
			}
			//If this word is the last word in the description, add it to the line
			//and add the line to the lore list.
			else if (x == split.length - 1)
			{
				line += next;
				lore.add(line);
			}
			else
			{
				//Otherwise, simply add the word to the existing line.
				line += next  + " ";
			}
		}

		//Set the ItemMeta lore to the lore list generated above.
		im.setLore(lore);
		
		//Assign the modified ItemMeta to the ItemStack
		item.setItemMeta(im);
		
		return this;
	}
	
	/**Appends a new line to the description, without formatting it into multiple lines.
	 * The normal formatting line character limit is 26, so attempt to follow that standard.
	 * 
	 * @param append The line to append to the description
	 * @return The same icon, for ease in construction
	 */
	public Icon appendLineDescription(String append)
	{
		description += " " + append;
		
		ItemMeta im = item.getItemMeta();
		
		List<String> lore = im.getLore();
		
		if (lore == null)
			lore = new ArrayList<String>();
		
		lore.add(append);
		im.setLore(lore);
		
		item.setItemMeta(im);
		
		return this;
	}
	
	/**Gets the String description for the icon. Line breaks are not included
	 * here.
	 * This description includes any appendments that may have been made.
	 * 
	 * @return The description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**Gets the description without any manual appendments that may have been made.
	 * Line breaks are not included here.
	 * 
	 * @return The unappended description
	 */
	public String getAppendlessDescription()
	{
		return appendlessDescription;
	}

	/**Gets the ItemStack stored in the icon
	 * 
	 * @return The ItemStack
	 */
	public ItemStack getItem() 
	{
		return item;
	}
	
}

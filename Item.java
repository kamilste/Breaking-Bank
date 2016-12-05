import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class Item - an item in an adventure game.
 * 
 * @author Kamil Stepien 
 * @version 12/01/15
 */
public class Item
{
    private String name;
    private String description;
    private int weight;

    /**
     * Create a new item with the given description and weight.
     * @param name The item's name
     * @param description The item's description
     * @param weight The item's weight
     */
    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    /** 
     * Delete this next method if unsuccessful
     */
    
    private void addItems(Item item)
    {
        this.addItems(item);
    }
    
    private void removeItems(Item item)
    {
        item = null;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public String getName()
    {
        return name;
    }
}

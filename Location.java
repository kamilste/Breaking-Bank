import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Location - a room in an adventure game.
 * 
 * This class is part of the "Braking Bank" application.
 * "Braking Bank" is very simple, text based adventure game.
 * 
 * A "Location" represents one place in the scenery of the game. 
 * It is connected to other rooms via exits.
 * For each existing exit, the location stores a reference to the neighboring location.
 * 
 * @author Kamil Stepien 
 * @version 12/01/15
 */
public class Location
{
    private String description;
    private HashMap<String, Location> exits;
    
    private String weight;    
    private Items items;
    
    /**
     * Create a location described "description".
     * Initially, it has no exits.
     * "description" is something like "a guard" or "oustide".
     * @param description The location's descripion.
     */
    
    public Location(String description)
    {
        this.description = description;
        exits = new HashMap<String, Location>();
        
        items = new Items();
    }
    
    /**
     * Define an exit from this location.
     * @param direction The direction of the exit.
     * @param neighbor The location to which the exit leads.
     */
    
    public void setExit(String direction, Location neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    public Location getExit(String direction)
    {
        return exits.get(direction);
    }
    
    public void addItem(Item item)
    {
        items.put(item.getName(), item);
    }
    
    public Item removeItem(String name)
    {
        return items.remove(name);
    }
    
    /**
     * @return The short description of the location
     * (the one that was defined in the constructor).
     */
    
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Return a description of the location in the form:
     *  You are in the inside corridor.
     *  Exits: north west
     *@return A long description of this location
     */
    
    public String getLongDescription()
    {
        return "You are located " + description + ".\n" + getExitString() + "\nItems in the room: " + items.getLongDescription();        
    }
    
    /**
     * Return a string describing the location's exits, for example
     * "Exits north west".
     * @return Details of the location's exits.
     */
    
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys)
        {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return the location that is reached if we go from this location in direction "direction".
     * If there is no location in that direction, return null.
     * @param direction That exit's direction.
     * @return The location in the given direction.
    
    
    public Location getExit(String direction)
    {
        return exits.get(direction);
    }
    */
}

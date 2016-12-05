import java.util.Stack;

/**
 * This class is the main class of the "Braking Bank" game.
 * "Braking Bank" is a very simple. ext based adventure game. 
 * Users can walk around some scenery, pick up and drop items find the way to steal the money and run away safely.
 * 
 * To play this game, create an instance of game class and call the "play" method.
 * 
 * This class creates different methods for a player class which are used in other classes.
 * 
 * @author Kamil Stepien
 * @version 12/01/15
 */
public class Player
{
    public Location currentLocation;

    //player's name
    private String name;
    private int health = 100;
    private int attackDamage = 100;

    //locations where player has been.
    private Stack<Location> locationHistory;

    private Items items = new Items();
    
    /**
     * Constructor for objects of class Player.
     */

    public Player(String name)
    {
        this.name = name;
        currentLocation = null;
        locationHistory = new Stack<Location>();
    }

    /**
     * Saving players locations.
     * This is later used for location history in the back button.
     */
    public void storeLocation(Location currentLocation)
    {
        this.currentLocation = currentLocation;
    }

    /**
     * Getter for the name of the player.
     */
    public String getName()
    {
        return name;
    }

    /**
     * getter for the current location.
     */
    public Location getCurrentLocation()
    {
        return currentLocation;
    }

    /**
     * This stores continous rooms that player is entering.
     */
    
    public void enterLocation(Location location)
    {
        if (currentLocation != null)
        {
            locationHistory.push(currentLocation);
        }
        currentLocation = location;
    }

    /**
     * This is another function to deal with history of player locations.
     * this checks what was the previous location of the player.
     * function is called in the game class.
     */
    
    public boolean goBack()
    {
        if (locationHistory.isEmpty())
        {
            return false;
        }
        else
        {
            Location previousLocation = locationHistory.pop();
            currentLocation = previousLocation;
        }

        return true;
    }

    /**
     * It returns the details about current location to the user.
     */
    
    public String getLongDescription()
    {
        String returnString = currentLocation.getLongDescription();
        returnString += "\n" + getItemsString();

        return returnString;
    }

    /**
     * This message is used to show the user what he is carrying.
     */
    
    public String getItemsString()
    {
        return "You are carrying: " + items.getLongDescription();
    }

    public Item pickUpItem(String itemName)
    {
        Item item = currentLocation.removeItem(itemName);
        if (item != null) 
        {
            items.put(itemName, item);
        }
        return item;
    }
    
    public Item dropItem(String itemName)
    {
        Item item = items.remove(itemName);
        if (item != null)
        {
            currentLocation.addItem(item);
        }
        return item;
    }
}

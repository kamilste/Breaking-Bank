/**
 * This class is the main class of the "Barking Bank" game.
 * "Braking Bank" is a very simple. ext based adventure game. 
 * Users can walk around some scenery, pick up and drop items, find the way to steal the money and run away safely.
 * 
 * To play this game, create an instance of this class and call the "play" method.
 * 
 * This main class creates and initialises all the others: it crates all rooms, creastes the parser and starts the game.
 * It also evalutes and executes the commands that the praser returns.
 * 
 * @author Kamil Stepien
 * @version 12/01/15
 */

public class Game
{
    private Parser parser;
    private Player player;

    private Items inventoryItem;

    /**
     * Create the game and initalise its internal map.
     */
    public Game()
    {
        Location startLocation = createLocations();
        parser = new Parser();
        player = new Player("Kamil");
        player.enterLocation(startLocation);

        inventoryItem = new Items(); // place to store player's items
    }

    /**
     * Create all the locations and link their exits together.
     */
    private Location createLocations()
    {
        Location outside, insideCorridor, ammunitionRoom, guard, alarmRoom, cash;

        //create the rooms
        outside = new Location("outside the bank");
        insideCorridor = new Location("inside");
        ammunitionRoom = new Location("in ammunition room");
        guard = new Location("next to a guard");
        alarmRoom = new Location("in alarm room");
        cash = new Location("where the cash is");

        //create the items
        Item pistol, submachine, knife, rifle, money;
        pistol = new Item("pistol", "pistol", 2);
        submachine = new Item("submachine", "submachine", 3);
        knife = new Item("knife", "knife", 1);
        rifle = new Item("rifle", "rifle", 4);
        money = new Item("money", "money", 1);
     
        
        //initialise room exits
        outside.setExit("north", insideCorridor);

        insideCorridor.setExit("north", guard);
        insideCorridor.setExit("south", outside);
        insideCorridor.setExit("west", ammunitionRoom);

        ammunitionRoom.setExit("east", insideCorridor);

        guard.setExit("east", alarmRoom);
        guard.setExit("south", insideCorridor);

        alarmRoom.setExit("south", cash);
        alarmRoom.setExit("west", guard);

        cash.setExit("north", alarmRoom);

        //adding the items to the rooms
        ammunitionRoom.addItem(pistol);
        ammunitionRoom.addItem(submachine);
        ammunitionRoom.addItem(knife);
        ammunitionRoom.addItem(rifle);
        cash.addItem(money);

        //start game inside
        return insideCorridor;
    }

    /**
     * Main play routine. Loops until end of game.
     */

    public void play()
    {
        printWelcome();

        //Enter the main commad Loop.
        //Here we repeatedly read commands and execute them until the game is over.
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Braking the Bank Game");
        System.out.println("This version of the game is less boring than original World of Zuul");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getLongDescription());
    }

    /**
     * Givem a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */

    private boolean processCommand (Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord)
        {
            case UNKNOWN:
            System.out.println("I don't know what you mean...");
            break;

            case HELP:
            printHelp();
            break;

            case GO:
            goLocation(command);
            break;

            case PICK:
            pick(command);
            break;

            case DROP:
            drop(command);
            break;

            case BACK:
            back(command);
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;
        }

        return wantToQuit;
    }

    //implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and list of the command words.
     */

    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the bank.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();        
    }

    /**
     * Try to go in one direction.
     * If there us an exit, enter new room, otherwise print an error message.
     */

    private void goLocation(Command command)
    {
        if(!command.hasSecondWord())
        {
            //If there is no second word, we don't know where to go...
            System.out.println("Go where");
            return;
        }

        String direction = command.getSecondWord();

        //Try to leave current room.
        Location nextLocation = player.getCurrentLocation().getExit(direction);

        if (nextLocation == null)
        {
            System.out.println("There is no door!");
        }
        else
        {
            player.enterLocation(nextLocation);
            System.out.println(player.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. 
     * Check the rest of the command to see whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */

    private boolean quit(Command command)
    {
        if(command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        }
        else
        {
            //signal that we want to quit
            return true;
        }
    }

    /**
     * This is function created to pick up items by the player
     * It checks if there is command properly typed and the if it isin't then an message shows up.
     * Otherwise the item is picked.
     */
    
    private void pick(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Pick what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.pickUpItem(itemName);
        
        if (item == null)
        {
            System.out.println("Can't pick up the item: " + itemName);
        }
        else
        {
            System.out.println("Picked up " + item.getDescription());
        }
    }

    /**
     * This function allows to drop the item.
     * It checks if there is command properly typed and the if it isin't then an message shows up.
     * Otherwise the item is droped.
     */
    
    private void drop(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        
        Item item = player.dropItem(itemName);
        
        if (item == null) 
        {
            System.out.println("You don't carry the item: " + itemName);
        }
        else
        {
            System.out.println("Dropped " + item.getDescription());
        }
    }

    /**
     * This function allows player to come back to a start of the game.
     * Each time back is typed the game gets back by one step.
     */
    
    private void back(Command command)
    {
        if(player.goBack())
        {
            System.out.println(player.getLongDescription());
        }
        else
        {
            System.out.println("You can't go back any further");
        }
    }
    
   // private void gameover()
    //{
      //  if(player.getCurrentLocation(outside) && and (item = "cash"))
        //{
          //  System.out.println("Congratulations you runned away with money");
        //}
    //}
    
    
}

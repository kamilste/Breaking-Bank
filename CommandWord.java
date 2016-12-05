/**
 * Representations for all the valid command words for the game along with a string in a particular language.
 * 
 * @author Kamil Stepien
 * @version 12/01/15
 */
public enum CommandWord
{
    //A value for each command word along with its
    //corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), PICK("pick"), DROP("drop"), BACK("back");
    
    //The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    
    public String toString()
    {
        return commandString;
    }
}
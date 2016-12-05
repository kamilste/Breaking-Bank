import java.util.Scanner;

/**
 * This class is part of the "Braking Bank" application.
 * "Braking Bank" is very simple, text based adventure game.
 * 
 * This parser reads user input and tries to interpret it as an "Adventure" command.
 * Every time it it called it reads a line from the terminal and tries to interpret the line as a two-word command.
 * It returns the command as an object of class Command.
 * 
 * The paraser has a set of known command words.
 * It checks user input against the known commands, and if the input is not one of the known commands, it returns a command object that is marked as an unknown command.
 * 
 * @author Kamil Stepien
 * @version 12/01/15
 */
public class Parser
{
    //holds all valid command words
    private CommandWords commands;
    
    // source of command input
    private Scanner reader;
    
    /**
     * Create a parser to read from the terminal window.
     */
    
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }
    
    /**
     * @return The next command from the user.
     */
    
    public Command getCommand()
    {
        //will hold the full input line
        String inputLine;
        
        String word1 = null;
        String word2 = null;
        
        //print prompt
        System.out.print("> ");
        
        inputLine = reader.nextLine();
        
        //Find up to two words of the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext())
        {
            //get first word
            word1 = tokenizer.next();
            if(tokenizer.hasNext())
            {
                //get second word
                word2 = tokenizer.next();
                
                //note: we just ignore the rest of the input line.
            }
        }
        
        return new Command(commands.getCommandWord(word1), word2);
    }
    
    /**
     * Print out list of valid command words.
     */
    
    public void showCommands()
    {
        commands.showAll();
    }
}

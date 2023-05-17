// --== CS400 Spring 2023 File Header Information ==--
// Name: Sreya Sarathy
// Email: sarathy2@wisc.edu
// Team: DT
// TA: Daniel Finer
// Lecturer: Florian Heimerl (004)
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the frontend interface for an NBA team data management system.
 * It provides a user interface for users to interact with the system
 * and perform various operations on NBA teams and players.
 */


public class NBATeamFrontend implements NBATeamFrontendInterface {
    private Scanner userInput;
    private NBATeamBackendInterface backend;
    private int capacity;

    /**
     * Constructs a new NBA Team Frontend instance with the specified user input and backend.
     * The user input is used to prompt the user for input, and the backend is used to handle the data
     * and execute commands.
     *
     * @param userInput The Scanner object used to prompt the user for input.
     * @param backend   The backend object that handles the data and executes commands.
     */

    public NBATeamFrontend(Scanner userInput, NBATeamBackendInterface backend) {
        this.userInput = userInput;
        this.backend = backend;
        this.capacity = 15;
    }


    /**
     * Helper method to display a 79 column wide row of dashes: a horizontal rule.
     */
    private void hr() {
        System.out.println("-------------------------------------------------------------------------------");
    }

    /**
     * This method overrides the superclass method to load data from the Data Wrangler.
     * It loads data that is used to populate the list of players and their statistics.
     * However, if the data does not exist then a fileNotFoundException is thrown.
     */
    public void loadDataCommand() {
        System.out.print("Enter the name of the file to load: ");
        String filename = userInput.nextLine().trim();
        try {
            backend.loadData(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find or load file: " + filename);
        }
    }

    /**
     * Prints the command options to System.out and reads user's choice through
     * userInput scanner.
     */
    public char mainMenuPrompt() {
        hr();
        System.out.println("Choose a command from the following list to Make Your Own NBA Team:");
        System.out.println("[L]oad data for your NBA Team ");
        System.out.println("[C]reate team");
        System.out.println("Search for player by [N]ame");
        System.out.println("[D]isplay statistics for a player");
        System.out.println("[R]emove a player from your team");
        System.out.println("Display entire [T]eam along with its statistics");
        System.out.println("[Q]uit Application");

        System.out.println("Please choose your respective command: ");
        String toSearch = userInput.nextLine().trim();
        if (toSearch.length() != 1) {
            System.out.println("Invalid entry. Please try again.");
            hr();
            return '\0';
        }
        return Character.toUpperCase(toSearch.charAt(0));
    }


    /**
     * Displays dataset statistics to System.out.
     */
    @Override
    public void displayStatsCommand(String name) {

        System.out.println(backend.getStatisticsString(name));

    }


    /**
     * This loop repeated prompts the user for commands and displays appropriate
     * feedback for each. This continues until the user enters 'q' to quit.
     */
    public void runCommandLoop() {
        hr(); // display welcome message
        System.out.println("Welcome to the make your own NBA Team App!");
        hr();

        List<String> players = null;
        char commandByUser = '\0';
        while (commandByUser != 'Q') { // main loop continues until user chooses to quit
            commandByUser = this.mainMenuPrompt();
            switch (commandByUser) {
                case 'L': //System.out.println("[L]oad data for your NBA Team");
                    loadDataCommand();
                    break;

                case 'C': //System.out.println("[C]reate team");
                    createTeam();
                    break;

                case 'N':  //System.out.println("Search for player by [N]ame");
                    System.out.println("Enter the name of the player you want to search:");
                    Scanner scanner = new Scanner(System.in);
                    String playerName = scanner.nextLine();
                    searchName(playerName);
                    break;

                case 'D':  //System.out.println("[D]isplay statistics for a player");
                    System.out.println("Enter the player whose statistics you want to display");
                    String user = userInput.nextLine();
                    displayStatsCommand(user);
                    break;

                case 'R': // //System.out.println("[R]emove a player from your team");
                    removeTeam();
                    break;

                case 'T': //System.out.println("Display entire [T]eam");
                    printTeam();
                    break;

                case 'Q': // System.out.println(" [Q]uit");
                    // do nothing, containing loop condition will fail
                    break;

                default:
                    System.out.println(
                            "Didn't recognize that command.  " +
                                    "Please type one of the letters presented within []s to identify the command you would like to choose.");
                    break;
            }
        }

        hr(); // thank user before ending this application
        System.out.println("Thank you for using the make your own NBA Team App!");
        hr();
    }


    /**
     * This method allows the user to create their own NBA team by selecting players from a list of available
     * players.The method first gets the list of available players from the backend and then prompts
     * the user to select players one by one. The maximum number of players that can be selected is 15.
     * Once the user has selected 15 players, the method displays the final team and returns the list
     * of selected players.
     */
    @Override
    public void createTeam() {
        System.out.println("Creating team:");
        Scanner scanner = new Scanner(System.in);
        boolean cond = true;
        while (cond) {
            System.out.print("Enter player name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                cond = false;
                break;
            }
            boolean addPlayer = backend.addPlayer(name);

            if (addPlayer == false) {
                System.out.println("Add not successful, " + name + " not found in the player database");
            } else {
                System.out.println(name + " added successfully.");
            }

        }
    }

    /**
     * This method allows users to remove a player from their team.
     * Uses the Algorithm Engineer's Remove Method.
     */
    public void removeTeam() {
        System.out.println("Player to be removed: ");
        Scanner scanner = new Scanner(System.in);
        boolean cond = true;
        while (cond) {
            System.out.print("Enter player name to be removed (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                cond = false;
                break;
            }
            boolean removePlayer = backend.removePlayer(name);

            if (removePlayer == false) {
                System.out.println("Remove not succesful for" + name);
            } else {
                System.out.println(name + " removed successfully.");
            }
        }
    }


    /**
     * Checks if a player with the given name exists in the team.
     *
     * @param name the name of the player to search for
     * @return true if a player with the given name exists in the team, false otherwise
     */
    public void searchName(String name) {
        NBAPlayerInterface NBAPlayer = backend.findNBAPlayerByName(name);
        if (NBAPlayer != null) {
            System.out.println("Player: " + NBAPlayer.getName() + " exists in the database!");
        } else {
            System.out.println("Error: Player not found in the database!");
        }
    }


    /**
     * Returns a string representation of the names of all players in the team.
     *
     * @return a string containing the names of all players in the team, separated by newlines
     */
    @Override
    public void printTeam() {
        List<NBAPlayerInterface> list = backend.getAllPlayers();
        if (list == null) {
            System.out.println("The team is empty.");
        } else {
            System.out.println("Printing team...");

            for (NBAPlayerInterface playerPrintList : list) {
                System.out.println(playerPrintList.toString());
            }
        }
    }

}

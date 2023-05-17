// --== CS400 Spring 2023 File Header Information ==--
// Name: Naman Parekh
// Email: ncparekh@wisc.edu
// Team: DT
// TA: Daniel Finer
// Lecturer: Florian Heimerl (004)
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements the implementation of the BackendDeveloper
 * @author Naman Parekh
 *
 */

public class NBATeamBackend implements NBATeamBackendInterface {

    public int players; // number of players in our data structure
    private NBAPlayerRedBlackTreeInterface nbaRBT;
    private NBAPlayerReaderInterface playerReader;
    List<NBAPlayerInterface> playersInfo; // List which will store all NBA player's data

    // constructor
    public NBATeamBackend
    (NBAPlayerRedBlackTreeInterface nbaRBT, NBAPlayerReaderInterface playerReader) {
        this.nbaRBT = nbaRBT;
        this.playerReader = playerReader;
        this.players = 0;
        playersInfo = null;

    }

    /**
     * Use DataWrangler's code to load NBA players from the specified file, and then add
     * those players to a list which can be accessed at all times.
     *
     * @param filename the path and name of the file to load the csv file containing the
     *                 NBA players
     */
    @Override
    public void loadData(String filename) throws FileNotFoundException {

        // consists of a list of NBA player objects from the csv file
        playersInfo = playerReader.readPlayersFromFile(filename);

    }

    /**
     * Helper method that strips out punctuation and converts each word's first letter to uppercase for
     * efficient searching through the CSV file
     *
     * @param name of the NBA player
     * @return a String of the NBA player name which can now be searched for in the csv file
     */
    private String nameOfNBAPlayer(String name) {



        StringBuilder stringBuilder = new StringBuilder();

        String clean = name.replaceAll("[^a-zA-Z\\s]", "");
        String[] words = clean.split("\\s"); // split the string around whitespace

        for (String word : words) { // converts each word's first character to UpperCase so CSV file data can be read
            if (!word.isEmpty()) {
                stringBuilder.append(Character.toUpperCase(word.charAt(0)));
                stringBuilder.append(word.substring(1));
            }
            stringBuilder.append(" ");
        }



        return stringBuilder.toString().trim();
    }

    /**
     * Finds the NBA player object in the user's csv file
     *
     * @param name of the NBA player to search for
     * @return the NBA player object that identifies with the name supplied by the user
     */
    @Override
    public NBAPlayerInterface findNBAPlayerByName(String name) {

        String playerName = nameOfNBAPlayer(name);

        NBAPlayerInterface playerToSearch = null;

        // traverses the playersInfo list to find a match of a NBA player the user inputs
        for (NBAPlayerInterface player : playersInfo) {
            if (player.getName().equals(playerName)) {
                playerToSearch = player;
                return playerToSearch;
            }
        }
        return null;

    }

    /**
     * Finds the NBA player object in the user's team (RBT) and returns it's statistics
     *
     * @param name of the NBA player to search for
     * @return a string of the NBA player's statistics
     */
    @Override
    public String getStatisticsString(String name) {

        NBAPlayerInterface player = findNBAPlayerByName(name);

        if (player != null) {
            return player.toString();
        }

        else {
            return "Player not found in the database.";
        }
    }

    /**
     * Returns as a list, all the NBA players the user has added to their red black tree (team)
     *
     * @return a list of NBA player objects that are present in the red black tree
     */
    @Override
    public List<NBAPlayerInterface> getAllPlayers() {

        List<NBAPlayerInterface> list = new ArrayList<>();

        // traverses the playersInfo list to find all players in the list which are also in the RBT
        for (NBAPlayerInterface player: playersInfo) {
            if (nbaRBT.contains(player)) {
                list.add(player);
            }
        }

        return list;
    }

    /**
     * Adds a player to the RBT.
     *
     * @param name of the NBA player the user wants to add
     * @return true if player was successfully added, false otherwise
     */
    @Override
    public boolean addPlayer(String name) {

        // cannot add player as RBT is full capacity
        if (nbaRBT.isFull()) {
            return false;
        }

        String playerName = nameOfNBAPlayer(name);
        NBAPlayerInterface playerToAdd = null;

        // traverses the playersInfo list to find a match of a NBA player the user inputs
        for (NBAPlayerInterface player : playersInfo) {
            if (player.getName().equals(playerName)) {
                playerToAdd = player;

                break;
            }
        }

        if (playerToAdd != null) {
            nbaRBT.insert(playerToAdd);
            players++;
            return true;
        }

        return false;
    }

    /**
     * Removes a player to the RBT.
     *
     * @param name of the NBA player the user wants to remove
     * @return true if player was successfully removed, false otherwise
     */
    @Override
    public boolean removePlayer(String name) {

        NBAPlayerInterface playerToRemove = findNBAPlayerByName(name);

        if (playerToRemove != null) {
            nbaRBT.remove(playerToRemove);
            players--;
            return true;
        }

        return false;
    }
}

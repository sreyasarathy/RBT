import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class reads csv files of NBA Player data. This data is then separated and
 * used to create individual NBA Player objects which are then returned in a list.
 * @author DataWrangler
 */

public class NBAPlayerReaderDW implements NBAPlayerReaderInterface {

    /**
     * Constructor for NBAPlayerReader object
     */
    public NBAPlayerReaderDW() {
        // Constructor
    }

    /**
     * This method reads each line from the specified file, and stores NBAPlayer objects
     * with corresponding data into a list that is then returned.
     *
     * @param filename contains the path and name of the data file that should be
     *                 read from
     * @return List of NBAPlayers read from file
     * @throws FileNotFoundException when a file corresponding to the provided
     *                               filename cannot be read from
     */
    @Override
    public List<NBAPlayerInterface> readPlayersFromFile(String filename) throws FileNotFoundException {

        // use scanner to read from the specified file, and store results in posts
        List<NBAPlayerInterface> players = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()) {
            // for each line in the file being read:
            String line = scanner.nextLine();

            // split that line into parts around around the delimiter: ,
            String[] fields = line.split(",");

            String name = fields[0];

            int numGames = Integer.parseInt(fields[1]);
            int points = (int)(Double.parseDouble(fields[2])*numGames);
            int rebounds = (int)(Double.parseDouble(fields[3])*numGames);
            int steals = (int)(Double.parseDouble(fields[5])*numGames);
            int assists = (int)(Double.parseDouble(fields[4])*numGames);
            int blocks = (int)(Double.parseDouble(fields[6])*numGames);

            NBAPlayerDW player = new NBAPlayerDW(name, points, rebounds, steals, assists, blocks, numGames);
            players.add(player);
        }

        // then close the scanner before returning the list of posts
        scanner.close();
        return players;
    }
}

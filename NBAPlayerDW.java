/**
 * Represents an NBA player
 *
 */
public class NBAPlayerDW implements NBAPlayerInterface {

    private String name; // name of player
    private int points; // total points player has scored in all games
    private int rebounds; // total number of rebounds by player in all games
    private int steals; // total number of rebounds by player in all games
    private int assists; // total number of rebounds by player in all games
    private int blocks; // total number of rebounds by player in all games
    private int numGames; // total number of games the player has played


    /**
     * Constructer for NBAPlayer object
     * @param name - name of player
     * @param points - total points
     * @param rebounds - total num of rebounds
     * @param steals - total num of steals
     * @param assists - total num of assists
     * @param blocks - total num of blocks
     * @param numGames - total num of games played
     */
    public NBAPlayerDW(String name, int points, int rebounds, int steals, int assists, int blocks, int numGames) {
        this.name = name;
        this.points = points;
        this.rebounds = rebounds;
        this.steals = steals;
        this.assists = assists;
        this.blocks = blocks;
        this.numGames = numGames;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the total points scored by the player.
     *
     * @return the total points scored by the player
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * Returns the total number of rebounds grabbed by the player.
     *
     * @return the total number of rebounds grabbed by the player
     */
    @Override
    public int getRebounds() {
        return rebounds;
    }

    /**
     * Returns the total number of steals made by the player.
     *
     * @return the total number of steals made by the player
     */
    @Override
    public int getSteals() {
        return steals;
    }

    /**
     * Returns the total number of assists made by the player.
     *
     * @return the total number of assists made by the player
     */
    @Override
    public int getAssists() {
        return assists;
    }

    /**
     * Returns the total number of blocks made by the player.
     *
     * @return the total number of blocks made by the player
     */
    @Override
    public int getBlocks() {
        return blocks;
    }

    /**
     * Returns the total number of games played by the player.
     *
     * @return the total number of games played by the player
     */
    @Override
    public int getNumGames() {
        return numGames;
    }

    /**
     * Compares two NBAPlayer objects by their total points.
     *
     * @param other the NBAPlayer object to compare to
     * @return a negative integer, zero, or a positive integer as this player's total points is less than, equal to, or greater than the other player's total points.
     */
    @Override
    public int compareTo(NBAPlayerInterface other) {
        // Compare by points
        return Integer.compare(this.getPoints(), other.getPoints());
    }

    @Override
    public String toString() {

        return this.name + "\n    " + " Points: " + this.getPoints() + " Rebounds: " + this.getRebounds() +
                " Steals: " + this.getSteals() + " Assists: " + this.getAssists() + " Blocks: " + this.getBlocks() +
                " Number of Games: " + this.getNumGames();

    }
    }


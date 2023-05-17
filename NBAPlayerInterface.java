import java.io.FileNotFoundException;
import java.util.List;

interface NBAPlayerInterface extends Comparable<NBAPlayerInterface> {
    public String getName();
    public int getPoints();
    public int getRebounds();
    public int getSteals();
    public int getAssists();
    public int getBlocks();
    public int getNumGames();


    }

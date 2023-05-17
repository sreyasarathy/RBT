import java.io.FileNotFoundException;
import java.util.List;

interface NBAPlayerReaderInterface {
    // public NBAPlayerReaderInterface();
    public List<NBAPlayerInterface> readPlayersFromFile(String filename) throws FileNotFoundException;
}


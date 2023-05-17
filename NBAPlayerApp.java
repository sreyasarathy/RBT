import java.util.Scanner;

public class NBAPlayerApp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub



        // Use data wrangler's code to load post data
        NBAPlayerReaderInterface playerLoader = new NBAPlayerReaderDW();
        //PostReaderInterface postLoader = new PostReaderDW();

        // Use algorithm engineer's code to store and search for data
        NBAPlayerRedBlackTreeInterface tree;
        tree = new NBAPlayerRedBlackTree();

        //HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
        //hashtable = new HashtableWithDuplicateKeysAE<>();

        // Use the backend developer's code to manage all app specific processing
        NBAPlayerReaderInterface playerReader = new NBAPlayerReaderDW();
        NBAPlayerRedBlackTree redBlackTree = new NBAPlayerRedBlackTree();
        NBATeamBackend backend = new NBATeamBackend(redBlackTree, playerReader);
        //CHSearchBackendInterface backend = new CHSearchBackendBD(hashtable, postLoader);

        // Use the frontend developer's code to drive the text-base user interface
        Scanner scanner = new Scanner(System.in);
        NBATeamFrontendInterface frontend = new NBATeamFrontend(scanner, (NBATeamBackend) backend);
        frontend.runCommandLoop();


        //CHSearchFrontendInterface frontend = new CHSearchFrontendFD(scanner, backend);
        //frontend.runCommandLoop();

    }

}


/**
 * Changed constructor of NBATeamFrontend.java
 */


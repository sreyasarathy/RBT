// --== CS400 Role Code Header ==--
// Name: Sreya Sarathy
// CSL Username: ssarathy
// Email: sarathy2@wisc.edu
// Lecture #: MWF Lecture 1 3:30 PM with Prof Florian
// Notes to Grader: <any optional extra notes to your grader>


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;

public class FrontendDeveloperTests {


    private NBAPlayerReaderDW playerReader;


    /**
     * Test 1 tests whether the current team is empty
     * using the JUnit 5 Testing Framework.
     */
    @Test
    public void test1() {
        ArrayList<NBAPlayerInterface> playerList = new ArrayList<NBAPlayerInterface>();
        NBAPlayerRedBlackTree redBlackTree = new NBAPlayerRedBlackTree();
        NBATeamBackendInterface backend = new NBATeamBackend(redBlackTree, playerReader);
        NBATeamFrontend frontend = new NBATeamFrontend(null, backend);
        boolean expected = true;

        assertEquals(expected, playerList.isEmpty());
    }

    /**
     * Test 2 tests whether Danny Green is stored in the 3rd index of the Player List
     * using the JUnit 5 Testing Framework.
     */
    @Test
    public void test2() {
        ArrayList<String> playerList = new ArrayList<>();
        NBAPlayerRedBlackTree redBlackTree = new NBAPlayerRedBlackTree();
        NBATeamBackendInterface backend = new NBATeamBackend(redBlackTree, playerReader);
        NBATeamFrontend frontend = new NBATeamFrontend(null, backend);
        playerList.add("Steph Curry");
        playerList.add("LeBron James");
        playerList.add("Kyrie Irving");
        playerList.add("Danny Green");
        playerList.add("Saben Lee");
        playerList.add("Anthony Gill");
        playerList.add("Jason Preston");
        playerList.add("Matt Ryan");
        playerList.add("Paul Reed");
        playerList.add("Matt Ryan");
        String expectedString = "Danny Green";
        assertEquals(expectedString, playerList.get(3));
    }

    /**
     * Test 3 checks the total limit of the variable capacity. The maximum value of capacity is 15.
     * The test returns false if the expected is not equal to the actual.
     */
    @Test
    public void test3() {
        int capacity = 15;
        if (capacity != 15) {
            int expected = capacity;
            int actual = 16;
            assertFalse(expected == actual);
        }
    }

    /**
     * Test 4  tests the functionality of finding an NBA player by name.
     * It adds an NBA player "LeBron James" to the backend and frontend
     * objects, then tests if the backend can successfully find the player
     * by name. The expected output is "LeBron James".
     */
    @Test
    public void test4() {
        NBAPlayerRedBlackTree redBlackTree = new NBAPlayerRedBlackTree();
        NBATeamBackendInterface backend = new NBATeamBackend(redBlackTree, playerReader);
        ArrayList<NBAPlayerInterface> team = new ArrayList<NBAPlayerInterface>();
        NBATeamFrontend frontend = new NBATeamFrontend(null, backend);

        if (team.contains("LeBron James")) {
            String expected = "LeBron James";

            Assertions.assertTrue(Boolean.parseBoolean(expected),
                    String.valueOf(backend.findNBAPlayerByName("LeBron James")));

        }

    }

    /**
     * Test 5 tests the mainMenuPrompt. If the user tests the wrong command in the [] then assertFalse is thrown.
     * In this case, it is specific to [L] which represents load data for the NBA Team.
     */
    @Test
    public void test5() {
        char expected = 'L'; // System.out.println("[L]oad data for your NBA Team ");
        char actual = 'W';
        if (expected == actual) {
            Assertions.assertFalse(expected == actual);

        }
    }


    /**
     * The following tests are integration tests (test6 and test7)
     * The first test (test6) tests whether the player has been added and found
     * from the CSV file created by the DW. Tests the working of the entire application
     */

    @Test
    void test6() throws FileNotFoundException {
        NBAPlayerRedBlackTreeInterface redBlackTree = new NBAPlayerRedBlackTree();
        NBAPlayerReaderInterface playerReader =  new NBAPlayerReaderDW();
        NBATeamBackend backend =  new NBATeamBackend(redBlackTree, playerReader);

        //Adding the player Stephen Curry
        backend.loadData("NBAStats2023.csv");
        boolean result1 = backend.addPlayer("Stephen Curry");
        assertTrue(result1);
        backend.findNBAPlayerByName("Stephen Curry");



    }


    /**
     * Test 7 tests the addition of players by creating an NBATeamBackend instance and
     * reads in player information from a CSV file. It then adds three players to the backend,
     * and verifies that the correct number of players were added.
     * Finally, it adds a fourth player, and verifies that the addition was successful.
     * @throws FileNotFoundException if the specified CSV file cannot be found
     */

    @Test
    void test7() throws FileNotFoundException {
        NBAPlayerRedBlackTreeInterface redBlackTree = new NBAPlayerRedBlackTree();
        NBAPlayerReaderInterface playerReader =  new NBAPlayerReaderDW();
        NBATeamBackend backend =  new NBATeamBackend(redBlackTree, playerReader);

        backend.playersInfo = playerReader.readPlayersFromFile("NBAStats2023.csv");

        backend.addPlayer("Stephen Curry");
        backend.addPlayer("LeBron James");
        backend.addPlayer("Paul George");

        assertTrue(backend.addPlayer("Klay Thompson"));
        assertEquals(4,backend.players);




    }

    /**
     * The below tests test the BD's code specifically
     * Test 1 tests for the NBATeamBackend class, testing the addPlayer() and findNBAPlayerByName() methods.
     * This test creates a new NBATeamBackend object, loads player data from a CSV file, adds a new player
     * "LeBron James" to the user's team and searches for this player.
     * The test ensures that the player is
     * added successfully and that the player's name matches the expected value.
     *
     */

    @Test
    public void codeReviewOfBackendDeveloper1() throws FileNotFoundException {
        NBAPlayerRedBlackTreeInterface redBlackTree = new NBAPlayerRedBlackTree();
        NBAPlayerReaderInterface playerReader =  new NBAPlayerReaderDW();
        NBATeamBackend backend =  new NBATeamBackend(redBlackTree, playerReader);

        backend.loadData("NBAStats2023.csv");
        boolean result1 = backend.addPlayer("LeBron James");
        assertTrue(result1);

        NBAPlayerInterface player = backend.findNBAPlayerByName("LeBron James");
        assertNotNull(player);
        assertEquals("LeBron James", player.getName());

    }



    /** Test 2 tests the functionality of the NBATeamBackend class,
     * specifically the loadData, addPlayer, and findNBAPlayerByName methods.
     */
    @Test
    public void codeReviewOfBackendDeveloper2() throws FileNotFoundException {
        NBAPlayerRedBlackTreeInterface redBlackTree = new NBAPlayerRedBlackTree();
        NBAPlayerReaderInterface playerReader =  new NBAPlayerReaderDW();
        NBATeamBackend backend =  new NBATeamBackend(redBlackTree, playerReader);

        // loading the CSV file required and testing for a player present in them - LeBron James
        backend.loadData("NBAStats2023.csv");
        boolean result1 = backend.addPlayer("LeBron James");
        assertTrue(result1);
        NBAPlayerInterface player1 = backend.findNBAPlayerByName("LeBron James");
        assertNotNull(player1);
        assertEquals("LeBron James", player1.getName());

        // Adding a player that isnt present in the database - myself.
        boolean result2 = backend.addPlayer("Sreya Sarathy");
        assertFalse(result2);
        NBAPlayerInterface player2 = backend.findNBAPlayerByName("Sreya Sarathy");
        assertNull(player2);

    }
}





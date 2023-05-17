import java.util.List;

interface NBATeamFrontendInterface {
    // public NBATeamFrontendInterface(Scanner in, NBATeamBackendInterface backend);
    public void runCommandLoop();
    public char mainMenuPrompt();
    public void loadDataCommand();
    public void createTeam();
    // Add remove method to allow the user to remove a method
    public void removeTeam();
    public void searchName(String name);
    public void displayStatsCommand(String name);
    public void printTeam();

}

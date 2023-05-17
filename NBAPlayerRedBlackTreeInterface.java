interface NBAPlayerRedBlackTreeInterface extends SortedCollectionInterface<NBAPlayerInterface> {
    // public NBAPlayerRedBlackTreeInterface();
    public boolean isFull();
    public NBAPlayerInterface getByName(String name);
    public NBAPlayerInterface removeByName(String name);
}

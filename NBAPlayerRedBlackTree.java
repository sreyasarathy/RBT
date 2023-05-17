// --== CS400 Spring 2023 File Header Information ==--
// Name: Naman Parekh
// Email: ncparekh@wisc.edu
// Team: DT
// TA: Daniel Finer
// Lecturer: Florian Heimerl (004)
// Notes to Grader: <optional extra notes>

/**
 * The algorithm engineer needs to make it possible to do all functions using the NBA player name
 * (i.e. adding and removing a player to the tree with just their name).
 * They also need to add an isFull() function if the tree reaches 15 players, the maximum number of players for our NBA team.
 * They would also add get() and remove() functionality to get/remove players by name. All this is done in
 * the NBAPlayerRedBlackTree class
 */
public class NBAPlayerRedBlackTree extends RedBlackTree<NBAPlayerInterface> implements NBAPlayerRedBlackTreeInterface {

    /**
     * Checks whether team is at full capacity of 15 players
     * @return true if at capacity, false if not
     */
    @Override
    public boolean isFull() {
        if (size >= 15) return true;
        return false;
    }

    /**
     * Searches red black tree for name of NBA player specified
     * @param name of NBA player to search for
     * @return the object of the NBA player specified
     */
    @Override
    public NBAPlayerInterface getByName(String name) {
        // null references will not be stored within this tree
        if (name == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            NBAPlayerDW player = new
                    NBAPlayerDW(name, 0, 0, 0, 0, 0, 0);
            Node<NBAPlayerInterface> nodeWithData = this.findNodeWithData(player);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + player.toString());
            }
            // returning NBA player
            return nodeWithData.data;
        }
    }

    /**
     * remove helper which doesn't enforce RBT properties and returns removed player node
     * @param name of player to remove
     * @return removed player node
     */
    private Node<NBAPlayerInterface> removeByNameHelper(String name) {
        // null references will not be stored within this tree
        NBAPlayerDW data = new
                NBAPlayerDW(name, 0, 0, 0, 0, 0, 0);
        Node<NBAPlayerInterface> nodeWithData = null;
        Node<NBAPlayerInterface> nodeToReturn = null;
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            // copying node data to return correct node correctly
            nodeWithData = this.findNodeWithData(data);
            nodeToReturn = new Node<>(nodeWithData.data);
            nodeToReturn.context = nodeWithData.context;
            nodeToReturn.blackHeight = nodeWithData.blackHeight;
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<NBAPlayerInterface> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                this.replaceNode(nodeWithData, null);
            }
            this.size--;

        }
        return nodeToReturn;
    }

    /**
     * Searches, removes player specified, and makes sure red-black tree always balanced optimally
     * @param name of NBA player to remove
     * @return the removed player
     */
    @Override
    public NBAPlayerInterface removeByName(String name) {
        Node<NBAPlayerInterface> nodeWithData = removeByNameHelper(name);
        // only need to enforce RBT properties if black height is not 0
        if (nodeWithData.blackHeight != 0) {
            // enforcing properties through reinsertion
            NBAPlayerInterface[] players = new NBAPlayerDW[15];
            int index = 0;
            while (!isEmpty()) {
                // level order traversal to find all nodes
                if (root != null) players[index++] = root.data;
                if (root != null && root.context[1] != null) players[index++] = root.context[1].data;
                if (root != null && root.context[2] != null) players[index++] = root.context[2].data;
                if (root != null && root.context[1] != null && root.context[1].context[1] != null) players[index++] = root.context[1].context[1].data;
                if (root != null && root.context[1] != null && root.context[1].context[2] != null) players[index++] = root.context[1].context[2].data;
                if (root != null && root.context[2] != null && root.context[2].context[1] != null) players[index++] = root.context[2].context[1].data;
                if (root != null && root.context[2] != null && root.context[2].context[2] != null) players[index++] = root.context[2].context[2].data;
                if (root != null && root.context[1] != null && root.context[1].context[1] != null && root.context[1].context[1].context[1] != null) players[index++] = root.context[1].context[1].context[1].data;
                if (root != null && root.context[1] != null && root.context[1].context[1] != null && root.context[1].context[1].context[2] != null) players[index++] = root.context[1].context[1].context[2].data;
                if (root != null && root.context[1] != null && root.context[1].context[2] != null && root.context[1].context[2].context[1] != null) players[index++] = root.context[1].context[2].context[1].data;
                if (root != null && root.context[1] != null && root.context[1].context[2] != null && root.context[1].context[2].context[2] != null) players[index++] = root.context[1].context[2].context[2].data;
                if (root != null && root.context[2] != null && root.context[2].context[1] != null && root.context[2].context[1].context[1] != null) players[index++] = root.context[2].context[1].context[1].data;
                if (root != null && root.context[2] != null && root.context[2].context[1] != null && root.context[2].context[1].context[2] != null) players[index++] = root.context[2].context[1].context[2].data;
                if (root != null && root.context[2] != null && root.context[2].context[2] != null && root.context[2].context[2].context[1] != null) players[index++] = root.context[2].context[2].context[1].data;
                if (root != null && root.context[2] != null && root.context[2].context[2] != null && root.context[2].context[2].context[2] != null) players[index++] = root.context[2].context[2].context[2].data;

                // removing everything
                root = null;
                size = 0;
            }
            index = 0;
            // reinserting all nodes in the correct order, with correct colors
            while (players[index] != null) {
                insert(players[index]);
                players[index] = null;
                index++;
            }

        }

        return nodeWithData.data;
    }


    /**
     * Inserts player into tree by calling parent class, NBAPlayer should
     * be compared by name for sorting purposes
     * @param player - player to be added to tree
     * @return true if inserted correctly, false if not
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    @Override
    public boolean insert(NBAPlayerInterface player) throws NullPointerException, IllegalArgumentException {
        super.insert(player);
        return true;
    }

    /**
     * Calls removeByName and removes player from team
     * @param player player to be removed
     * @return true if removed successfully, false if not
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    @Override
    public boolean remove(NBAPlayerInterface player) throws NullPointerException, IllegalArgumentException {
        removeByName(player.getName());
        return true;
    }

    /**
     * Checks whether the team contains a specific NBA player
     * @param data the player to check for
     * @return true if it does contain the player, false if not
     */
    @Override
    public boolean contains(NBAPlayerInterface data) {
        // calls parent function to allow user to check if team contains player
        return super.contains(data);
    }

    /**
     * Returns the number of players in the team
     * @return the number of nodes in the red black tree
     */
    @Override
    public int size() {
        // calls parent function to allow user to check size
        return super.size();
    }

    /**
     * Checks whether the team has 0 players
     * @return true if empty, false if not
     */
    @Override
    public boolean isEmpty() {
        // calls parent function to allow user to see if empty
        return super.isEmpty();
    }

}

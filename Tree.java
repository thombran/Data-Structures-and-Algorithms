package Assignment5;

public class Tree {
    Node root; //The top node of the tree. Does not have a parent node

    /**
     * Default constructor for tree class, contains non-initialized root
     */
    public Tree(){}


    /**
     *
     * @return The root node of the tree calling this method
     */
    public Node getRoot() {
        return root;
    }


    /**
     *
     * @param root The node which is to be set at the new root of the calling tree
     */
    public void setRoot(Node root) {
        this.root = root;
    }


    /**
     * SOURCE = https://stackoverflow.com/questions/10832496/finding-if-a-binary-tree-is-a-binary-search-tree
     * Some code was modified from this source to complete this assignment.
     * @param node The node to start the search algorithm at. Should ideally start at the root node,
     * however the method could also be called to a node at a higher depth to test section of a BST.
     * @return True if the binary tree satisfies the binary search tree property, false otherwise
     */
    public boolean isSearchTree(Node node)
    {
        //If the current node is a leaf, then we know we have reached the end of this subtree and its satisfies
        //a BST
        if(node.left == null && node.right == null)
            return true;
        // The current node (within the recursive process) is compared to the child node to the left, which should
        // be a smaller value, and compared to the value on the right, which should be a greater value.
        // The method then recursively calls both the left and right child subtrees and continues comparing
        if(node.value > node.left.value && node.value < node.right.value && isSearchTree(node.getLeft())
           && isSearchTree(node.getRight()))
            return true;
        // If either the left or right child node does not meet the above requirements, then the calling tree
        // is not a BST.
        else
            return false;
    }

    public static void main(String [] args) {
        //Create a new tree which satisfies the BST property. Did not create a visual to terminal as this would
        //require a hefty method
        Tree ts = new Tree();
        ts.setRoot(new Node(15));
        ts.root.left = new Node(10);
        ts.root.right = new Node(20);
        ts.root.left.left = new Node(9);
        ts.root.left.right = new Node(11);
        ts.root.right.left = new Node(17);
        ts.root.right.right= new Node(21);
        //Result should be true
        System.out.println("Does this tree satisfy the BST property? " + ts.isSearchTree(ts.root));

        //Creating a new tree which does not satisfy the BST property. We can see that this should return false
        //because the left child of the left child of the root node is greater than its parent node.
        Tree ts2 = new Tree();
        ts2.setRoot(new Node(15));
        ts2.root.left = new Node(10);
        ts2.root.right = new Node(20);
        ts2.root.left.left = new Node(24);
        ts2.root.left.right = new Node(11);
        ts2.root.right.left = new Node(17);
        ts2.root.right.right= new Node(21);

        System.out.println("Does this tree satisfy the BST property? " + ts2.isSearchTree(ts2.root));


        //Now lets create 4 new trees of varying sizes, and compare the runtime of the BST searches to determine
        //if our search algorithm fits into a linear time curve.

        //A tree with one root and two children

        Tree ts3 = new Tree();
        ts3.setRoot(new Node(5));
        ts3.root.left = new Node(8);
        ts3.root.right = new Node(16);
        long startTime = System.nanoTime();
        ts3.isSearchTree(ts3.root);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in ms for 3 nodes: " + timeElapsed);

        //A tree with one root and 4 other nodes

        Tree ts4 = new Tree();
        ts4.setRoot(new Node(20));
        ts4.root.left = new Node(10);
        ts4.root.right = new Node(25);
        ts4.root.left.left = new Node(5);
        ts4.root.left.right = new Node(14);
        long startTime2 = System.nanoTime();
        ts4.isSearchTree(ts4.root);
        long endTime2 = System.nanoTime();
        long timeElapsed2 = endTime2 - startTime2;
        System.out.println("Execution time in ms for 5 nodes: " + timeElapsed2);

        //A tree with one root and 6 other children

        Tree ts5 = new Tree();
        ts5.setRoot(new Node(100));
        ts5.root.left = new Node(50);
        ts5.root.right = new Node(110);
        ts5.root.left.left = new Node(40);
        ts5.root.left.right = new Node(60);
        ts5.root.right.left = new Node(105);
        ts5.root.right.right= new Node(115);
        long startTime3 = System.nanoTime();
        ts5.isSearchTree(ts5.root);
        long endTime3 = System.nanoTime();
        long timeElapsed3 = endTime3 - startTime3;
        System.out.println("Execution time in ms for 7 nodes: " + timeElapsed3);

        //A tree with one root and 8 other children

        Tree ts6 = new Tree();
        ts6.setRoot(new Node(100));
        ts6.root.left = new Node(50);
        ts6.root.right = new Node(110);
        ts6.root.left.left = new Node(40);
        ts6.root.left.right = new Node(60);
        ts6.root.right.left = new Node(105);
        ts6.root.right.right= new Node(115);
        ts6.root.right.right.left = new Node(106);
        ts6.root.right.right.right = new Node(120);
        long startTime4 = System.nanoTime();
        ts6.isSearchTree(ts6.root);
        long endTime4 = System.nanoTime();
        long timeElapsed4 = endTime4 - startTime4;
        System.out.println("Execution time in ms for 9 nodes: " + timeElapsed4);
    }
}

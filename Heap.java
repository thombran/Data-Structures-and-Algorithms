package Assignment11;
import java.util.Random;


/**
 * This is my implementation of a heap which main functions consist of insert (to insert values into a heap
 * one at a time), as well as a constructor to build a heap in linear time. This class is going to test both of these
 * implementations for sorted, reversed-ordered, and random input arrays, and then compare the running times to
 * see if they are on the order of O(N).
 * @sources: https://www.geeksforgeeks.org/min-heap-in-java/
 * https://www.educative.io/edpresso/how-to-build-a-heap-from-an-array
 * https://www.journaldev.com/36805/min-heap-binary-tree
 * @author Brandon Thomas
 * @version 1.0.0
 */
public class Heap {
    public HuffmanNode[] minHeap; //This is the array which will serve as the minHeap when inserting one value at a time
    int size; //This variable holds the current size of the heap
    Random numGen = new Random(); //Random number generator for testing various input types for speed
    private int[] inputArray; //Array to use when testing speeds of various inputs
    int [] tempHeap;


    /**
     * Default constructor for the heap. Creates an array as indicated by the user
     * @param heapSize The size of the array which will hold the heap
     */
    public Heap(int heapSize){
        minHeap = new HuffmanNode[heapSize];
        size = 0;
        //We want to make all values zero so we know when a node in the heap is empty
        for (int i =0; i < minHeap.length; i++) {
            minHeap[i] = new HuffmanNode(-1, Character.MIN_VALUE);
            minHeap[i].freq = -1;
        }
    }


    /**
     * This function is used to clear the heap in between running times of the algorithms
     */
    public void clearHeap() {
        minHeap = new HuffmanNode[size];
        for (int i =0; i < minHeap.length; i++) {
            minHeap[i] = new HuffmanNode(-1, Character.MIN_VALUE);
        }
        this.size = 0;
    }

    public void clearTempHeap() {
        for (int i =0; i < tempHeap.length; i++)
            tempHeap[i] = -1;
    }

    /**
     * Gets the index value of the parent node
     * @param node The value in the array which we want to return the parent value of
     * @return Returns the index of node's parent
     */
    public int getParent(int node){
        return node /2;
    }


    /**
     * Gets the index of the left child of a node
     * @param node The value in the array which we want to return the left child value of
     * @return Returns the index of the node's left child
     */
    public int getLeftChild(int node){
        return (2 * node) + 1;
    }


    /**
     * gets the index of the right child of a node
     * @param node The value in the array which we want to return the right child value of
     * @return Returns the index of the node's right child
     */
    public int getRightChild(int node){
        return (2 * node) + 2;
    }




    /**
     * This is a helper function to swap values in the heap in order to maintain the heap order property
     * @param nodeToSwap The first node we are swapping which needs to move up the tree
     * @param nodeToSwapWith The second node we are swapping which needs to be moved down the tree
     */
    public void swapValues(int nodeToSwap, int nodeToSwapWith){
        HuffmanNode tempNode = minHeap[nodeToSwapWith]; //We want to temporarily hold the value of the node we are swapping with
        minHeap[nodeToSwapWith] = minHeap[nodeToSwap];
        minHeap[nodeToSwap] = tempNode;
    }


    /**
     * This is one of the main function of this class where we will test inserting items into a heap (array) one at a
     * time
     * @param node This is the value of the node we want to insert
     */
    public void insert(HuffmanNode node){
        //If we are trying to insert a value when the heap is already full, stop
        if(size >= minHeap.length)
            return;

        int currentNode = size; //Used to keep track of node while we are inserting it
        minHeap[size] = node;
        size++;


        //Here we are looping through and swapping the node being inserted with its parent until it is no longer
        //the smaller value between the two. This will satisfy the heap order property.
        while(minHeap[currentNode].freq < minHeap[getParent(currentNode)].freq && minHeap[currentNode].freq != -1){
            swapValues(currentNode, getParent(currentNode));
            currentNode = getParent(currentNode);
        }
    }

    /**
     * This is a helper function to determine if a node is a leaf or not. If the node is greater than or equal to
     * half the size of the heap, then we know it cannot have children based on the rules/patterns of children and
     * parent nodes
     * @param node The node we are checking
     * @return True if the node is a leaf within the tree, false otherwise
     */
    public boolean isLeaf(int node){
        if (node >= (inputArray.length / 2) && node <= inputArray.length)
            return true;
        else
            return false;
    }

    /**
     * This is the secondary main function of this class where we will build the heap in linear time
     */
    public void createMinHeap(int root){

        //First we want to check to make sure the node we are checking is not a leaf, as there will not be any
        //children to compare to
        if (!isLeaf(root)) {
            if (getRightChild(root) < inputArray.length && getLeftChild(root) < inputArray.length) {
                //Then compare to see if the parent node has a greater value than either the left or right child
                if (inputArray[root] > inputArray[(getLeftChild(root))] || inputArray[root]
                        > inputArray[getRightChild(root)]) {
                    //If the left child has a lesser value than the right, this is the one we are going to swap with,
                    //and then we recursively go back through this sub tree to make sure this swap did not mess
                    //up the order of the other nodes
                    if (inputArray[getLeftChild(root)] < inputArray[getRightChild(root)]) {
                        int val = inputArray[root];
                        inputArray[root] = inputArray[getLeftChild(root)];
                        inputArray[getLeftChild(root)] = val;
                        createMinHeap(getLeftChild(root));
                    }
                    //Otherwise, we know the right child node is the lesser of the two, and we are going to swap with this
                    //value, and correct the subsequent subtree
                    else {
                        int val = inputArray[root];
                        inputArray[root] = inputArray[getRightChild(root)];
                        inputArray[getRightChild(root)] = val;
                        createMinHeap(getRightChild(root));
                    }
                }
            }
        }
    }

    /**
     * This function is to implement the createMinHeap function across the entire tree starting from the bottom
     * (so that way we are not just min heaping a single subtree).
     */
    public void doMinHeap(){
        for (int node = (inputArray.length / 2); node >= 0; node--){
            createMinHeap(node);
        }
    }


    /**
     * This method extracts the current min value/node located at the top of the minHeap. Code has been modified
     * slightly to accomodate HuffmanNode implementation
     * @return The minimum node
     */
    public HuffmanNode extractMin() {
        if (size <= 0)
            throw new IndexOutOfBoundsException(); //If there is no node to extract, throw error
        //If size is one, extract first/last element and the minHeap is now empty
        if (size == 1) {
            size--;
            return minHeap[0];
        }

        HuffmanNode root = minHeap[0]; //Temporarily hold on to min value/node
        HuffmanNode [] tempHeap = minHeap; //Create temp heap while we clear the minHeap
        clearHeap(); //Clear the minHeap to reinsert nodes (Yes inefficient but it works)
        //For each value in the temp heap except for the first (because we are removing it), reinsert it properly back
        //into the minHeap
        for (int i = 1; i < tempHeap.length; i++){
            if (tempHeap[i].freq != -1) //Make sure we are not adding back in an (essentially) empty HuffmanNode
                insert(tempHeap[i]);
        }
        return root; //Return the min Node which was extracted
    }


    /**
     * This function puts values into the heap in a sorted (ascending order) to be tested at a later time
     * @param size Size of the array to create
     */
    public void createSortedInput(int size){
        inputArray = new int[size];
        int num = 1; //Number to be inserted into heap
        for (int i = 0; i < inputArray.length; i++){
            inputArray[i] = num;
            num++;
        }
    }


    /**
     * This function puts values into the heap in a sorted (descending order) to be tested at a later time
     * @param size Size of the array to create
     */
    public void createReverseInput(int size){
        inputArray = new int[size];
        int num = 60;
        for (int i = 0; i < inputArray.length; i++){
            inputArray[i] = num;
            num--;
        }
    }


    /**
     * This function puts values into the heap with random values to be tested at a later time
     * @param size Size of the array to create
     */
    public void createRandomInput(int size){
        inputArray = new int[size];
        int num;
        for (int i = 0; i < inputArray.length; i++){
            num = numGen.nextInt(100);
            inputArray[i] = num;
        }
    }


    public static void main(String [] args){
        /**
         * This is the start of assessing the running time of the insert function for sorted, reverse-ordered, and
         * random inputs
         */
        //Testing sorted input
        Heap insertHeap = new Heap(10);
        insertHeap.createSortedInput(10);


//
//        //Testing reverse-ordered input
//        insertHeap.clearHeap();
//        insertHeap.createReverseInput(50);
//        long startTime2 = System.nanoTime();
//        for (int i = 0; i < insertHeap.inputArray.length; i++){
//            insertHeap.insert(insertHeap.inputArray[i]);
//        }
//        long endTime2 = System.nanoTime();
//        long timeElapsed2 = endTime2 - startTime2;
//        System.out.println("Running time for insert reverse-ordered algorithm: " + timeElapsed2 + " nanoseconds");
//
//        //Testing random input
//        insertHeap.clearHeap();
//        insertHeap.createRandomInput(50);
//        long startTime3 = System.nanoTime();
//        for (int i = 0; i < insertHeap.inputArray.length; i++){
//            insertHeap.insert(insertHeap.inputArray[i]);
//        }
//        long endTime3 = System.nanoTime();
//        long timeElapsed3 = endTime3 - startTime3;
//        System.out.println("Running time for insert reverse-ordered algorithm: " + timeElapsed3 + " nanoseconds");
//
//
//        /**
//         * This is the start of assessing the running time of the doMinHeap function for sorted, reverse-ordered, and
//         * random inputs. In this function, we will be building the heap in linear time
//         */
//        //Testing sorted with doMinHeap algorithm
//        Heap buildHeap = new Heap(50);
//        buildHeap.createSortedInput(50);
//        long startTime4 = System.nanoTime();
//        buildHeap.doMinHeap();
//        long endTime4 = System.nanoTime();
//        long timeElapsed4 = endTime4 - startTime4;
//        System.out.println("Running time for doMinHeap sorted algorithm: " + timeElapsed4 + " nanoseconds");
//
//
//        //Testing reverse ordered with doMinHeap algorithm
//        buildHeap.clearHeap();
//        buildHeap.createReverseInput(50);
//        long startTime5 = System.nanoTime();
//        buildHeap.doMinHeap();
//        long endTime5 = System.nanoTime();
//        long timeElapsed5 = endTime5 - startTime5;
//        System.out.println("Running time for doMinHeap reverse-ordered algorithm: " + timeElapsed5 + " nanoseconds");
//
//
//        //Testing random input with doMinHeap algorithm
//        buildHeap.clearHeap();
//        buildHeap.createRandomInput(50);
//        long startTime6 = System.nanoTime();
//        buildHeap.doMinHeap();
//        long endTime6 = System.nanoTime();
//        long timeElapsed6 = endTime6 - startTime6;
//        System.out.println("Running time for doMinHeap random input algorithm: " + timeElapsed6 + " nanoseconds");
//
//
//        //Now we are going to test the doMinHeap function to ensure it builds the heap in linear time
//        //We are also going to do this with reverse-ordered to make sure it is as consistent as possible
//        buildHeap.clearHeap();
//        buildHeap.createReverseInput(50);
//        long startTime7 = System.nanoTime();
//        buildHeap.doMinHeap();
//        long endTime7 = System.nanoTime();
//        long timeElapsed7 = endTime7 - startTime7;
//        System.out.println("Building heap in linear time with size 50: " + timeElapsed7);
//
//        buildHeap.clearHeap();
//        buildHeap.createReverseInput(100);
//        long startTime8 = System.nanoTime();
//        buildHeap.doMinHeap();
//        long endTime8 = System.nanoTime();
//        long timeElapsed8 = endTime8 - startTime8;
//        System.out.println("Building heap in linear time with size 100: " + timeElapsed8);
//
//        buildHeap.clearHeap();
//        buildHeap.createReverseInput(150);
//        long startTime9 = System.nanoTime();
//        buildHeap.doMinHeap();
//        long endTime9 = System.nanoTime();
//        long timeElapsed9 = endTime9 - startTime9;
//        System.out.println("Building heap in linear time with size 150: " + timeElapsed9);
//
////        buildHeap.clearHeap();
////        buildHeap.createReverseInput(200);
////        long startTime10 = System.nanoTime();
////        buildHeap.doMinHeap();
////        long endTime10 = System.nanoTime();
////        long timeElapsed10 = endTime10 - startTime10;
////        System.out.println("Building heap in linear time with size 200: " + timeElapsed10);
////
////        buildHeap.clearHeap();
////        buildHeap.createReverseInput(250);
////        long startTime11 = System.nanoTime();
////        buildHeap.doMinHeap();
////        long endTime11 = System.nanoTime();
////        long timeElapsed11 = endTime11 - startTime11;
////        System.out.println("Building heap in linear time with size 250: " + timeElapsed11);
    }

}

package Assignment11;

import java.io.FileNotFoundException; //We need this for file I/O
import java.util.*;

/**
 * This is my implementation of the Huffman Algorithm. I did source GeekforGeeks
 * (Source 1) in terms of following the logical steps of the algorithm,
 * and I used it to determine how to approach the recursive step of creating the code. This class modifies my earlier
 * implementation of a minHeap/Priority queue, as well as a hash map to store frequencies of characters read in from
 * files. After the freqs are read in, we store them in a priority queue and 1 by 1 remove them and readd them as a
 * single combined node/frequency. This gives us a mapping for recursively determining the Huffman code for each char.
 * @author Brandon Thomas
 * @source 1. https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
 * @version 1.0.0
 */
public class HuffmanTree {
    Map <String, Integer> charMap; //HashMap to store the frequencies of each character, pulled in from frequency class
    Frequencies textOutput = new Frequencies(); //Object that is reading in the data from the file, in this case Poach.txt
    Heap minHeap; //This is our minHeap that will store the characters and their freqs in a priority queue


    /**
     * Default constructor for HuffmanTree. Creates initialized HashMap with values from text file, and creates a minHeap
     * of a size equal to that of the HashMap.
     * @throws FileNotFoundException If the file cannot be found on the user's system, incorrect name, etc.
     */
    public HuffmanTree() throws FileNotFoundException {
        textOutput.findFrequencies(); //Reading in values from file
        charMap = textOutput.getFrequencies(); //Filled HashMap
        minHeap = new Heap(charMap.size()); //Priority queue
    }


    /**
     * This function creates the initial nodes of the minHeap, whereby each node is a leaf as they do not have any left
     * or right nodes. Each node stores a character value as well as an int frequency (how many times the char occurs)
     */
    public void createLeafNodes() {
        //Loop through each key in the hashMap and create a new node based off of it, and then insert it into its
        //respective position in the minHeap
        for (Map.Entry<String, Integer> set : charMap.entrySet()){
            minHeap.insert(new HuffmanNode(set.getValue(), set.getKey().charAt(0)));
        }
    }


    /**
     * This is the fruition of the Huffman Algorithm where we are looping through the various nodes of the minHeap and
     * printing out their codes based on their position in the tree. Codes with more bits occur less frequently in the
     * text, and vice versa.
     * @param start
     * @param str
     */
    public static void printHuffCodes(HuffmanNode start, String str) {
        //If we have reached a leaf node and it is not a combined frequency node (i.e. it is actually a character),
        //print out the code (str) that was created recursively
        if (start.LNode == null && start.RNode == null && start.character != Character.MIN_VALUE) {
            System.out.println("Character is: " + start.character + " Huffman Code is: " + str);
            return;
        }

        printHuffCodes(start.LNode, str + "0"); //If not a leaf and is left node, add 0 to code and recurse
        printHuffCodes(start.RNode, str + "1"); //If not a leaf and is right node, add 1 to code and recurse
    }


    /**
     * This is the main function being called for the program which utilizes all of the helper functions (printHuffCodes
     * , createLeafNodes, and using the minHeap) in order to encode and construct the Huffman Codes for the text file.
     */
    public void doHuffman() {
        createLeafNodes(); //First we need to create all of the leaves corresponding to the HashMap
        //Now we want to loop through the minHeap until there is only one node left, and continuously add up the two
        //smallest nodes (frequency) and add the new single node back into the minHeap
        while (minHeap.size > 1) {
            HuffmanNode min1 = minHeap.extractMin(); //First min value/node
            HuffmanNode min2 = minHeap.extractMin(); //Second min value/node
            //New node which has the frequency of min1 + min2, no character value (MIN_VALUE) and has the first min
            //as the left node and the second min as the right node
            HuffmanNode newNode = new HuffmanNode(min1.freq + min2.freq, Character.MIN_VALUE, min1, min2);
            minHeap.insert(newNode); //Insert the new node back into the minHeap
        }
        minHeap.minHeap[1] = null; //This is an artifact of modifying my previous heap node, not sure why I am
                                   //left with a single extra node at the end
        printHuffCodes(minHeap.minHeap[0], ""); //Call the recursive function to print the HuffmanCodes
    }

    public static void main (String [] args) throws FileNotFoundException {
        HuffmanTree h = new HuffmanTree();
        System.out.println("PRINTING THE HUFFMAN CODES FOR THIS FILE....");
        System.out.println("_____________________________________________");
        h.doHuffman();
        System.out.println("DONE.");

    }
}

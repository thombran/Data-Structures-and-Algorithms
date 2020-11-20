package Assignment11;


/**
 * This class simply serves as the nodes which are inserted into the minHeap and which store both the character and
 * frequency values obtained from the keys in the HashMap.
 */
public class HuffmanNode {
    public int freq; //Occurrence of character for this node
    public char character; //The character of this node
    HuffmanNode LNode; //Left node
    HuffmanNode RNode; //Right node


    /**
     * Constructor for HuffmanNode where user can specify the character and the frequency of the character
     * @param freq Amount of occurrences of the specified character
     * @param character The character of this node
     */
    public HuffmanNode(int freq, char character){
        this.freq = freq;
        this.character = character;
        LNode = null;
        RNode = null;
    }


    /**
     * Secondary constructor for HuffmanNode where user can also specify the left and right nodes, in addition to the
     * character and its frequency
     * @param freq Amount of occurrences of the specified character
     * @param character The character of this node
     * @param LNode The attached left node of the current node
     * @param RNode The attached right node of the current node
     */
    public HuffmanNode(int freq, char character, HuffmanNode LNode, HuffmanNode RNode){
        this.freq = freq;
        this.character = character;
        this.LNode = LNode;
        this.RNode = RNode;
    }
}

package Assignment4;
import java.util.Random;


/**
 * @author Brandon Thomas
 * @version 1.0
 * This class is my implementation of a Stack structure, which abstracts arrays on the backend
 */
public class Stack {
    public int [] theArray; //The array which holds the stack
    public int topOfStack = -1; //Keeps track of the top, starts at -1 before the first item is added
    Random r = new Random(); //Random number generator to show functionality of class


    /**
     * Constructor for the Stack class. Creates a new array of size 52
     */
    public Stack(){
        theArray = new int [52]; //Standard size of deck of cards...
    }


    /**
     * Adds random value to put on the top of the stack
     */
    public void push(){
        topOfStack++;
        //Instead of pushing specific element, generate/push a random card to put on top
        theArray[topOfStack] = r.nextInt(14) + 1;
        System.out.println(theArray[topOfStack]);
    }


    /**
     * Shows the current value at the top of the stack and removes it
     * @return The value at the top of the stack
     */
    public int pop(){
        int value = theArray[topOfStack];
        topOfStack--;
        return value;
    }


    /**
     * Shows how big the stack is
     * @return How many elements are in the stack
     */
    public int size(){
        int count = 0; //Keeps track of the number of items found in the stack
        int countTop = topOfStack;
        while(countTop != -1) {
            count++;
            countTop--;
        }
        return count;
    }


    /**
     *
     * @return True if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        if (topOfStack == -1)
            return true;
        return false;
    }


    /**
     * Function that displays the contents of the stack. In this case, built specifically to display the
     * numbers 1-10, as well as J,Q,K, and A like a deck of cards.
     */
    public void display() {
        int count = topOfStack;
        while (count != -1) {
            //Each of these outputs is to make sure the J,Q,K, and A can be represented (for fun)
            if (theArray[count] > 10) {
                if (theArray[count] == 11)
                    System.out.println("J");
                if (theArray[count] == 12)
                    System.out.println("Q");
                if (theArray[count] == 13)
                    System.out.println("K");
                if (theArray[count] == 14)
                    System.out.println("A");
            } else
                System.out.println(theArray[count]);
            count--;
        }
    }

    public static void main (String [] args) {
        String p;
        Stack s = new Stack();
        //Filling stack with pseudorandom values for the deck of cards
        System.out.println("_________________PRINTING OUT TOP OF STACK VALUES AS WE GO____________________________");
        for(int i = 0; i < 10; i++){
            s.push();
        }
        System.out.println("_____________TESTING SIZE FUNCTION FOR STACK______________");
        System.out.println(s.size()); //Testing to see if size is 52
        System.out.println("___________TESTING DISPLAY FUNCTION FOR STACK_________________");
        s.display(); //Showing the display function works
        System.out.println("_____________TESTING IF STACK IS EMPTY___________________");
        p = String.valueOf(s.isEmpty()).toUpperCase(); //Value should be false since stack is filled
        System.out.println("Is the stack empty? " + p);

        //Removing values from the deck/stack using the pop function
        for (int i = 0; i < 10; i++) {
            System.out.println(s.pop());
        }
        System.out.println("_____________TESTING IF STACK IS EMPTY AFTER REMOVING VALUES___________________");
        p = String.valueOf(s.isEmpty()).toUpperCase(); //Value should now be true since the stack has been emptied
        System.out.println("Is the stack empty? " + p);
    }
}

package Assignment4;

/**
 * @author Brandon Thomas
 * @version 1.0
 * This class is my attempt to recreate a Queue data structure by abstracting an array on the backend
 * Contains the function Enqueue, Dequeue, displayQueue, Peek, and Size
 */
public class Queue {
    public QueueNum [] theArray; //Keeps track of the queue
    public int front = 0; //Keeps track of which value is currently in the front position
    public int back = 0; //Keeps track of which value is currently  in the back position
    public int currentSize = 0; //Keeps track of the current size the the queue


    /**
     * Default constructor of the Queue class which sets the backend array size to 10
     */
    public Queue(){
        theArray = new QueueNum [11]; //Creates new Queue of size 10
    }


    /**
     * This function adds a new Queue item to the top of the current Queue
     * @param data The integer value to be added to the queue
     */
    public void enqueue(int data) {
        if (currentSize == 11) {
            System.out.println("The Queue is full! You must dequeue first!");
            return;
        }
        theArray[back] = new QueueNum(data); //Set whatever the next back position is to the new data value
        currentSize++; //Increase size of queue by one
        theArray[back].setPlaceInQueue(currentSize);
        if (back == 10)
            back = 0;
        else
            back++; //Move back "pointer" to next position for next value

    }


    /**
     * This function removes whichever Queue item (integer value) that was at the top of the Queue
     * @return The integer number which was removed from the top of the Queue
     */
    public int dequeue() {
        if (currentSize == 0) {
            System.out.println("The Queue is empty! You must first enqueue an item!");
            throw new RuntimeException(); //If trying to call this function on an empty Queue
        }
        theArray[front].setPlaceInQueue(-1); //Lets the program know which Queue items are not in the Queue
        int value = theArray[front].getNum();
        if (front == 10) //Loop back to front of array if at the back already
            front = 0;
        else
            front++;
        currentSize--; //Decrease size of the Queue by 1
        int currentPos = front;
        //This portion of code serves to reiterate through the Queue to renumber their positions
        while (theArray[currentPos].getPlaceInQueue() != -1) {
                theArray[currentPos].setPlaceInQueue(theArray[currentPos].getPlaceInQueue() - 1);
                currentPos++;
            //Make sure the program loops back to the front if we reach the end while the Queue is not finished
            if (currentPos == 11)
                currentPos = 0;
        }
        return value;
    }


    /**
     * This function displays all of the integer values of the Queue items currently present
     */
    public void displayQueue() {
        int currentPos = front;
        //This loops through and prints each item until the end of the Queue is reached
        while (theArray[currentPos].getPlaceInQueue() != currentSize) {
            System.out.println(theArray[currentPos].getNum() + " POSITION IN QUEUE: " + theArray[currentPos].getPlaceInQueue());
            currentPos++;
            //Loops back to front of array if necessary
            if (currentPos == 11)
                currentPos = 0;
        }
        //This makes sure the last item in the Queue is printed out once cuurentSize is reached
        System.out.println(theArray[currentPos].getNum()+ " POSITION IN QUEUE: " + theArray[currentPos].getPlaceInQueue());
    }


    /**
     * This function reveals, but does not remove, the first item in the Queue
     * @return The value of the Queue item (integer) that is at the front of the Queue
     */
    public int peek() {
        if (theArray[front].getPlaceInQueue() != -1) {
            System.out.print("Number at the top of the Queue: ");
            return theArray[front].getNum();
        }
        else
            System.out.println("The Queue is empty!");
        //Default value to return if the Queue is empty
        return -1;
    }


    /**
     *
     * @return The size of the Queue. Serves as get function for currentSize
     */
    public int size() {
        System.out.print("The current size of the Queue is: ");
        return currentSize;
    }


    public static void main (String [] args) {
        System.out.println("_____________QUEUEING UP TEN ITEMS____________________");
        Queue q = new Queue();
        q.enqueue(10);
        q.enqueue(8);
        q.enqueue(7);
        q.enqueue(6);
        q.enqueue(1);
        q.enqueue(12);
        q.enqueue(90);
        q.enqueue(6);
        q.enqueue(3);
        q.enqueue(100);

        q.displayQueue();
        System.out.println("___________DEQUEUE 5 ITEMS______________");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.displayQueue();
        System.out.println("__________DEQUEUE 4 MORE ITEMS__________________");
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.displayQueue();
        System.out.println("_________QUEUEING UP 2 MORE ITEMS________________");
        q.enqueue(47);
        q.enqueue(9);
        q.displayQueue();
        System.out.println("_________SHOWING PEEK (TOP) VALUE______________");
        System.out.println(q.peek());
        System.out.println("___________SHOWING SIZE OF THE QUEUE_____________");
        System.out.println(q.size());

    }
}

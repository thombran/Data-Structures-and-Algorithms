package Assignment3;

public class LinkedList1 {
    public Node head;
    public Node tail;

    public LinkedList1(){}


    /**
     * This function adds a new node with the given data to the end of the linked list
     * @param data The integer value to be stored by the node being added
     *
     */
    public void add(int data) {
        Node newNode = new Node(data);
        //If list is empty
        if (head == null) {
            head = tail = newNode;
        }
        //If the list has at least one element, add to end
        else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = tail.getNext();
        }
    }


    /**
     *
     * @return Returns the size of the linked list (ie. the number of elements)
     */
    public int size() {
        Node curr = head;
        if (head == null)
            return 0;
        int count = 0;
        while (curr != null) {
            curr = curr.getNext();
            count++;
        }
        return count;
    }


    /**
     *
     * @param index1 The first number (ie the data value) that will be swapped (index value)
     * @param index2 The second number (ie the data value) that will be swapped (index value)
     */
    public void swapSingly(int index1, int index2) {
        //Immediately returns if list has less than two elements or if invalid indices are given
        if (this.size() < 2 || Math.abs(index2 - index1) != 1)
            return;
        //This is to make sure index1 is always the lesser of the two values
        if (index1 > index2) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }
        Node prev;
        Node node1 = head;
        Node node2 = node1;
        int index3;

        index3 = index1 - 1;
        prev = node1;
        //For loops to get each index pointing to the proper node in the linked list
        for (int i = 0; i < index1; i++)
            node1 = node1.getNext();
        for (int i = 0; i < index2; i++)
            node2 = node2.getNext();
        for (int i = 0; i < index3; i++)
            prev = prev.getNext();
        //If list is only two elements long
        if (node1 == head && node2 == tail) {
            head = node2;
            head.setNext(node1);
            tail = head.getNext();
            tail.setNext(null);
        }
        //If swapping the first two elements in the list, but size() > 2
        else if (node1 == head && node2.getNext() != null) {
            Node temp = node1;
            Node temp2 = node2.getNext();
            head = node2;
            head.setNext(temp);
            head.getNext().setNext(temp2);
        }
        //If node2 is the tail, then need to avoid using a temp pointing to the next node after node2
        else if (node2 == tail) {
            Node temp = node1;
            prev.setNext(node2);
            prev.getNext().setNext(temp);
            temp.setNext(null);
        }
        //Otherwise the nodes to be swapped are elsewhere in the array
        else {
            Node temp = node1;
            Node temp2 = node2.getNext();
            prev.setNext(node2);
            prev.getNext().setNext(temp);
            prev.getNext().getNext().setNext(temp2);
        }
    }


    /**
     * This function fully utilizes the double linked list by also using previous node pointers, unlike swapSingly
     * @param index1 The first element in the linked list to be swapped indicated by this index
     * @param index2 The second element in the linked lis tto be swapped indicated by this index
     */
    public void swapDoubly(int index1, int index2){
        //Immediately returns if list has less than two elements or if invalid indices are given
        if (this.size() < 2 || Math.abs(index2 - index1) != 1)
            return;
        //This is to make sure index1 is always the lesser of the two values
        if (index1 > index2) {
            int temp = index1;
            index1 = index2;
            index2 = temp;
        }

        Node node1 = head;
        Node node2 = node1;

        //For loops to get each index pointing to the proper node in the linked list
        for (int i = 0; i < index1; i++)
            node1 = node1.getNext();
        for (int i = 0; i < index2; i++)
            node2 = node2.getNext();

        //If there is only two nodes
        if (size() == 2) {
            head = node2;
            head.setNext(node1);
            tail = head.getNext();
            tail.setNext(null);
        }
        //If swapping first two nodes and list size is greater than 2
        else if (node1 == head && size() > 2) {
            Node next = node2.getNext();
            head = node2;
            node2.setPrev(null);
            node2.setNext(node1);
            node1.setPrev(node2);
            node1.setNext(next);
        }
        //Otherwise the list size is greater than 2
        else {
            Node prev = node1.getPrev();
            Node next = node2.getNext();

            if (prev != null)
                prev.setNext(node2);
            node2.setPrev(prev);
            node2.setNext(node1);
            node1.setPrev(node2);
            node1.setNext(next);
            if (next != null)
                next.setPrev(node1);
        }
    }

    public void printLots(LinkedList1 L, LinkedList1 P){
        Node currL = L.head;
        Node currP = P.head;
        int indexL = 0;
        //Loop to iterate through entire P linked list
        while (currP != null) {
            //Loop to find the correct index in L as indicated by P
            while (indexL != currP.getData()) {
                currL = currL.getNext();
                indexL++;
            }
            //After finding the correct index in L, print L and go to next value in P
            System.out.println(currL.getData());
            currP = currP.getNext();
        }
    }


    /**
     * Function to print out the data items of a linked list
     */
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    public static void main(String [] args) {

        //Creating the first list L which denotes the list of elements to choose from

        LinkedList1 L = new LinkedList1();
        L.add(6);
        L.add(18);
        L.add(27);
        L.add(42);
        L.add(61);
        L.add(64);
        L.add(93);

        //Creating the second list P which denotes the index items to print from L

        LinkedList1 P = new LinkedList1();
        P.add(1);
        P.add(3);
        P.add(5);
        P.add(6);
        //Creating the timer to find runtime of printLots function
        long startTime = System.nanoTime();
        L.printLots(L,P);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time in milliseconds for printLots function: " + (totalTime / 1000000.0000) + "ms");

        //Testing the singly and doubly swap function to swap adjacent elements
        System.out.println("----------TESTING SINGLY SWAP BEFORE----------");
        L.display();
        L.swapSingly(2,3);
        System.out.println("----------TESTING SINGLY SWAP AFTER (Index 2 & 3)----------");
        L.display();
        System.out.println("----------TESTING DOUBLY SWAP BEFORE----------");
        L.display();
        L.swapDoubly(5,6);
        System.out.println("----------TESTING DOUBLY SWAP AFTER (Index 5 & 6)----------");
        L.display();
    }
}

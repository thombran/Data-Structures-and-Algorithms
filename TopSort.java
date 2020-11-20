package Assignment9;
import java.util.LinkedList;
import java.util.Queue;

import java.util.ArrayList;


/**
 * This is my implementation of topological sorting using Kahn's algorithm (Source:https://www.geeksforgeeks.org/
 * topological-sorting-indegree-based-solution/). I did not look at the Java code, rather I just used the steps/
 * approach to write out my code.
 * @author Brandon Thomas + source seen above
 * @version 1.0
 */
public class TopSort {

    private ArrayList<int[]> adjList; //This is the adjacency list of the example graph
    //This is the queue used to implement Kahn's algorithm for topological sorting. Had to use stl as my own queue
    //did not like this implementation (which I believe you said using queue stl in class was fine).
    private Queue<Integer> kahnQueue;
    private int[] inDegrees; //The array that gives us the amount of in degrees for each vertex
    private int[] ordering; //The array that holds the topological ordering after we perform the sort on the graph


    /**
     * Default constructor for TopSort. Currently automatically creates example graph from book by implementing
     * an adjacency list
     * @param size The size to make the arrays of inDegrees and Ordering arrays
     */
    public TopSort(int size){
        createExGraph(); //Creates example graph from figure 9.81 in book
        kahnQueue = new LinkedList<>();
        inDegrees = new int[size];
        ordering = new int[size];
    }

    /**
     * This function creates the example graph from figure 9.81 in the book. Each new array being added to the
     * ArrayList represents vertices s-t. A 1 represents a directed vector pointing towards vertex[i] (ie
     * adjList.get(0)[1] returns as 1 because there is a directed edge between s and A. A 0 means there is no
     * directed edge between the two vertices.
     */
    private void createExGraph() {
        adjList = new ArrayList<int[]>();
        
        //Creating the vertices for the adjacency list and their respective directed edges

        adjList.add(new int[] {0,1,0,0,1,0,0,1,0,0,0}); //Adding adj vertices for vertex s
        adjList.add(new int[] {0,0,1,0,0,1,0,0,0,0,0}); //Adding adj vertices for vertex A
        adjList.add(new int[] {0,0,0,1,0,0,0,0,0,0,0}); //Adding adj vertices for vertex B
        adjList.add(new int[] {0,0,0,0,0,0,0,0,0,0,1}); //Adding adj vertices for vertex C
        adjList.add(new int[] {0,1,0,0,0,1,0,0,0,0,0}); //Adding adj vertices for vertex D
        adjList.add(new int[] {0,0,0,1,0,0,1,0,0,1,0}); //Adding adj vertices for vertex E
        adjList.add(new int[] {0,0,0,1,0,0,0,0,0,0,1}); //Adding adj vertices for vertex F
        adjList.add(new int[] {0,0,0,0,1,1,0,0,1,0,0}); //Adding adj vertices for vertex G
        adjList.add(new int[] {0,0,0,0,0,1,0,0,0,1,0}); //Adding adj vertices for vertex H
        adjList.add(new int[] {0,0,0,0,0,0,1,0,0,0,1}); //Adding adj vertices for vertex I
        adjList.add(new int[] {0,0,0,0,0,0,0,0,0,0,0}); //Adding adj vertices for vertex t
    }


    /**
     * Helper function used by doTopSort to determine the indegrees for each vertex, which is part of Kahn's
     * algorithm. Method goes through each row and column and if an entry == 1, increases indegree of that vertex.
     */
    private void compInDegrees(){
        for (int i = 0; i < this.adjList.size(); i++){
            for(int j = 0; j < this.adjList.size(); j++){
                if (this.adjList.get(i)[j] == 1)
                    this.inDegrees[j]++;
            }
        }
    }


    /**
     * Main method performing the topological sort of a graph using Khan's algorithm. As the assignment did not ask for
     * it, currently does not implement any way of checking to make sure graph does not contain a cycle.
     */
    private void doTopSort(){
        //Compute the amount of indegrees for each vertex
        this.compInDegrees();

        //Here we want to loop through and, if and of the vertices have an indegree of 0, add them to the queue
        //This is because any vertex with indegree = 0 can be a starting point as no vertices can come before it
        for (int i = 0; i < inDegrees.length; i++){
            if (inDegrees[i] == 0)
                kahnQueue.add(i);
        }

        int placeInSort = 0; //Use this variable to keep track of where we are adding vertices into the ordering array

        //As long as the queue is not empty, continue looping through and emptying the queue. This is done by
        //first adding a vertex with indegree 0 into the ordering, and then subtracting 1 from the indegree value
        //of each adjacent vertex. If any adjacent vertex has indegree == 0 after, add it to the queue. This ensures
        //that vertices that still have dependencies remaining are not being added into the ordering before their
        //other adjacent vertices are ordered first
        while (!kahnQueue.isEmpty()){
            ordering[placeInSort] = kahnQueue.remove();

            for (int i = 0; i < adjList.size(); i++){
                if (adjList.get(ordering[placeInSort])[i] == 1) {
                    inDegrees[i] -= 1;
                    if (inDegrees[i] == 0)
                        kahnQueue.add(i);
                }
            }
            placeInSort++; //Once we are done assessing a vertex, move on to next one by increasing placeInSort
        }
    }


    /**
     * Since everything up until this point has been computed using integer values, and because the example graph in the
     * book has vertices with letters, this function converts those integer values into letters corresponding to the
     * vertices in the example graph.
     */
    public void printOrdering() {
        char letter;
        for (int i = 0; i < ordering.length; i++){
            switch (ordering[i]) {
                case 0:
                    letter = 's';
                    System.out.print(letter + "-->");
                    break;
                case 1:
                    letter = 'A';
                    System.out.print(letter + "-->");
                    break;
                case 2:
                    letter = 'B';
                    System.out.print(letter + "-->");
                    break;
                case 3:
                    letter = 'C';
                    System.out.print(letter + "-->");
                    break;
                case 4:
                    letter = 'D';
                    System.out.print(letter + "-->");
                    break;
                case 5:
                    letter = 'E';
                    System.out.print(letter + "-->");
                    break;
                case 6:
                    letter = 'F';
                    System.out.print(letter + "-->");
                    break;
                case 7:
                    letter = 'G';
                    System.out.print(letter + "-->");
                    break;
                case 8:
                    letter = 'H';
                    System.out.print(letter + "-->");
                    break;
                case 9:
                    letter = 'I';
                    System.out.print(letter + "-->");
                    break;
                case 10:
                    letter = 't';
                    System.out.print(letter + "-->");
                    break;
            }
        }
        System.out.println("DONE"); //We have reached the end of the ordering
    }


    public static void main (String [] args) {
        TopSort s = new TopSort(11);
        s.createExGraph();
        s.doTopSort();
        System.out.println("This is the ordering of the graph after performing a topological sort:");
        s.printOrdering();
    }
}

package Assignment10;
import java.util.ArrayList;

/**
 * This is my implementation of the Ford-Fulkerson algorithm which finds the max flow of a system. This implementation
 * references the graph from Figure 9.41 in Data Structures and Algorithm Analysis by Mark Allen Weiss
 * @author Brandon Thomas
 * @version 1.0.3
 */
public class MaxFlow {

    private ArrayList<int[]> graph; //Holds the connections and weights from the example graph
    private ArrayList<int[]> flow; //Holds the flow of a path from s -> t after finding path using DFS/BFS
    private ArrayList<int[]> residual; //Holds residual values of graph after finding flow
    private int pathFlow; //Holds the value of the current minFlow for an augmented path
    private ArrayList<Boolean> visited; //Assists DFS algorithm with knowing which vertices have been visited
    private ArrayList<Integer> augPath; //Holds the current augmented path
    private int maxFlow; //Holds the overall (int) max flow value of the graph as the algorithm proceeds


    /**
     * Default constructor for the MaxFlow class. Initializes the 3 graphs which hold the values for the example,
     * flow, and residual graphs respectively.
     */
    public MaxFlow() {
        initGraph(); //Construct example graph from figure 9.41 in the textbook
        initFlow(); //Construct initial flow graph, all values of which are 0
        initResidual(); //Construct initial residual graph, all values of which are initially the same as the graph
        visited = new ArrayList<Boolean>(); //Create new visited list to keep track of visited vertices
        initVisited(); //Initialize all values of the list to false
        augPath = new ArrayList<Integer>(); //Create new (empty) augmented path list
        pathFlow = 0; //Initial path flow of the graph starts at 0 before calculations
        maxFlow = 0; //Initial max flow of the graph is also 0 before calculations
    }


    /**
     * This function creates the initial graph from Figure 9.41 in the textbook
     */
    private void initGraph() {
        graph = new ArrayList<int[]>(); //Creating adjacency matrix which holds graph values
        graph.add(new int[] {0,4,2,0,0,0}); //Vertex s and its respective connections/weights
        graph.add(new int[] {0,0,1,2,4,0}); //Vertex a
        graph.add(new int[] {0,0,0,0,2,0}); //Vertex b
        graph.add(new int[] {0,0,0,0,0,3}); //Vertex c
        graph.add(new int[] {0,0,0,0,0,3}); //Vertex d
        graph.add(new int[] {0,0,0,0,0,0}); //Vertex t
    }


    /**
     * This function creates the initial flow graph, all the values of which are 0 as there is initially no flow
     */
    private void initFlow() {
        flow = new ArrayList<int[]>(); //Creating adjacency matrix which holds flow values
        flow.add(new int[] {0,0,0,0,0,0}); //Vertex s and its respective connections/weights
        flow.add(new int[] {0,0,0,0,0,0}); //Vertex a
        flow.add(new int[] {0,0,0,0,0,0}); //Vertex b
        flow.add(new int[] {0,0,0,0,0,0}); //Vertex c
        flow.add(new int[] {0,0,0,0,0,0}); //Vertex d
        flow.add(new int[] {0,0,0,0,0,0}); //Vertex t
    }


    /**
     * This function creates the initial residual graph, which before any max flow calculations are made, is the same
     * as the example graph. This is what will be used to determine the max flow at the end of the problem as well
     * as when to terminate the algorithm (when there are no more augmented paths s -> t).
     */
    private void initResidual() {
        residual = new ArrayList<int[]>(); //Creating adjacency matrix which holds residual values
        residual.add(new int[] {0,4,2,0,0,0}); //Vertex s and its respective connections/weights
        residual.add(new int[] {0,0,1,2,4,0}); //Vertex a
        residual.add(new int[] {0,0,0,0,2,0}); //Vertex b
        residual.add(new int[] {0,0,0,0,0,3}); //Vertex c
        residual.add(new int[] {0,0,0,0,0,3}); //Vertex d
        residual.add(new int[] {0,0,0,0,0,0}); //Vertex t
    }


    /**
     * Used in the constructor to initialize all vertices in the graph as not visited
     */
    private void initVisited() {
        visited.add(false);
        visited.add(false);
        visited.add(false);
        visited.add(false);
        visited.add(false);
        visited.add(false);
    }


    /**
     * Helper function for DFS to clear the visited ArrayList
     */
    private void clearVisited() {
        for (int i = 0; i < visited.size(); i++) //Loop through and set each value to false
            visited.set(i, false);

    }


    /**
     * Clears all of the values from the augmented path
     */
    private void clearAugPath() {
        augPath.clear();
    }


    /**
     * This function used depth field search (DFS) to find an augmented path on the residual graph. The minimum edge
     * value found on this path is what will be used to determine the pathFlow variable and therefore will be used to
     * update the flow graph.
     * @return Returns true if there is an augmented path, returns false otherwise
     * @param vertex The vertex of the graph we want to perform the DFS search on
     */
    private boolean findAugPath(int vertex) {
        visited.set(vertex, true); //Set the current vertex as visited in our visited list
        augPath.add(vertex); //Add the current vertex to our augmented path list

        for (int i = 0; i < residual.size(); i++) { //Loop through all of the edges for each vertex
            if (vertex == residual.size() - 1) //If vertex is equal to 't' vertex, we have reached the end
                return true;
            if (residual.get(vertex)[i] > 0 && !visited.get(i)) { //Otherwise if there is a weighted edge and the edge
                return findAugPath(i); //is not visited, recursively find path from that new vertex
            }
        }
        return false; //Return false if we cannot find any path from 's' to 't'
    }


    /**
     * This function is used to find the minimum edge/flow value of a given augmented path
     */
    private void findFlow() {
        int min = residual.get(augPath.get(0))[augPath.get(1)]; //Minimum is first edge in path to start
        for (int i = 0; i < augPath.size() - 1; i++){ //Loop thru path and if any value is less than curr min, it is min
            if (residual.get(augPath.get(i))[augPath.get(i+1)] < min){
                min = residual.get(augPath.get(i))[augPath.get(i+1)];
            }
        }
        pathFlow = min; //Make current pathFlow equal to the minimum value of the path we found
    }


    /**
     * This is the function which directly implements all of the helper functions to use Ford-Fulkerson algorithm
     * which will return the max flow of a weighted directed graph. For some reason, the reverse edges messes up the
     * output even thought its simply a matter of instead of -= (u,v), we do += (v,u), so that has been commented out.
     * @return The max flow of a given weighted directed graph as represented by an integer
     */
    private int findMaxFlow() {
        while(findAugPath(0)) { //While we can still find an augmented path in the graph, loop through
            findFlow(); //Find the max flow/min value of the graph using helper function
            for (int i = 0; i < augPath.size() - 1; i++) { //Loop through path values to update flow graph edges
                if (flow.get(augPath.get(i))[augPath.get(i+1)] + pathFlow <= //As long as flow being added is less
                        graph.get(augPath.get(i))[augPath.get(i+1)]) { //than max as dictated by graph, add it
                    flow.get(augPath.get(i))[augPath.get(i + 1)] += pathFlow;
                }
            }
            for (int i = 0; i < augPath.size() - 1; i++) { //Loop through path values to update residual edges
                residual.get(augPath.get(i))[augPath.get(i+1)] -= pathFlow; //Subtract flow from residual
                //residual.get(augPath.get(i+1))[augPath.get(i)] += pathFlow; //This does not work for some reason
            }
            maxFlow += pathFlow; //Add whatever current flow we just calculated to the max flow
            clearVisited(); //Clear all of the visited vertices
            clearAugPath(); //Clear the current augmented path from ArrayList

        }
        return maxFlow;
    }


    /**
     * Testing the max flow output of the example graph, which should be 5
     * @param args
     */
    public static void main (String [] args) {
        MaxFlow f = new MaxFlow();
        System.out.print("The max flow of this graph using the Ford-Fulkerson Algorithm is: [");
        System.out.print(f.findMaxFlow() + "] ");
    }

}

package Assignment6;
import java.util.Random;

/**
 * This class is my implementation of a hash table, with methods/functions to insert items using
 * linear and quaratic probing, and double hashing
 * @author Brandon Thomas
 * @version 1.5
 */
public class Collisions {
    public int [] hashTable = new int [1001]; //Table that holds the values after being hashed
    Random r = new Random(); //Random number generator
    int collisions = 0; //Keeps track of the number of collisions after using a hash method
    int numElements = 0; //Number of elements in the hash table


    /**
     * Sets all the values of the hash table to -1 so we are able to tell if a spot has been inserted into already.
     * If this class used objects, -1 would be replaced with null.
     */
    public void clearhashTable() {
        for (int i =0; i < hashTable.length; i++) {
            hashTable[i] = -1;
        }
    }


    /**
     * The hash function for the hash table, mods the param by table size
     * @param num The int number to be hashed
     * @return Number after it has been hashed (mod by table size)
     */
    public int hash(int num){
        return num % hashTable.length;
    }


    /**
     * This is the secondary hash function used in the double hash method. If the number cannot be placed in a
     * bucket, the value is double hashed, and the new value is linearly multiplied until a position is found
     * @param num The number we are trying to hash
     * @param tries The f(tries) value: determines how many times we have double hashed/how much to multiply
     *              double hash by
     * @return The number after it has been secondarily hashed
     */
    public int secondaryHash(int num, int tries){
        return tries * (7 - (num % 7));
    }


    /**
     * This method inserts hashed numbers into the table by first trying the index of the hash, and if that
     * does not work, the method continues to try current pos + 1 until an empty index is found
     */
    public void linearProbe() {
        clearhashTable(); //Make sure all buckets are empty in hash table
        while (numElements != hashTable.length) { //Loop through table until it is full
            int number = r.nextInt(1001); //Creates random number between 0 - table size
            if (hashTable[hash(number)] == -1) { //If bucket is empty, insert number into bucket
                hashTable[hash(number)] = number;
            }
            //If that bucket is full, we need to loop through 1 bucket at a time to find an empty bucket
            else {
                int index = 1; //Used to keep track of how many buckets away from initial we are
                collisions = 1; //Already have made one collision
                //If the bucket we try is full, increase amount of collisions and move forward 1
                while ((hashTable[(hash(number) + index) % hashTable.length]) != -1) {
                    collisions++;
                    index++;
                }
                hashTable[(hash(number) + index) % hashTable.length] = number;
            }
            numElements++; //Table has one more element
        }
    }


    /**
     * This method inserts hashed numbers into buckets in the table by first hashing, and then quadratically
     * trying buckets ahead in the table (i.e. 1, 2, 4, 8, etc.).
     */
    public void quadraticProbing() {
        clearhashTable(); //Make sure to clear table's buckets before running hash algorithm
        collisions = 0;
        //Continue looping until the table is full, however for quadratic probing we will break once the
        //number of elements in the table exceeds half the table capacity
        while (numElements != hashTable.length) {
            int number = r.nextInt(1001);
            if (hashTable[hash(number)] == -1) { //If the bucket is empty
                hashTable[hash(number)] = number;
            }
            else {
                collisions++; //One collision has already happened
                int index = 1;
                int count = 1;
                //We continue to quadratically loop/probe through the table until we find an open bucket to place
                //the value in
                while (hashTable[(hash(number) + index) % hashTable.length] != -1) {
                    collisions++;
                    index = (int) Math.pow(2, count); //Exponentially increasing index value
                    count++; //Increase exponent value
                    if (numElements > hashTable.length / 2) //If table is too full, exit
                        return;
                }
                hashTable[hash(number + index) % hashTable.length] = number;
            }
            numElements++;
        }
    }

    /**
     * This hashing method places numbers into buckets by first hashing, and if that fails, double hashing occurs.
     * With double hashing, the value is hashed by a separate modulo value (7), and linearly multiplied until
     * the value can be placed in a bucket.
     */
    public void doubleHashing() {
        clearhashTable(); //Make sure to clear table's buckets before running hash algorithm
        collisions = 0;
        //Continue looping until the table is full, however for quadratic probing we will break once the
        //number of elements in the table exceeds half the table capacity
        while (numElements != hashTable.length) {
            int number = r.nextInt(1001);
            if (hashTable[hash(number)] == -1) { //If the bucket is empty
                hashTable[hash(number)] = number;
            } else {
                collisions++; //One collision has already happened
                int secondHashes = 1; //Keeps track of how much to multiply double hash by
                //We want to keep double hashing until we reach an empty bucket
                while (hashTable[(hash(number) + secondaryHash(number, secondHashes)) % hashTable.length] != -1) {
                    secondHashes++;
                    //If the double hash function has tired to many times, just stop the function from continuing
                    if (secondHashes > 100)
                        break;
                }
                //Place the number in the bucket designated by the double hash and hash value
                hashTable[(hash(number) + secondaryHash(number, secondHashes)) % hashTable.length] = number;
            }
            numElements++; //Increase value of number of elements in the table
        }
    }

    public static void main (String [] args) {
        //Here we are creating a new collision object and trying the linear probing function
        Collisions c = new Collisions();
        c.linearProbe();
        System.out.println("Number of collisions for linear probing: " + c.collisions);

        //Here we are creating a new collision object and trying the quadratic probing function
        Collisions d = new Collisions();
        d.quadraticProbing();
        System.out.println("The quadratic probing function incurred " + d.collisions + " collisions before failing.");

        //Here we are creating a new collision object and trying the double hashing function
        Collisions e = new Collisions();
        e.doubleHashing();
        System.out.println("Number of collisions for double hashing: " + e.collisions);
    }

}

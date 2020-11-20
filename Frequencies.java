package Assignment11;
import java.io.*;
import java.util.*;

/**
 * This class does the behind the scenes work of loading in a file from windows, and then creating a HashMap of all
 * of the characters and their respective values from the given file. This approach considers all characters which are
 * non-whitespace characters.
 */
public class Frequencies {
    File poachFile = new File("C:\\Users\\br4nd\\Desktop\\Poach.txt"); //The file we are loading in
    Scanner scanner = new Scanner(poachFile); //Our scanner object to read in the file
    Map <String, Integer> charMap = new HashMap<String, Integer>(); //The HashMap to store the values
    Iterator it; //Used in testing portion of code


    /**
     * Default constructor for Frequencies class. Does nothing other than create an instance of this file output
     * @throws FileNotFoundException If file name is incorrect or file cannot be found on host computer
     */
    public Frequencies() throws FileNotFoundException {
    }


    /**
     * This function reads in the tokens from the given file (strings), and then breaks those strings down into
     * characters, and merges the values into the current HashMap
     */
    public void findFrequencies() {
        //While we haven't reached the EOF, continue
        while (scanner.hasNext()){
            String word = scanner.next(); //Store the current token
            //While we have not reached the end of the string, merge char counts into HashMap
            for (int i = 0; i < word.length(); i++)
                charMap.merge(String.valueOf(word.charAt(i)), 1, Integer::sum);
        }
    }


    /**
     * This function just returns the current HashMap and its values
     * @return
     */
    public Map getFrequencies(){
        return this.charMap;
    }

    public static void main (String [] args) throws FileNotFoundException {
        Frequencies f = new Frequencies();
        f.findFrequencies();
        System.out.println("PRINTING CHARACTERS AND THEIR RESPECTIVE COUNTS FOR THIS DOCUMENT...");
        System.out.println(f.charMap.entrySet());
        /**
         * The commented code here can be used to check the total char counts of the HashMap with the total given
         * by the file you are reading in (Ex. using word count feature on Microsoft Word)
         */
//        Iterator it = h.charMap.entrySet().iterator();
//        int total = 0;
//        while (it.hasNext()){
//            Map.Entry pair = (Map.Entry)it.next();
//            total += (Integer)pair.getValue();
//        }
//        System.out.println(total);
    }
}

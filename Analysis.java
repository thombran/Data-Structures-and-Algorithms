package Assignment2;

public class Analysis {
    int n = 40;
    public static void main (String [] args) {

        Analysis run = new Analysis(); //Used to alter the n value
        System.out.println("N Value: " + run.getN());

        
        /**
         * Code 1 Loops
         */
        long startTime = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < run.getN(); ++i)
            ++sum;
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time in nanoseconds for problem 1: " + totalTime);


        /**
         * Code 2 Loops
         */
        long startTime2 = System.nanoTime();
        int sum2 = 0;
        for (int i = 0; i < run.getN(); ++i)
            for (int j = 0; j < run.getN(); ++j)
                ++sum2;
        long endTime2 = System.nanoTime();
        long totalTime2 = endTime2 - startTime2;
        System.out.println("Time in nanoseconds for problem 2: " + totalTime2);


        /**
         * Code 3 Loops
         */
        long startTime3 = System.nanoTime();
        int sum3 = 0;
        for (int i = 0; i < run.getN(); ++i)
            for (int j = 0; j < (run.getN() * run.getN()); ++j)
                ++sum3;
        long endTime3 = System.nanoTime();
        long totalTime3 = endTime3 - startTime3;
        System.out.println("Time in nanoseconds for problem 3: " + totalTime3);


        /**
         * Code 4 Loops
         */
        long startTime4 = System.nanoTime();
        int sum4 = 0;
        for (int i = 0; i < run.getN(); ++i)
            for (int j = 0; j < i; ++j)
                ++sum4;
        long endTime4 = System.nanoTime();
        long totalTime4 = endTime4 - startTime4;
        System.out.println("Time in nanoseconds for problem 4: " + totalTime4);


        /**
         * Code 5 Loops
         */
        long startTime5 = System.nanoTime();
        int sum5 = 0;
        for (int i = 0; i < run.getN(); ++i)
            for (int j = 0; j < (i * i); ++j)
                for (int k = 0; k < j; ++k)
                    ++sum5;
        long endTime5 = System.nanoTime();
        long totalTime5 = endTime5 - startTime5;
        System.out.println("Time in nanoseconds for problem 5: " + totalTime5);


        /**
         * Code 6 Loops
         */
        long startTime6 = System.nanoTime();
        int sum6 = 0;
        for (int i = 1; i < run.getN(); ++i)
            for (int j = 1; j < (i * i);++j)
                if (j % i == 0)
                    for (int k = 0; k < j; ++k)
                        ++sum6;
        long endTime6 = System.nanoTime();
        long totalTime6 = endTime6 - startTime6;
        System.out.println("Time in nanoseconds for problem 6: " + totalTime6);
    }

    public int getN() {
        return n;
    }
}

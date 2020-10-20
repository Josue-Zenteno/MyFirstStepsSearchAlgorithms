import java.util.Scanner;

/*********************************************************************
 * Class Name: Lab_1
 * Author/s name: Marina Prieto Pech
 *                Josue Carlos Zenteno Yave
 * Release/Creation date: 23-march-2020
 * Class version: 1.0
 * Class description: Main class, including all needed methods
 ***********************************************************************/
public class Lab_1 {
    /*************************************************************************************
     * Method name: Main method
     * Description of the Method: Main method, where all the Equation methods are called.
     * Calling arguments: (String[] args)
     *************************************************************************************/
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //Scanner declarations
        String result="-----------------------------------------------RESULT-----------------------------------------------"; //Separator
        int n=0; //N value to be substituted on each equation execution possibilities
        long value, start, end, tf1 ,tf2 ,tf3; //Variables to store equation value, start and ending times, and total methods execution times

        System.out.println("Select number of tests");
        int x = sc.nextInt();//X value, used to store number of repetitions/trials to make

        while (x > 0){// While loop, repeated X times (each repetition, a new N is selected)
            System.out.println("Select N value for trial: [" + (x) + "]");
            n = sc.nextInt();

            if (n == 0) {
                System.out.println("There is no a hexagonal number using this value choose another one");
            }else{
                start = System.nanoTime();// Start time is stored
                value = Eq1(n);// Insertion of n value to the method, so it can return calculation results onto main method
                end = System.nanoTime();// After executing the method, final time is also stored
                tf1 = end - start;// To get total time, we just substract one from another

                start = System.nanoTime();
                value = Eq2Iterative(n);
                end = System.nanoTime();
                tf2 = end - start;

                start = System.nanoTime();
                value = Eq2Recursive(n - 1, 0);// In the case of recursive call, n-1 is introduced
                end = System.nanoTime();
                tf3 = end - start;

                result += "\n[N]:  " +n+"\t\t[Valor]   "+value+"\t\t[Formula]   "+tf1+"ns\t\t[Iterative]   "+tf2+"ns\t\t[Recursive]   "+tf3+"ns";
                // For each trial of N, we store that onto a result string so we can finally print all the times
                x--;// Finally, we decrease value x, so we can go to the next iteration.
            }
        }
        sc.close();
        System.out.println(result);// Printing on final result
    }

    /*************************************************************************************
     * Method name: Eq1
     * Description of the Method: Equation result is calculated with the first given formula
     *                            ( result = n(2n – 1) )
     * Calling arguments: Int n (Value n to replace onto the formula)
     * Return value: Long result (Final hexagonal number as long value)
     *************************************************************************************/
    private static long Eq1 (int n) {
        long result = n * ((2 * n) - 1);
        return result;
    }

    /*************************************************************************************
     * Method name: Eq2Iterative
     * Description of the Method: Equation result is calculated with the second given formula
     *                            but in an iterative way (by using a for loop)
     * Calling arguments: Int n (value n to replace onto the formula)
     * Return value: Long result (Final hexagonal number as long value)
     *************************************************************************************/
    private static long Eq2Iterative (int n) {
        long result = 0;// Result variable, started to 0, so we can then add each iteration result to it

        for(int i = 0; i < n; i++) {// Repetition from 0 to n-1 (repeat while number is lower than n)
            result += (4*i) + 1;// Calculation of each iteration and storing into result
        }
        return result;// Returning final result, after all the iterations of i
    }

    /*************************************************************************************
     * Method name: Eq2Recursive
     * Description of the Method: Equation result is calculated with the second given formula
     *                            but in a recursive way (by using the calling of the same method)
     * Calling arguments: Int n (Value n to replace onto the formula)
     *                    Long result (To not lose the results of the iterations, we pass them too)
     * Return value: Long result (Final iteration: final result, other iterations: iteration result added to the previous result passed)
     *************************************************************************************/
    private static long Eq2Recursive (int i, long result) {
        if (i == 0) { // Final case, when i = 0, one is added to result and final result is returned
            result += 1;
        }else {// Iterative case (repeated from n-1 to 1)
            result += (4*i) +1;// To result, we add the iteration by substituting i value with the calling argument i
            result = Eq2Recursive((i-1), result);// Recursive call, reducing i value by one unit
        }
        return result;// Final return
    }
}

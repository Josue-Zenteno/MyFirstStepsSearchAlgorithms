import java.util.Vector;
/***********************************************************************************************************************
 * Class Name: Principal
 * Author/s name: Josue Carlos Zenteno Yave and Marina Prieto Pech
 * Release/Creation date: 26/04/2020
 * Class version: 1.0
 * Class description: Principal class that triggers all the methods that solve the task
 **********************************************************************************************************************/
public class Principal {
    /*******************************************************************************************************************
     * Method name: main
     * Description of the Method: main method that triggers all the methods that solve this task
     * Required Files: fellowshipsX.txt
     ******************************************************************************************************************/
    public static void main(String[] args) {
        System.out.println("We are Josue Carlos Zenteno Yave and Marina Prieto Pech\nWelcome to our program!\nThe results are:");
        Vector<Fellowship> fellowships = FileReader.read("Fellowships8.txt");   //Vector that will store the fellowships
        int [] bestSol = new int [fellowships.size()];   //Vector that will store the final solution
        int [] state = new int [fellowships.size()];      //Vector that will store a partial solution during backtracking
        int [] result;    //Vector that will store the result of the backtracking method
        long  initial_time = System.nanoTime();
        result = backtracking(state,0,bestSol,fellowships);
        long final_time = System.nanoTime();
        printResult(result,fellowships,(final_time-initial_time));      //Printing the result
    }
    /*******************************************************************************************************************
     * Method name: backtracking
     * Description of the Method: Method that deals with the task of selecting the best option of fellowships groups
     *                            using backtracking
     * Calling arguments: int [] state : Auxiliary array that will store a partial solution of the problem
                          int level : Auxiliary variable that stores the level in which we are in each iteration
                          int [] bestSol : Auxiliary array that will store the best solution until it finds another one
                                           even better
                          Vector<Fellowship> fellowships : Vector that stores all the available fellowships
     * Return value: int [] : Array that stores the final result
     ******************************************************************************************************************/
    private static int [] backtracking(int [] state , int level , int [] bestSol , Vector<Fellowship> fellowships){

        if (level == fellowships.size()-1){   //Base case
            if (isBest(state,bestSol,fellowships)){ //Looking only for the best solution
                for  ( int i = 0 ; i < state.length ; i++){
                    bestSol[i] = state [i];
                }
            }
        }else{
            if (isCompatible(fellowships.elementAt(level),state,level,fellowships)) {   //Feasibility criteria
                state [level] = 1;
                backtracking(state,level+1,bestSol,fellowships);        //Recursive call to the next level
            }
            state [level] = 0;
            backtracking(state,level+1,bestSol,fellowships);        //Recursive call to the next level
        }
        return bestSol;
    }
    /*******************************************************************************************************************
     * Method name: isBest
     * Description of the Method: Method that checks if the purposed solution is better than the previous one
     * Calling arguments: int [] state : The purposed solution
     *                    int [] bestSol : The actual solution
     *                    Vector<Fellowship> fellowships : Vector that stores all the available fellowships
     * Return value: boolean : True if the solution is better than the previous one, False if the current one is
     *                         still the best solution
     ******************************************************************************************************************/
    private static boolean isBest(int [] state , int [] bestSol , Vector<Fellowship> fellowships){
        int s_state = 0;        //Auxiliary variable that will store the total profit of the purposed solution
        int s_bestSol = 0;      //Auxiliary variable that will store the total profit of the actual solution

        for (int i = 0 ; i < state.length ; i++) {      //Adding the total_salary of every fellowship
            if (state[i] == 1){
                s_state += fellowships.elementAt(i).totalSalary;
            }
            if (bestSol[i] == 1){
                s_bestSol += fellowships.elementAt(i).totalSalary;
            }
        }
        return s_state > s_bestSol;
    }
    /*******************************************************************************************************************
     * Method name: isCompatible
     * Description of the Method: Method that checks if the fellowship is not overlapped with another one previously
     *                            chosen
     * Calling arguments: Fellowship fs : The actual candidate to join to the solution
     *                    int [] state : The purposed solution
     *                    int level : The actual level
     *                    Vector<Fellowship> fellowships : Vector that stores all the available fellowships
     * Return value: boolean : True if the purposed fellowship fits with the other ones in the solution , False otherwise.
     ******************************************************************************************************************/
    private static boolean isCompatible(Fellowship fs , int [] state , int level , Vector<Fellowship> fellowships){

        boolean comp = true;        //True if the fellowship fits, False otherwise
        for (int i = 0 ; (i<level) && comp ; i++){
            if (state[i] == 1) {
                comp = (fellowships.elementAt(i).finalMonth < fs.startMonth) || (fs.finalMonth < fellowships.elementAt(i).startMonth);
            }
        }
        return comp;
    }
    /*******************************************************************************************************************
     * Method name: printResult
     * Description of the Method: Prints the corresponding fellowship identifiers  that are part of the solution
     * Calling arguments: int [] result : The result array
     *                    Vector <Fellowship> fellowships : Vector that stores all the available fellowships
     ******************************************************************************************************************/
    private static void printResult (int [] result , Vector <Fellowship> fellowships , long execution_time) {
        int  final_profit = 0;      //Auxiliary variable that will store the total profit of the solution

        System.out.print("[ ");
        for (int i = 0 ; i < result.length ; i++){
            if(result[i] == 1){
                System.out.print(fellowships.elementAt(i).id+" ");
                final_profit += fellowships.elementAt(i).totalSalary;
            }
        }
        System.out.println("]");

        System.out.println("with a profit of : "+final_profit);
        System.out.println("Execution time : "+execution_time+" ns");
    }
}

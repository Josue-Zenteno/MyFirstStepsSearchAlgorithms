import java.util.Vector;
import java.util.Scanner;
/***********************************************************************************************************************
 * Class Name: Principal
 * Author/s name: Josue Carlos Zenteno Yave and Marina Prieto Pech
 * Release/Creation date: 15/04/2020
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
        System.out.println("We are Josue Carlos Zenteno Yave and Marina Prieto Pech\nWelcome to our program!");
        Vector<Fellowship> fellowships = FileReader.read("Fellowships100.txt");
        int option;
        long Execution_time,initial_time_quicksort=0,final_time_quicksort=0,initial_time_greedy,final_time_greedy;
        while(true){
            option = printMenu();
            switch (option) {
                case 0:
                    System.out.println("Thank you, rest in peace");
                    System.exit(1);
                    break;
                case 1:
                    initial_time_quicksort = System.nanoTime();
                    quicksort(fellowships,1);
                    final_time_quicksort = System.nanoTime();
                    break;
                case 2:
                    initial_time_quicksort = System.nanoTime();
                    quicksort(fellowships,2);
                    final_time_quicksort = System.nanoTime();
                    break;
                default:
                    System.out.println("Choose one of the three possible options, please");
                    break;
            }
            initial_time_greedy=System.nanoTime();
            Vector<Fellowship> result = intervalSchedule(fellowships);
            final_time_greedy=System.nanoTime();
            Execution_time = (final_time_quicksort-initial_time_quicksort)+(final_time_greedy-initial_time_greedy);
            printResults(option , fellowships , result,Execution_time);
        }
    }
    /*******************************************************************************************************************
     * Method name: intervalSchedule
     * Description of the Method: Method to reorder vectors to a decreasing order
     * Calling arguments: Vector<Fellowship> T
     * Return value: Vector<Fellowship> aux
     ******************************************************************************************************************/
    private static Vector <Fellowship> intervalSchedule(Vector <Fellowship> T){
        Vector <Fellowship> Taux = new Vector<>(); //Auxiliary vector to return
        for (int i = T.size()-1 ; i>=0 ; i--){
            Taux.add(T.elementAt(i)); //Adds into aux positions from original vector from last to first
        }
        return greedyAlgorithm(Taux);
    }
    /*******************************************************************************************************************
     * Method name: greedyAlgorithm
     * Description of the Method: Greedy algorithm implementation
     * Calling arguments: Vector<Fellowship> t
     * Return value: Vector<Fellowship> S
     ******************************************************************************************************************/
    private static Vector <Fellowship> greedyAlgorithm(Vector <Fellowship> T){
        Vector <Fellowship> S = new Vector<>(); //Vector to store solution
        for (Fellowship f : T){ //goes through each position of partitioned vector
            if (isCompatible(S,f)){ //if the position selected is compatible to others in solution...
                S.add(f); //It is added to the solution vector
            }
        }
        return S;
    }
    /*******************************************************************************************************************
     * Method name: isCompatible
     * Description of the Method: Comparation of an object compatibility in a given vector
     * Calling arguments: Vector<Fellowships> S (Vector with selected fellowships)
     *                    Fellowship f (selected object to decide whether or not it can be added)
     * Return value: boolean (value used to help greedy algorithm decision making)
     ******************************************************************************************************************/
    private static boolean isCompatible(Vector<Fellowship> S , Fellowship f){
        boolean compatible = true;

        //If element doesn't collide with any of the other intervals it returns true; if it does, false.
        if(!S.isEmpty()){
            for (int i = 0 ; i < S.size() && compatible ; i++){
                compatible = (S.elementAt(i).finalMonth < f.startMonth || S.elementAt(i).startMonth > f.finalMonth);
            }
        }
        return compatible;
    }
    /*******************************************************************************************************************
     * Method name: quicksort
     * Description of the Method: Method to select option 1 or 2 from menu to redirect to the needed quicksort
     * Calling arguments: Vector<Fellowship> fellowships_r
     *                    int option
     ******************************************************************************************************************/
    private static void quicksort(Vector<Fellowship> fellowships_r,int option) {
        if(option == 1) {
            quicksortRec1(fellowships_r, 0, fellowships_r.size() - 1);
        }else{
            quicksortRec2(fellowships_r, 0, fellowships_r.size() - 1);
        }
    }
    /*******************************************************************************************************************
     * Method name: quicksortRec1
     * Description of the Method: Method that applies a divide and conquer strategy
     * Calling arguments: Vector<Fellowship> fellowships_r (vector to be handled)
     *                    Int left (left interval position)
     *                    Int right (right interval position)
     ******************************************************************************************************************/
    private static void quicksortRec1(Vector<Fellowship> fellowships_r, int left, int right) {
        if (left < right) {
            int p = partition1(fellowships_r, left, right);        // Divide
            quicksortRec1(fellowships_r, left, p - 1);       // Conquer
            quicksortRec1(fellowships_r, p + 1, right);
        }
    }
    /*******************************************************************************************************************
     * Method name: partition1
     * Description of the Method: A description of what the method does.
     *
     * Calling arguments: Vector<Fellowship> fellowship_r (vector to get partitioned)
     *                    Int left (left interval position)
     *                    Int right (right interval position)
     * Return value: int (resulting number from hoare's partition)
     ******************************************************************************************************************/
    private static int partition1(Vector<Fellowship> fellowships_r, int left, int right) {
        return HoarePartition1 (fellowships_r, left, right);     // Using Hoare partition
    }
    /*******************************************************************************************************************
     * Method name: HoarePartition1
     * Description of the Method: Method that divides and reorders a given vector according to hoare's partition
     * Calling arguments: Vector<Fellowship> fellowship_r (vector to get partitioned)
     *                    Int left (left interval position)
     *                    Int right (right interval position)
     * Return value: Int r (pivot from partitioning)
     ******************************************************************************************************************/
    private static int HoarePartition1(Vector<Fellowship> fellowships_r, int left, int right) {
        int l = left, r = right+1;
        Fellowship p = fellowships_r.elementAt(left);
        Fellowship aux;
        while (l < r) {
            while (l < right &&  fellowships_r.elementAt(++l).term < p.term);
            while (r > left && p.term < fellowships_r.elementAt(--r).term);
            if (l < r) {
                aux  =  fellowships_r.elementAt(l);
                fellowships_r.remove(l);
                fellowships_r.add(l,fellowships_r.elementAt(r-1));
                fellowships_r.remove(r);
                fellowships_r.add(r,aux);
                //Since we are working with an object vector, to change the positions
                //we make use of an aux  variable to then reintroduce it
            }
        }
        aux = fellowships_r.elementAt(r);
        fellowships_r.remove(left);
        fellowships_r.add(left,aux);
        fellowships_r.remove(r);
        fellowships_r.add(r,p);
        //The same as before is done here,but with different variables
        return r;
    }
    /*******************************************************************************************************************
     * Method name: quicksortRec2
     * Description of the Method: Method that applies a divide and conquer strategy
     * Calling arguments: Vector<Fellowship> fellowships_r (vector to be handled)
     *                    Int left (left interval position)
     *                    Int right (right interval position)
     ******************************************************************************************************************/
    private static void quicksortRec2(Vector<Fellowship> fellowships_r, int left, int right) {
        if (left < right) {
            int p = partition2(fellowships_r, left, right);        // Divide
            quicksortRec2(fellowships_r, left, p - 1);       // Conquer
            quicksortRec2(fellowships_r, p + 1, right);
        }
    }
    /*******************************************************************************************************************
     * Method name: partition2
     * Description of the Method: A description of what the method does.
     *
     * Calling arguments: Vector<Fellowship> fellowship_r (vector to get partitioned)
     *                    Int left (left interval position)
     *                    Int right (right interval position)
     * Return value: int (resulting number from hoare's partition)
     ******************************************************************************************************************/
    private static int partition2(Vector<Fellowship> fellowships_r, int left, int right) {
        return HoarePartition2 (fellowships_r, left, right);     // Using Hoare partition
    }
    /*******************************************************************************************************************
     * Method name: HoarePartition2
     * Description of the Method: Method that divides and reorders a given vector according to hoare's partition
     * Calling arguments: Vector<Fellowship> fellowship_r (vector to get partitioned)
     *                    Int left (left interval position)
     *                    Int right (right interval position)
     * Return value: Int r (pivot from partitioning)
     ******************************************************************************************************************/
    private static int HoarePartition2(Vector<Fellowship> fellowships_r, int left, int right) {
        int l = left, r = right+1;
        Fellowship p = fellowships_r.elementAt(left);
        Fellowship aux;
        while (l < r) {
            while (l < right &&  fellowships_r.elementAt(++l).totalSalary < p.totalSalary);
            while (r > left && p.totalSalary < fellowships_r.elementAt(--r).totalSalary);
            if (l < r) {
                aux  =  fellowships_r.elementAt(l);
                fellowships_r.remove(l);
                fellowships_r.add(l,fellowships_r.elementAt(r-1));
                fellowships_r.remove(r);
                fellowships_r.add(r,aux);
            }
        }
        aux = fellowships_r.elementAt(r);
        fellowships_r.remove(left);
        fellowships_r.add(left,aux);
        fellowships_r.remove(r);
        fellowships_r.add(r,p);
        return r;
    }
    /*******************************************************************************************************************
     * Method name: printResults
     * Description of the Method: Method to print results so the user can see them
     * Calling arguments: int option (heuristic option chosen)
     *                    Vector<Fellowship> fellowships (vector of organized fellowships)
     *                    Vector<Fellowship> solution (vector of selected fellowships)
     ******************************************************************************************************************/
    private static void printResults(int option ,Vector<Fellowship> fellowships, Vector<Fellowship> solution, long Execution_time){
        int profit = 0;
        System.out.println("\nHeuristic : "+option+"\nFellowhips Order :");
        for ( Fellowship f : fellowships){
            System.out.println("\t"+f.toString());
        }
        System.out.println("\nSolution with the selected heuristic : ");
        for ( Fellowship f : solution){
            System.out.println("\t"+f.toString());
            profit += f.totalSalary; //individual profits get added to final total
        }
        System.out.println("The total profit for this solution is : "+profit);
        System.out.println("The execution time is :"+Execution_time+" ns");
        System.out.println("\n");
    }
    /*******************************************************************************************************************
     * Method name: printMenu
     * Description of the Method: Method to print a menu and return selected menu option
     * Return value: int (option chosen)
     ******************************************************************************************************************/
    private static int printMenu(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Choose your fighter:" +
                            "\n[0]  Exit the program" +
                            "\n[1]  Longer-term fellowship" +
                            "\n[2]  Highest total salary");
        return sc.nextInt();
    }
}

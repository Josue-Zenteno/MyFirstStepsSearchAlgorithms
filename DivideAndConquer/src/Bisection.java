import java.util.Scanner;
/***********************************************************************
 * Class Name: Bisection
 * Author/s name: Josue Carlos Zenteno Yave
 *                  Marina Prieto Pech
 * Release/Creation date: 19/03/2020
 * Class version: 1.0
 * Class description: Class that implements the bisection method using dive and conquer
 *************************************************************************/
public class Bisection {
    private static Scanner sc = new Scanner(System.in); //Scanner created to read the keyboard input in all the functions
    /***********************************************************************
     * Method name: main
     * Description of the Method: main method
     * **********************************************************************/
    public static void main(String[] args) {
        double a,b,epsilon; // a and b variables in order to store the values of the first range
        int option; //  variable to store the option and keep the program running
        Function f; // A instance of function class
        do{
            option=Menu(); //Saving the option chosen by the user
            if(option == 1){ //Keep looping if the user don't want to exit
                System.out.print("Set the precision: ");
                epsilon=sc.nextDouble(); //Saving the precision "epsilon"
                if(epsilon != 0){ //Checking if the precision is not 0
                    //Equation_1
                    f = new Function_A(); //Creating a function fo type A
                    System.out.print("\nFor function f(x)=x^2-2x-3 in [0,4]\n\t Root: ");
                    Calculate(0,4,epsilon,f); //Calling to the method Calculate
                    System.out.println("\n---------------------------------------------------");

                    System.out.print("For function f(x)=x^2-2x-3 in [2,2.5]\n\t Root: ");
                    Calculate(2,2.5,epsilon,f);//Calling to the method Calculate
                    System.out.println("\n---------------------------------------------------");

                    //Equation_2
                    f = new Function_B();//Creating a function fo type B
                    System.out.print("For function f(x)=2^x-x^2-10 in [0,6]\n\t Root: ");
                    Calculate(0,6,epsilon,f);
                    System.out.println("\n---------------------------------------------------");

                    //Equation_3
                    f = new Function_C();//Creating a function fo type C
                    System.out.print("For function f(x)=sin(x)-1/x in [2pi,5pi/2]\n\t Root: ");
                    Calculate(2*Math.PI,(5*Math.PI)/2,epsilon,f);//Calling to the method Calculate
                    System.out.println("\n---------------------------------------------------");
                }else{
                    System.out.println("Precision should be different to 0");
                }
            }
        }while(option == 1);
        System.out.println("Good Bye!");
    }
    /***************************************************************************************
     * Method name: Menu
     * Description of the Method: Prints the menu and return the option chosen by the user
     * Return value:
     *              -the option read by the scanner
     * ************************************************************************************/
    private static int Menu(){
        System.out.println("Choose one option" +
                "\n\t[1] ~ Set the precision" +
                "\n\t[Any number] ~ Exit");
        return sc.nextInt();
    }
    /***************************************************************************************************
     * Method name: Calculate
     * Description of the Method: This method checks if Bolzano's theorem applies in the selected range
     * Calling arguments:
     *                      - double a : The first value in the range [x,b]
     *                      - double b : The second value in the range [a,x]
     *                      - double epsilon : The precision
     *                      - Function f : The chosen function
     * *************************************************************************************************/
    private static void Calculate(double a, double b, double epsilon, Function f){
        if( (f.calculate(a) > 0 && f.calculate(b) <0) ||  (f.calculate(a) < 0 && f.calculate(b) > 0)){ //Checks if f(a) and f(b) has a different sign
            System.out.print(findRoot(a,b,epsilon,f)); //If it's true it implements the method
        }else{
            System.out.print("Bolzano's Law does not apply here");
        }
    }
    /**************************************************************************************************************************
     * Method name: findRoot
     * Description of the Method: Method that applies the Divide and Conquer algorithm, in order to solve the bisection method
     * Calling arguments:
     *                      - double a : The first value in the range [x,b]
     *                      - double b : The second value in the range [a,x]
     *                      - double epsilon : The precision
     *                      - Function f : The chosen function
     *
     * Return value:
     *                  - double root : The root obtained
     * ************************************************************************************************************************/
    private static double findRoot(double a,double b, double epsilon,Function f){
        double fa,fc,c,root; // Variable used to store the results of functions, the midpoint "c" , and the final root
        c=((a+b)/2); // Midpoint calculation
        if(Math.abs(f.calculate(c)) <= epsilon){ //Checks if the result is a valid solution
            root=c;
        }else{//Else it proceeds to call itself
            fa=f.calculate(a); //saves the result of applying the formula in the point a
            fc=f.calculate(c); //saves the result of applying the formula in the point c
            if((fa*fc)<0){ //Check this conditions in order to know if the new interval must be the left one or the right one
                root = findRoot(a,c,epsilon,f);
            }else{
                root = findRoot(c,b,epsilon,f);
            }
        }
        return root;
    }

}



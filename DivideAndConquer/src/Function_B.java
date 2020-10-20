/***********************************************************************
 * Class Name: Function
 * Author/s name: Josue Carlos Zenteno Yave
 *                  Marina Prieto Pech
 * Release/Creation date: 19/03/2020
 * Class version: 1.0
 * Class description: Inherits from Function
 *************************************************************************/
public class Function_B extends Function{
    /***********************************************************************
     * Method name: calculate
     * Description of the Method: Constructor of the class with an empty body
     ***********************************************************************/
    public Function_B(){}
    /***********************************************************************
     * Method name: calculate
     * Description of the Method: Applies a formula
     * Calling arguments:
     *                      - double x : X used in the formula
     * Return value:
     *                  - The result of the formula
     * **********************************************************************/
    public double calculate (double x){return (Math.pow(2,x)) - (Math.pow(x,2)) - 10;}//Returns the result of this formula
}

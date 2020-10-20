/***********************************************************************
 * Class Name: Function
 * Author/s name: Josue Carlos Zenteno Yave
 *                  Marina Prieto Pech
 * Release/Creation date: 19/03/2020
 * Class version: 1.0
 * Class description: Abstract class that uses polymorphism in the method Calculate and inherits it to Function_A ,Function_B, Function_C classes
 *************************************************************************/
public abstract class Function {
    /***********************************************************************
     * Method name: calculate
     * Description of the Method: Constructor of the class with an empty body
     ***********************************************************************/
    public Function(){ }
    /***********************************************************************
     * Method name: calculate
     * Description of the Method: Applies a formula
     * Calling arguments:
     *                      - double x : X used in the formula
     * Return value:
     *                  - The result of the formula
     * **********************************************************************/
    abstract public double calculate (double x);
}

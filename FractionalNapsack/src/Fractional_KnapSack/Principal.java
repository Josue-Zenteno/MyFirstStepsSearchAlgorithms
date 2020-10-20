package Fractional_KnapSack;
/***********************************************************************
 * Class Name: Principal
 * Author/s name: Josue Carlos Zenteno Yave
 * Release/Creation date: 16/03/2020
 * Class version: 1.0
 * Class description: Main class
 *************************************************************************/
public class Principal {
    /******************************************************
     * Method name: main
     * Description of the Method: This is the main class.
     * ***************************************************/
    public static void main(String[] args) {
        Item [] Items = fillItemsMatrix(); //Here we fill the matrix of Items
        Item [] solution = fractionalKnapSack(15,Items,Items.length); //Here we save the result of the algorithm
        //Here we start printing the result
        System.out.print("KnapSack : {");
        for(int i=0;i<solution.length && solution[i] != null ;i++){
            System.out.print(" "+solution[i].getName());
        }
        System.out.print(" }");
    }
    /*******************************************************************************************************************
     * Method name: fractionalKnapSack
     * Description of the Method: Here we implement the greedy algorithm in order to know which items include in our
     *                              knapSack.
     * Calling arguments:
     *                  - int weight : The maximum weight of our knapSack
     *                  - Item [] Items : The array of Items
     *                  -int n_items : The number of Items in the array
     * Return value:
     *                  -Item [] : The array, now composed of the chosen Items
     * ****************************************************************************************************************/
    private static Item [] fractionalKnapSack(int weight,Item [] Items, int n_items){
        sortPerRatio(Items); //Call to the sortPerRatio() in order to sort the array
        Item [] solution = new Item [Items.length]; // Create a solution array of Items with the original array's length
        int curWeight=0; //The current weight set to 0, in order to start the loop

        for(int i=0; i<n_items-1;i++){ //for loop comparing the sorted array items and checking if they are valid solutions or not
            if((curWeight+Items[i].getWeight()) <= weight){ //If the are, we save them in the solution array
                curWeight += Items[i].getWeight();
                solution[i] = Items[i];
            }else{ //As we are implementing a fractional KnapSack we can divide the last item by order
                int remain = weight - curWeight;
                 Items[i].setName((double)remain/Items[i].getWeight()+" of "+Items[i].getName());
                 solution[i] = Items[i];
                break;
            }
        }
        return solution; //return the solution array
    }
    /**************************************************************************
     * Method name: fillItemsMatrix
     * Description of the Method: Here we fill the matrix of Items manually
     * Return value:
     *              -Item [] : Filled array
     *************************************************************************/
    private static Item [] fillItemsMatrix(){
        Item [] Aux = new Item [8]; //Auxiliar matrix to return
        Aux[0]=new Item (3,14,"a");
        Aux[1]=new Item (1,5,"b");
        Aux[2]=new Item (6,10,"c");
        Aux[3]=new Item (4,12,"d");
        Aux[4]=new Item (2,8,"e");
        Aux[5]=new Item (4,10,"f");
        Aux[6]=new Item (8,16,"g");
        Aux[7]=new Item (9,9,"h");
        return Aux;
    }
    /*******************************************************************************************************************
     * Method name: sortPerRatio
     * Description of the Method: It sorts the array of Items focusing on it's ratios.
     * Calling arguments:
     *                      -Item [] Items : The Items array in order to sort it.
     ******************************************************************************************************************/
    private static void sortPerRatio(Item [] Items){
        int checker=0; //Variable used as a counter of successes where a success means that the ratio in the i position is higher than the ratio in the i+1 position
        while(checker < Items.length-1){ //It will continue until we have all the items sorted (7 successes in a 8 items array in this case)
            checker=0;
            for(int i=0;i<Items.length-1;i++){ //Loop that checks the ratios and change the position of the Items if it's needed
                if(Items[i].getRatio() < Items[i+1].getRatio()){
                    Item auxItem = Items[i+1];
                    Items[i+1] = Items[i];
                    Items[i] = auxItem;
                }else{
                    checker++;
                }
            }
        }
    }
}

package Fractional_KnapSack;
/*************************************************************************************
 * Class Name: Item
 * Author/s name: Josue Carlos Zenteno Yave
 * Release/Creation date: 16/03/2020
 * Class version: 1.0
 * Class description: Item class created in order to save the information of an item
 ************************************************************************************/
class Item {
    private int weight,value; //Attributes of the class
    private double ratio; //Attribute of the class
    private String name; //Attribute of the class

    /*******************************************************************************************************************
     * Method name: Item
     * Description of the Method: Constructor of the class.
     * Calling arguments:
     *                  -int weight : the weight of the item.
     *                  -int value : the value of the item.
     *                  -String name : the name of the item. (In this case characters as 'a' 'b' 'c' or 'd')
     *****************************************************************************************************************/
    Item(int weight, int value, String name) {
        this.weight = weight;
        this.value = value;
        this.name = name;
        this.ratio = getValue()/getWeight();
    }

    /*******************************************************************************************************************
     * Method name: Getters & Setters
     * Description of the Method: Getters and Setters of the attributes of the class.
     *****************************************************************************************************************/
    int getWeight() {
        return weight;
    }
    int getValue() {
        return value;
    }
    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
    double getRatio() {
        return ratio;
    }
    void setRatio(double ratio) {
        this.ratio = ratio;
    }
}

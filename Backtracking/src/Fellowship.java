/***********************************************************************************************************************
 * Class Name: Fellowship
 * Author/s name: Josue Carlos Zenteno Yave and Marina Prieto Pech
 * Release/Creation date: 26/04/2020
 * Class version: 1.0
 * Class description: Class that stores the information related to a fellowship
 **********************************************************************************************************************/
class Fellowship {
    public int id;
    public int startMonth;
    public int finalMonth;
    public int monthlySalary;
    public int totalSalary;
    /*******************************************************************************************************************
     * Method name: Fellowship
     * Description of the Method: Constructor of the class that only needs it's identifier
     * Calling arguments: int id : Identifier of the fellowship
     ******************************************************************************************************************/
     Fellowship (int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Fellowship{" +
                "id=" + id +
                ", startMonth=" + startMonth +
                ", finalMonth=" + finalMonth +
                ", monthlySalary=" + monthlySalary +
                ", totalSalary=" + totalSalary +
                '}';
    }
}

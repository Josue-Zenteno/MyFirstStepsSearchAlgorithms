import java.io.File;
import java.io.BufferedReader;
import java.util.Vector;
import java.util.StringTokenizer;
/***********************************************************************************************************************
 * Class Name: FileReader
 * Author/s name: Josue Carlos Zenteno and Marina Prieto Pech
 * Release/Creation date: 15/04/2020
 * Class version: 1.0
 * Class description: Class that reads a file and saves it's information as fellowships
 **********************************************************************************************************************/
class FileReader {
    /*******************************************************************************************************************
     * Method name: read
     * Description of the Method: Method that reads a given file
     * Calling arguments:
     - String path : Path of the file (where it's located in the computer)
     * Required Files:  fellowshipsX.txt
     ******************************************************************************************************************/
    static Vector<Fellowship> read (String path) {
        File file = new File(path);     //We create a File object that will be read
        Vector<Fellowship> vector = new Vector<>();     //We create a vector to store the information of the file
        readFile(file, vector);     //Method to read the File
        return vector;      //We return the vector, but now it's filled with the fellowships
    }
    /*******************************************************************************************************************
     * Method name: readFile
     * Description of the Method: Method that reads a given file
     * Calling arguments:
     - Vector<Fellowship> vector : Vector that we have to fill with fellowships
     * Required Files:  fellowshipsX.txt
     *                  - File File already opened for read method, this is the file that we have to read
     * List of Checked Exceptions: FileNotFoundException, when the system can not find the file in the indicated path
     *                  -
     ******************************************************************************************************************/
    private static void readFile(File File, Vector<Fellowship> vector ) {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(File));       //We create a buffered reader to read the file
            String line;        //Auxiliary line that will contain each line of the file
            while((line = br.readLine()) != null){      //Loop that reads the file line per line
                StringTokenizer st = new StringTokenizer(line, "\t");       //Tokenizer that tokenize the lines
                Fellowship f = new Fellowship(Integer.parseInt(st.nextToken()));    //We create a fellowships and then we initialize it's attributes
                f.startMonth = Integer.parseInt(st.nextToken());
                f.finalMonth = Integer.parseInt(st.nextToken());
                f.monthlySalary = Integer.parseInt(st.nextToken());
                f.totalSalary = (f.finalMonth - f.startMonth + 1) * f.monthlySalary ;
                f.term = (f.finalMonth - f.startMonth + 1);
                vector.add(f);      //We add the fellowship to the vector of fellowships
            }
            br.close();
        }catch(Exception ex){
            System.out.println("Message: " + ex.getMessage());
        }
    }
}
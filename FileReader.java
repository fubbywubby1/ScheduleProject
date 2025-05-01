import java.io.*;
import java.util.HashMap;

/**
 * Handles File I/O for Schedule by saving and loading the class contents
 * Serializes Schedules into transferable files
 * Stores the contents of Schedules in .txt files
 * Returns file information about stored Schedules
 * 
 * @version 2.0
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */

public class FileReader {

    private static HashMap<String, File> files;
    
    /**
     * Serializes and saves the currently stored Scheduled data under the given name
     * 
     * @param schedule
     * @return True if save is successful, false otherwise
     */
    public static boolean saveAs(String name) {
        try {
            File file;
            if(files.containsKey(name)) {
                file = files.get(name);
            } else {
                file = new File(name + ".ser");
                files.put(name, file);
            }

            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(output);
            out.writeObject(Schedule.scheduleMap);
            out.close();
            output.close();
            return true;
        } catch(IOException e) {
            return false;
        }
    }

    /**
     * Serializes and saves the currently stored Schedule data
     * 
     * @param schedule
     * @throws IOException
     * @return True if save is successful, false otherwise
     */
    public static boolean save() {
        return saveAs(Schedule.getName());
    }

    /**
     * Loads the data stored under the given name into the Schedule class
     * 
     * @return
     * @throws IOException
     */
    public static boolean load(String name) throws IOException{

        if(!files.containsKey(name)) return false;
        File file = files.get(name);

        FileInputStream input = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(input);

        try {
            Schedule.scheduleMap = (HashMap)in.readObject();
            in.close();
            input.close();
            return true;
            
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            in.close();
            input.close();
            return false;
        }
    }

}

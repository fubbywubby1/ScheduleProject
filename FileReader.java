import java.io.*;
import java.util.HashMap;
import java.util.Set;

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
    private static HashMap<String, File> files = new HashMap<>();

    // Scan the current directory for .ser files and populate files map
    public static void initializeFileList() {
        File dir = new File(".");
        File[] serFiles = dir.listFiles((d, name) -> name.endsWith(".ser"));
        if (serFiles != null) {
            for (File f : serFiles) {
                String name = f.getName().replace(".ser", "");
                files.put(name, f);
            }
        }
    }

    public static Set<String> getSavedScheduleNames() {
        return files.keySet();
    }

    public static boolean saveAs(String name) {
        try {
            File file = files.containsKey(name) ? files.get(name) : new File(name + ".ser");
            files.put(name, file);

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

    public static boolean save() {
        return saveAs(Schedule.getName());
    }

    public static boolean load(String name) throws IOException {
        if(!files.containsKey(name)) return false;
        File file = files.get(name);

        FileInputStream input = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(input);

        try {
            Schedule.scheduleMap = (HashMap) in.readObject();
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

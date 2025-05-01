import java.io.*;
import java.util.HashMap;
import java.util.Set;

import java.util.logging.Logger;

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
    private static final String FOLDER_NAME = "schedules"; // Folder for storing serialized files
    private static HashMap<String, File> files = new HashMap<>();

    private static final Logger logger = Logger.getLogger("FileReader");

    // Initialize the folder where the files will be saved
    static {
        File dir = new File(FOLDER_NAME);
        if (!dir.exists()) {
            dir.mkdir(); // Create the folder if it doesn't exist
        }
        logger.info("Created folder to store schedule.");
    }

    // Scan the schedules folder for .ser files and populate the files map
    public static void initializeFileList() {
        File dir = new File(FOLDER_NAME);
        File[] serFiles = dir.listFiles((d, name) -> name.endsWith(".ser"));
        if (serFiles != null) {
            for (File f : serFiles) {
                String name = f.getName().replace(".ser", "");
                files.put(name, f);
            }
        }
        logger.info("Initialized the File list.");
    }

    public static Set<String> getSavedScheduleNames() {
        return files.keySet();
    }

    // Save the schedule to the "schedules" folder with a given name
    public static boolean saveAs(String name) {
        try {
            // Ensure the folder is created
            File folder = new File(FOLDER_NAME);
            if (!folder.exists()) {
                folder.mkdir();
                logger.info("Made directory.");
            }

            // Construct the file path inside the schedules folder
            File file = files.containsKey(name) 
                    ? files.get(name) 
                    : new File(FOLDER_NAME + File.separator + name + ".ser");

            files.put(name, file);

            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(output);
            out.writeObject(Schedule.scheduleMap);
            out.close();
            output.close();
            logger.info("Successfully saved the schedule.");
            return true;
        } catch (IOException e) {
            logger.warning("Could not save the schedule.");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean save() {
        return saveAs(Schedule.getName());
    }

    // Load a schedule from the "schedules" folder
    public static boolean load(String name) throws IOException {
        if (!files.containsKey(name)) return false;
        
        File file = files.get(name);
        
        try (FileInputStream input = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(input)) {
    
            Schedule.scheduleMap = (HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>>) in.readObject();
            logger.info("Loaded the schedule.");
            return true;
        } catch (ClassNotFoundException e) {
            logger.warning("Failed to load schedule");
            e.printStackTrace();  // Handle the ClassNotFoundException
            return false;
        }
    }
}


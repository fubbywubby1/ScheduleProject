import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Specifically holds the universal static schedule we use for a week. 
 * Also holds a dummy test schedule.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class Schedule implements Serializable {

    private static final Logger logger = Logger.getLogger("Schedule");

    public static HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> scheduleMap = new HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>>();
    private static HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> testScheduleMap;
    private static String name = "name";;

    static {
        scheduleMap.put(DaysOfTheWeek.Sunday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Monday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Tuesday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Wednesday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Thursday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Friday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Saturday, new HashMap<>());
        testScheduleMap = scheduleMap;
        logger.info("Initialized schedule maps for all days of the week.");
    }

    /**
     * refreshes the test schedule, then returns it
     * @return testSchedule
     */
    public static HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> getTestSchedule() {
    HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> copy = new HashMap<>();
    for (Map.Entry<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> entry : scheduleMap.entrySet()) {
        copy.put(entry.getKey(), new HashMap<>(entry.getValue())); // shallow copy of each day's map
    }
    logger.fine("Returning a copy of the test schedule.");
    return copy;
}


    /**
     * sets the name of the schedule
     * @param name is the new name
     */
    public static void setName(String name) {
        logger.info("Schedule name set to: " + name);
        Schedule.name = name;
    }

    /**
     * Returns instance var name
     * @return name
     */
    public static String getName() {
        return name;
    }

    /**
     * Simply adds the given variables to the schedule
     * @param newChunk
     * @param newBlock
     * @param day
     * @throws UnableToScheduleException if any variables are invalid
     */
    public static void add(TimeChunk newChunk, TimeBlockable newBlock, DaysOfTheWeek day) throws UnableToScheduleException {
        try {
            logger.fine("Attempting to add to schedule: " + newChunk + " -> " + newBlock + " on " + day);
            TimeHandler.addToTimeBlock(newChunk, newBlock, scheduleMap.get(day));
        } catch (UnableToScheduleException e) {
            logger.log(Level.WARNING, "Unable to add to schedule on " + day + ": " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Simply adds the given variables to the testSchedule
     * @param newChunk
     * @param newBlock
     * @param day
     * @throws UnableToScheduleException is thrown if any variables are invalid
     */
    public static void addToTestMap(TimeChunk newChunk, TimeBlockable newBlock, DaysOfTheWeek day) throws UnableToScheduleException {
        try {
            logger.fine("Attempting to add to test schedule: " + newChunk + " -> " + newBlock + " on " + day);
            TimeHandler.addToTimeBlock(newChunk, newBlock, testScheduleMap.get(day));
        } catch (UnableToScheduleException e) {
            logger.log(Level.WARNING, "Unable to add to test schedule on " + day + ": " + e.getMessage(), e);
            throw e;
        }
    }
}

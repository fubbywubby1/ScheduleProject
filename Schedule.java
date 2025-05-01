import java.util.HashMap;
import java.util.Map;

/**
 * Specifically holds the universal static schedule we use for a week. 
 * Also holds a dummy test schedule.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class Schedule {

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
    return copy;
}


    /**
     * sets the name of the schedule
     * @param name is the new name
     */
    public static void setName(String name) {
        Schedule.name = name;
    }

    /**
     * Returns instance var name
     * @return name
     */
    public static String getName() {
        return name;
    }

    public static void add(TimeChunk newChunk, TimeBlockable newBlock, DaysOfTheWeek day) throws UnableToScheduleException {
        try {
            TimeHandler.addToTimeBlock(newChunk, newBlock, scheduleMap.get(day));
        } catch (UnableToScheduleException e) {
            throw e;
        }
    }

    public static void addToTestMap(TimeChunk newChunk, TimeBlockable newBlock, DaysOfTheWeek day) throws UnableToScheduleException {
        try {
            TimeHandler.addToTimeBlock(newChunk, newBlock, testScheduleMap.get(day));
        } catch (UnableToScheduleException e) {
            throw e;
        }
    }
}

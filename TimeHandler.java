/*
 * TimeHandler.java
 * Handles the time blocks for the schedule, and checks for time conflicts.
 * 
 * @author Xander Simonson
 */
import java.util.HashMap;
public class TimeHandler {
    public static HashMap<Integer, Event> timeBlocks = new HashMap<>();;

    /*
     * Adds a task to the time block.
     * @param key The time block key (in 24-hour format)
     * @param value The task to be added
     * @throws Exception if the key is invalid or the value is null
     */
    public static void addToTimeBlock(Integer key, Task value) throws Exception {
        if (key > 2359 || key < 0)  {
            throw new Exception("Invalid Time");
        } else if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            timeBlocks.put(key, value);
        }
    }

    /*
     * Adds an Event the time block.
     * @param key The time block key (in 24-hour format)
     * @param value The Event to be added
     * @throws Exception if the key is invalid or the value is null
     */
    public static void addToTimeBlock(Integer key, Event value) throws Exception {
        if (key > 2359 || key < 0)  {
            throw new Exception("Invalid Time");
        } else if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            timeBlocks.put(key, value);
        }
    }

    /*
     * Returns the time block for the given key.
     * @param key The time block key (in 24-hour format)
     */
    public static Object getTimeBlock(Integer key) {
        return timeBlocks.get(key);
    }

    /*
     * Removes the time block for the given key.
     * @param key The time block key (in 24-hour format)
     */
    public static void removeTimeBlock(Integer key) {
        timeBlocks.remove(key);
    }

    /*
     * Removes the time block for the given event.
     * @param value The event to be removed
     * @throws Exception if the value is null
     */
    public static void removeTimeBlockByEvent(Object value) throws Exception {
        if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            for (Integer key: timeBlocks.keySet()) {
                if (timeBlocks.get(key).equals(value)) {
                    timeBlocks.remove(key);
                }
            }
        }
    }

    /*
     * Checks if the given event conflicts with any existing time blocks.
     * @param checkedEvent The event to be checked
     * @return true if there is a conflict, false otherwise
     */
    public static Boolean checkTimeConflict(Event checkedEvent) {
        if (timeBlocks.isEmpty() == false) {
            if (checkedEvent.getStartTime() > 0 && checkedEvent.getEndTime() < 2400) {
                for (int blockToCheck: timeBlocks.keySet()) {
                    if (checkedEvent.getStartTime() >= timeBlocks.get(blockToCheck).getStartTime() &&
                        checkedEvent.getEndTime() <= timeBlocks.get(blockToCheck).getEndTime()) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                } else {
                    return false;
                }
        } else {
            return true;
        }
        return false;
    }
}

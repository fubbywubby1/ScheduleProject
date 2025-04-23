import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalTime;
public class TimeHandler {
    public static HashMap<TimeChunk, Event> timeBlocks = new HashMap<>();

    //To Do:
    //Make sure there's not time conflicts when adding a timeBlock
    public static void addToTimeBlock(TimeChunk key, Event value) throws Exception {
        if ((key.getEndTime().isBefore(LocalTime.MAX) && key.getEndTime().isAfter(LocalTime.MIN)) 
            && key.getStartTime().isBefore(LocalTime.MAX) && key.getStartTime().isAfter(LocalTime.MIN))  {
            throw new Exception("Invalid Time");
        } else if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            timeBlocks.put(key, value);
        }
    }

    public static TimeChunk getTimeBlock(TimeChunk key) {
        return timeBlocks.get(key);
    }

    public static void removeTimeBlock(TimeChunk key) {
        timeBlocks.remove(key);
    }

    public static void removeTimeBlockByEvent(Event value) throws Exception {
        if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            for (TimeChunk key: timeBlocks.keySet()) {
                if (timeBlocks.get(key).equals(value)) {
                    timeBlocks.remove(key);
                }
            }
        }
    }

    /**
     * To check a given timeChunk,
     * first, create a set of all the keys in the hashmap,
     * then stream through a new set, filter in this pattern:
     * Filter out elements where the input's starttime is after the elements endtime,
     * Filter out elements where the input's endtime is before the elements starttime,
     * Then, if the input's starttime is after the elements starttime or the inputs endtime is before the elements endtime,
     * add it to a collection
     * If the collection has elements in the end, then there is a conflict
     * Otherwise, there is not
     * @param checkTimeChunk is what we are testing
     * @return true if the collection is empty, false if it has elements
     */
    public static Boolean checkTimeConflict(TimeChunk checkTimeChunk) {
        Set<TimeChunk> set = timeBlocks.keySet();
        Set<TimeChunk> toCheck = set.stream().filter(t -> checkTimeChunk.getStartTime().isBefore(t.getEndTime()))
                                             .filter(t -> checkTimeChunk.getEndTime().isAfter(t.getStartTime()))
                                             .filter(t -> checkTimeChunk.getStartTime().isAfter(t.getStartTime()) 
                                                          || checkTimeChunk.getEndTime().isBefore(t.getEndTime()))
                                             .collect(Collectors.toSet());
        if (toCheck.isEmpty() == true) {
            return true;
        } else {
            return false;
        }
    }
    
}

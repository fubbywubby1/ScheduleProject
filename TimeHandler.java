import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
public class TimeHandler {
    public static HashMap<TimeChunk, Event> timeBlocks = new HashMap<>();;

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

    public static Object getTimeBlock(TimeChunk key) {
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

    /*
    public static Boolean checkTimeConflict(TimeChunk checkTimeChunk) {
        if (timeBlocks.isEmpty() == false) {
            if (checkTimeChunk.getStartTime().isAfter(LocalTime.MIN) && checkTimeChunk.getEndTime().isBefore(LocalTime.MAX)) {
                for (TimeChunk blockToCheck: timeBlocks.keySet()) {
                    if (checkTimeChunk.getStartTime().compareTo(blockToCheck.getStartTime()) >= 0 
                        && checkTimeChunk.getEndTime().compareTo(blockToCheck.getEndTime()) <= 0) {
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

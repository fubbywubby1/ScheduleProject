import java.util.HashMap;
import java.time.LocalTime;
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

    public static Object getTimeBlock(Integer key) {
        return timeBlocks.get(key);
    }

    public static void removeTimeBlock(Integer key) {
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

    public static Boolean checkTimeConflict(TimeChunk checkTimeChunk) {
        if (timeBlocks.isEmpty() == false) {
            if (checkTimeChunk.getStartTime().isAfter(LocalTime.MIN) && checkTimeChunk.getEndTime().isBefore(LocalTime.MAX)) {
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

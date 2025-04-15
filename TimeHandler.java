import java.util.HashMap;
public class TimeHandler {
    public static HashMap<Integer, Event> timeBlocks = new HashMap<>();;

    public static void addToTimeBlock(Integer key, Task value) throws Exception {
        if (key > 2359 || key < 0)  {
            throw new Exception("Invalid Time");
        } else if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            timeBlocks.put(key, value);
        }
    }

    public static void addToTimeBlock(Integer key, Event value) throws Exception {
        if (key > 2359 || key < 0)  {
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

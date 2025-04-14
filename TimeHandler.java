import java.util.HashMap;
public class TimeHandler {
    private HashMap<Integer, Object> timeBlocks;
    public TimeHandler() {
        timeBlocks = new HashMap<>();
    }

    public void addToTimeBlock(Integer key, Task value) throws Exception {
        if (key > 2359 || key < 0)  {
            throw new Exception("Invalid Time");
        } else if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            timeBlocks.put(key, value);
        }
    }

    public void addToTimeBlock(Integer key, Event value) throws Exception {
        if (key > 2359 || key < 0)  {
            throw new Exception("Invalid Time");
        } else if (value.equals(null)) {
            throw new Exception("Null input");
        } else {
            timeBlocks.put(key, value);
        }
    }

    public Object getTimeBock(Integer key) {
        return timeBlocks.get(key);
    }

    public void removeTimeBlock(Integer key) {
        timeBlocks.remove(key);
    }

    public void removeTimeBlockByEvent(Object value) throws Exception {
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
}

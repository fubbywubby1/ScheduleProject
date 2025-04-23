import java.util.Set;
import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap;
import java.util.Map.Entry;

interface Optimizer {
    public static void optimize() {
        Set<TimeChunk> keys = TimeHandler.timeBlocks.keySet();
        HashMap<TimeChunk, Integer> chunkPriority = getPrioritys();
        HashMap<TimeChunk, Entry<LocalTime, LocalTime>> chunkTime = getTimes();
        HashMap<TimeChunk, Label> chunkLabel = getLabels();
        /*
         * Idea:
         * Stream through the hashmap three different times
         * each time, map each event to its:
         * a. label
         * b. priority
         * c. time
         * Then, compare them together through a comparator
         * Return the best fitting activity
         */

    }

    private static HashMap<TimeChunk, Integer> getPrioritys() {
        HashMap<TimeChunk, Integer> chunkPriority = new HashMap<>();
        for (TimeChunk key: TimeHandler.timeBlocks.keySet()) {
            chunkPriority.put(key, TimeHandler.timeBlocks.get(key).getPriority());
        }
        return chunkPriority;
    }

    private static HashMap<TimeChunk, Entry<LocalTime, LocalTime>> getTimes() {
        HashMap<TimeChunk, Entry<LocalTime, LocalTime>> chunkTime = new HashMap<>();
        for (TimeChunk key: TimeHandler.timeBlocks.keySet()) {
            Map.Entry<LocalTime, LocalTime> keyTimes = Map.entry(TimeHandler.getTimeBlock(key).getStartTime(), TimeHandler.getTimeBlock(key).getEndTime());
            chunkTime.put(key, keyTimes);
        }
        return chunkTime;
    }

    private static HashMap<TimeChunk, Label> getLabels() {
        HashMap<TimeChunk, Label> chunkLabel = new HashMap<>();
        for (TimeChunk key: TimeHandler.timeBlocks.keySet()) {
            chunkLabel.put(key, TimeHandler.timeBlocks.get(key).getLabel());
        }
        return chunkLabel;
    }
}
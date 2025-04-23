import java.util.Set;
import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap;
import java.util.Map.Entry;

interface Optimizer {
    public static double optimize() {
        HashMap<Label, Integer> labelStress = RateStressPage.LabelStress;
        double stressAVG = labelStress.values()
                            .stream()
                            .mapToInt(Integer::intValue)
                            .average()
                            .getAsDouble();
        return stressAVG;
    }

    /*
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
    */
}
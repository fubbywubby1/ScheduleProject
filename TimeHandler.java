import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalTime;

import java.util.logging.Logger;

/**
 * This class is composed of static methods that can universally be used to 
 * alter and deal with TimeChunks and TimeBlockables together.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class TimeHandler {

    private static final Logger logger = Logger.getLogger("TimeHandler");

    /**
     * This method intakes the TimeChunk, TimeBlockable and HashMap that they are to be added to
     * and tests to ensure that under all circumstances they can be put in that hashmap
     * @look at checkNoTimeConflict
     * @param key is a TimeChunk, that we are trying to place as a key in the hashmap
     * @param value is a TimeBlockable, that we are trying to place as a value in the hashmap
     * @param timeBlocks is the targeted hashmap that we want to place these in
     * @throws UnableToScheduleException is thrown if the TimeChunk or TimeBlockable is invalid under any circumstance
     */
    public static void addToTimeBlock(TimeChunk key, TimeBlockable value, HashMap<TimeChunk, TimeBlockable> timeBlocks) throws UnableToScheduleException {
        if (!((key.getEndTime().isBefore(LocalTime.MAX) && key.getEndTime().isAfter(LocalTime.MIN)) 
        && key.getStartTime().isBefore(LocalTime.MAX) && key.getStartTime().isAfter(LocalTime.MIN)))  {
            logger.warning("Starttime or/and endtime are out of bounds");
            throw new UnableToScheduleException(value, key);
        } else if (value.equals(null)) {
            logger.warning("Value is null");
            throw new UnableToScheduleException(value, key);
        } else if (!(checkNoTimeConflict(key, timeBlocks).isEmpty())) {
            logger.warning("Time conflict found");
            throw new UnableToScheduleException(value, key, checkNoTimeConflict(key, timeBlocks));
        } else {
            timeBlocks.put(key, value);
        }
    }

    /**
     * Removes a given timeblock by the given event
     * @param value is what event we are searching for
     * @param timeBlocks is the respective day to find
     * @throws UnableToScheduleException is in case it is given an invalid event
     */
    public static void removeTimeBlockByEvent(Event value, HashMap<TimeChunk, Event> timeBlocks) throws UnableToScheduleException {
        if (value.equals(null)) {
            logger.warning("Null value.");
            throw new UnableToScheduleException(value);
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
     * @return the List of elements to check
     */
    public static List<TimeChunk> checkNoTimeConflict(TimeChunk checkTimeChunk, HashMap<TimeChunk, TimeBlockable> timeBlocks) {
        return timeBlocks.keySet().stream()
            .filter(t -> checkTimeChunk.getStartTime().isBefore(t.getEndTime()) && 
                         checkTimeChunk.getEndTime().isAfter(t.getStartTime()))
            .collect(Collectors.toList());
    }
}

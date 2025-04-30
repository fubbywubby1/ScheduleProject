import java.util.ArrayList;
import java.util.List;

public class UnableToScheduleException extends Exception {
    private List<Event> invalidEvents;
    private int numOfInvalidEvents;

    private TimeChunk invalidTimeChunk;

    private List<TimeChunk> conflictingChunks;

    public UnableToScheduleException(List<Event> invalidEvents) {
        this.invalidEvents = invalidEvents;
        this.numOfInvalidEvents = invalidEvents.size();
    }

    public UnableToScheduleException(Event invalidEvent) {
        this.invalidEvents = new ArrayList<>();
        invalidEvents.add(invalidEvent);
        this.numOfInvalidEvents = invalidEvents.size();
    }

    public UnableToScheduleException(List<Event> invalidEvents, TimeChunk invalidTimeChunk) {
        this.invalidEvents = invalidEvents;
        this.numOfInvalidEvents = invalidEvents.size();
        this.invalidTimeChunk = invalidTimeChunk;
    }

    public UnableToScheduleException(Event invalidEvent, TimeChunk invalidTimeChunk) {
        this.invalidEvents = new ArrayList<>();
        invalidEvents.add(invalidEvent);
        this.numOfInvalidEvents = invalidEvents.size();
        this.invalidTimeChunk = invalidTimeChunk;
    }

    public UnableToScheduleException(List<Event> invalidEvents, List<TimeChunk> conflictingChunks) {
        this.invalidEvents = invalidEvents;
        this.numOfInvalidEvents = invalidEvents.size();
        this.conflictingChunks = conflictingChunks;
    }

    public UnableToScheduleException(Event invalidEvent, List<TimeChunk> conflictingChunks) {
        this.invalidEvents = new ArrayList<>();
        invalidEvents.add(invalidEvent);
        this.numOfInvalidEvents = invalidEvents.size();
        this.conflictingChunks = conflictingChunks;
    }

    public UnableToScheduleException(Event invalidEvent, TimeChunk invalidChunk, List<TimeChunk> conflictingChunks) {
        this.invalidEvents = new ArrayList<>();
        invalidEvents.add(invalidEvent);
        this.numOfInvalidEvents = invalidEvents.size();
        this.conflictingChunks = conflictingChunks;
        this.invalidTimeChunk = invalidChunk;
    }

    public List<Event> getInvalidEvents() {
        return invalidEvents;
    }

    public int getNumOfInvalidEvents() {
        return numOfInvalidEvents;
    }

    public TimeChunk getInvalidTimeChunk() {
        return invalidTimeChunk;
    }

    public List<TimeChunk> getConflictingChunks() {
        return conflictingChunks;
    }
}

import java.util.ArrayList;
import java.util.List;

public class UnableToScheduleException extends Exception {
    private List<TimeBlockable> invalidBlocks;
    private int numOfInvalidBlocks;

    private TimeChunk invalidTimeChunk;

    private List<TimeChunk> conflictingChunks;

    public UnableToScheduleException(List<TimeBlockable> invalidBlocks) {
        this.invalidBlocks = invalidBlocks;
        this.numOfInvalidBlocks = invalidBlocks.size();
    }

    public UnableToScheduleException(TimeBlockable invalidBlock) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
    }

    public UnableToScheduleException(List<TimeBlockable> invalidBlocks, TimeChunk invalidTimeChunk) {
        this.invalidBlocks = invalidBlocks;
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.invalidTimeChunk = invalidTimeChunk;
    }

    public UnableToScheduleException(TimeBlockable invalidBlock, TimeChunk invalidTimeChunk) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.invalidTimeChunk = invalidTimeChunk;
    }

    public UnableToScheduleException(List<TimeBlockable> invalidBlocks, List<TimeChunk> conflictingChunks) {
        this.invalidBlocks = invalidBlocks;
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.conflictingChunks = conflictingChunks;
    }

    public UnableToScheduleException(TimeBlockable invalidBlock, List<TimeChunk> conflictingChunks) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.conflictingChunks = conflictingChunks;
    }

    public UnableToScheduleException(TimeBlockable invalidBlock, TimeChunk invalidChunk, List<TimeChunk> conflictingChunks) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.conflictingChunks = conflictingChunks;
        this.invalidTimeChunk = invalidChunk;
    }

    public List<TimeBlockable> getInvalidBlocks() {
        return invalidBlocks;
    }

    public int getNumOfInvalidBlocks() {
        return numOfInvalidBlocks;
    }

    public TimeChunk getInvalidTimeChunk() {
        return invalidTimeChunk;
    }

    public List<TimeChunk> getConflictingChunks() {
        return conflictingChunks;
    }

    public String toString() {
        return "Invalid Event(s): " + invalidBlocks.toString() + "\n" +
               "Found at Times: " + invalidTimeChunk.getStartTime().toString() + " to " + invalidTimeChunk.getEndTime().toString() + "\n" +
               "Conflicts With: " + conflictingChunks.get(0).getStartTime() + " to " + conflictingChunks.get(0).getEndTime() + " and " +
                conflictingChunks.get(1).getStartTime() + " to " + conflictingChunks.get(1).getEndTime();
    }
}

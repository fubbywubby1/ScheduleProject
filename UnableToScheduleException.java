import java.util.ArrayList;
import java.util.List;

/**
 * This is an extension of Exception, it can hold a TimeBlockable that is invalid, along with
 * the corresponding timechunk that it is trying to be placed with in a hashmap.
 * It can also hold the conflicting timechunks that it is arguing with.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class UnableToScheduleException extends Exception {
    private List<TimeBlockable> invalidBlocks;
    private int numOfInvalidBlocks;

    private TimeChunk invalidTimeChunk;

    private List<TimeChunk> conflictingChunks;

    /**
     * Constructor for only the invalid TimeBlockables we have
     * @param invalidBlocks is initialized to our instance var
     */
    public UnableToScheduleException(List<TimeBlockable> invalidBlocks) {
        this.invalidBlocks = invalidBlocks;
        this.numOfInvalidBlocks = invalidBlocks.size();
    }

    /**
     * Constructor for only one invalid TimeBlockable
     * @param invalidBlock is initialized to our instance var
     */
    public UnableToScheduleException(TimeBlockable invalidBlock) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
    }

    /**
     * Constructor for only the invalid TimeBlockables we have and the timechunk in question
     * @param invalidBlocks is initialized to our instance var
     * @param invalidTimeChunk is initialized to our instance var
     */
    public UnableToScheduleException(List<TimeBlockable> invalidBlocks, TimeChunk invalidTimeChunk) {
        this.invalidBlocks = invalidBlocks;
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.invalidTimeChunk = invalidTimeChunk;
    }

    /**
     * Constructor for only the invalidblock and the invalid timechunk
     * @param invalidBlock is initialized to our instance var
     * @param invalidTimeChunk is initialized to our instance var
     */
    public UnableToScheduleException(TimeBlockable invalidBlock, TimeChunk invalidTimeChunk) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.invalidTimeChunk = invalidTimeChunk;
    }

    /**
     * Constructor for if we only have the invalidBlocks and conflictingChunks
     * @param invalidBlocks is initialized to our instance var
     * @param conflictingChunks is initialized to our instance var
     */
    public UnableToScheduleException(List<TimeBlockable> invalidBlocks, List<TimeChunk> conflictingChunks) {
        this.invalidBlocks = invalidBlocks;
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.conflictingChunks = conflictingChunks;
    }

    /**
     * Constructor for if we only have an invalidBlock and conflictingchunks
     * @param invalidBlock is initialized to our instance var
     * @param conflictingChunks is initialized to our instance var
     */
    public UnableToScheduleException(TimeBlockable invalidBlock, List<TimeChunk> conflictingChunks) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.conflictingChunks = conflictingChunks;
    }

    /**
     * Constructor for if we only have the invalidblock, invalidchunk and conflictingchunks
     * @param invalidBlock is initialized to our instance var
     * @param invalidChunk is initialized to our instance var
     * @param conflictingChunks is initialized to our instance var
     */
    public UnableToScheduleException(TimeBlockable invalidBlock, TimeChunk invalidChunk, List<TimeChunk> conflictingChunks) {
        this.invalidBlocks = new ArrayList<>();
        invalidBlocks.add(invalidBlock);
        this.numOfInvalidBlocks = invalidBlocks.size();
        this.conflictingChunks = conflictingChunks;
        this.invalidTimeChunk = invalidChunk;
    }

    /**
     * returns instance var invalidBlocks
     * @return invalidBlocks
     */
    public List<TimeBlockable> getInvalidBlocks() {
        return invalidBlocks;
    }

    /**
     * returns instance var numOfInvalidBlocks
     * @return numOfInvalidBlocks
     */
    public int getNumOfInvalidBlocks() {
        return numOfInvalidBlocks;
    }

    /**
     * returns instance var invalidtimechunk
     * @return invalidtimechunk
     */
    public TimeChunk getInvalidTimeChunk() {
        return invalidTimeChunk;
    }

    /**
     * returns instance var conflictingchunks
     * @return conflictingchunks
     */
    public List<TimeChunk> getConflictingChunks() {
        return conflictingChunks;
    }

    /**
     * Converts all instance variables to Strings
     */
    public String toString() {
        String returnable = "";
        if (invalidBlocks != null) {
            returnable += "Invalid Event(s): ";
            for (TimeBlockable t: invalidBlocks) {
                returnable += t.getName() + "\n";
            }
        }
        if (invalidTimeChunk != null) {
            returnable += "Found at Times: " + invalidTimeChunk.getStartTime().toString() + " to " + invalidTimeChunk.getEndTime().toString() + "\n";
        }
        if (conflictingChunks != null) {
            returnable += "Conflicts With: ";
            for (TimeChunk t: conflictingChunks) {
                returnable += t.getStartTime() + "" + "" + t.getEndTime();
            }
        }
        return returnable;
    }
}

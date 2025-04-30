import java.util.List;

public class UnableToScheduleException extends Exception {
    public List<Event> invalidEvents;
    public int numOfInvalidEvents;

    public UnableToScheduleException(List<Event> invalidEvents) {
        this.invalidEvents = invalidEvents;
        numOfInvalidEvents = invalidEvents.size();
    }
}

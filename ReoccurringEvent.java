import java.util.HashSet;
import java.util.Set;

public class ReoccurringEvent extends Event implements Reoccurring {

    private Set<DaysOfTheWeek> recurringDays; // stores the days this event repeats

    public ReoccurringEvent (String name, String description, Label label, Boolean isTask, Set<DaysOfTheWeek> recurringDays) {
        super(name, description, label, isTask); // calls the parent class (event)
        this.recurringDays = new HashSet<>(recurringDays); // hashset to avoid duplicates?  
    }

    // returns the set of days when the event repeats
    @Override
    public Set<DaysOfTheWeek> getRecurringDays() {
        return new HashSet<>(recurringDays); 
    }

    // replaces the enire reocurrence pattern with a new set of days
    @Override
    public void setRecurringDays(Set<DaysOfTheWeek> days) {
        this.recurringDays = new HashSet<>(days); 
    }

    // checks if this event should happen on a given day of the week
    @Override
    public boolean occursOn(DaysOfTheWeek day) {
        return recurringDays.contains(day);
    }

    // adds a single weekday to the recurrence list
    @Override
    public void addRecurringDay(DaysOfTheWeek day) {
        recurringDays.add(day);
    }

    // removes a single weekday from the recurrence list
    @Override
    public void removeRecurringDay(DaysOfTheWeek day) {
        recurringDays.remove(day);
    }
}

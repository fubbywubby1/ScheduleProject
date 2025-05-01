import java.util.HashSet;
import java.util.Set;

/**
* represents an event that repeats on specific days of the week
* this class extends Event and implements Reoccurring, allowing the event
* to occur on multiple days in a single weekly schedule
* 
* used for things like classes, workouts, or any recurring routine
*
* @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
*/
public class ReoccurringEvent extends Event implements Reoccurring {

    private Set<DaysOfTheWeek> recurringDays; // stores the days this event repeats

    /**
    * creates a new reoccurring event with all relevant fields
    * @param name is the name of the event
    * @param description is the description of the event
    * @param label is the category label (work, self care, etc.)
    * @param isTask is whether this is a task (typically false in current project)
    * @param recurringDays is the set of days this event repeats on
    */
    public ReoccurringEvent (String name, String description, Label label, Boolean isTask, Set<DaysOfTheWeek> recurringDays) {
        super(name, description, label, isTask); 
        this.recurringDays = new HashSet<>(recurringDays); 
    }

    /**
    * returns the set of days when the event repeats
    * @return set of recurring days
    */
    @Override
    public Set<DaysOfTheWeek> getRecurringDays() {
        return new HashSet<>(recurringDays); 
    }

    /**
    * replaces the entire recurrence pattern with a new set of days
    * @param days is the new set of recurring days
    */
    @Override
    public void setRecurringDays(Set<DaysOfTheWeek> days) {
        this.recurringDays = new HashSet<>(days); 
    }

    /**
    * checks if this event should happen on a given day of the week
    * @param day is the day to check
    * @return true if it repeats on that day, false otherwise
    */
    @Override
    public boolean occursOn(DaysOfTheWeek day) {
        return recurringDays.contains(day);
    }

    /**
    * adds a single weekday to the recurrence list
    * @param day is the day to add
    */
    @Override
    public void addRecurringDay(DaysOfTheWeek day) {
        recurringDays.add(day);
    }

    /**
    * removes a single weekday from the recurrence list
    * @param day is the day to remove
    */
    @Override
    public void removeRecurringDay(DaysOfTheWeek day) {
        recurringDays.remove(day);
    }
}

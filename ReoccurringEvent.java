import java.util.HashSet;
import java.util.Set;

public class ReoccurringEvent extends Event implements Reoccurring {

    private Set<DaysOfTheWeek> recurringDays; // stores the days this event repeats

    public ReoccurringEvent (String name, String description, Label label, Boolean isTask, Set<DaysOfTheWeek> recurringDays) {
        super(name, description, label, isTask); // calls the parent class (event)
        this.recurringDays = new HashSet<>(recurringDays); // hashset to avoid duplicates?  
    }
   
}

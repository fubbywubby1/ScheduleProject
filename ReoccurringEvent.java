import java.util.HashSet;
import java.util.Set;

public class ReoccurringEvent extends Event implements Reoccurring {

    private Set<DaysOfTheWeek> recurringDays; // stores the days this event repeats

    public ReoccurringEvent (Set<DaysOfTheWeek> recurringDays) {
        this.recurringDays = new HashSet<>(recurringDays); // hashset to avoid duplicates?  
    }
   
}

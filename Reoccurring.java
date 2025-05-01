import java.util.Set;

/**
* This interface allows implementing classes to specify which days they recur on,
* and provides methods to check, add, or remove recurrence days.
* It is designed to work within a one-week scheduling system.
*
* @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
*/
interface Reoccurring {

    Set<DaysOfTheWeek> getRecurringDays();
    void setRecurringDays(Set<DaysOfTheWeek> days); 
    boolean occursOn(DaysOfTheWeek day); 
    void addRecurringDay(DaysOfTheWeek day);  
    void removeRecurringDay(DaysOfTheWeek day); 

}


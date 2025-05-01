import java.util.Set;

/**
* This interface allows implementing classes to specify which days they recur on,
* and provides methods to check, add, or remove recurrence days.
* It is designed to work within a one-week scheduling system.
*
* @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
*/
interface Reoccurring {

    /**
    * returns the set of days on which an item repeats
    * @return the recurring days as a set
    */
    Set<DaysOfTheWeek> getRecurringDays();

    /**
    * replaces the current recurring days with a new set
    * @param days is the new set of recurring days
    */
    void setRecurringDays(Set<DaysOfTheWeek> days); 

    /**
    * checks whether or not the item is supposed to happen on the given day
    * @param day is the day to check
    * @return true if it occurs on that day, false otherwise
    */
    boolean occursOn(DaysOfTheWeek day); 

    /**
    * adds a single day to the list of recurring days
    * @param day is the day to add
    */
    void addRecurringDay(DaysOfTheWeek day);  

    /**
    * removes a single day from the list of recurring days
    * @param day is the day to remove
    */
    void removeRecurringDay(DaysOfTheWeek day); 

}


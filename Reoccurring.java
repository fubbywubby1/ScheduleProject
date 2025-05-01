import java.util.Set;

interface Reoccurring {

    Set<DaysOfTheWeek> getRecurringDays(); // returns the set of days on which an item repeats
    void setRecurringDays(Set<DaysOfTheWeek> days); // replaces the currect recurring days with a new set
    boolean occursOn(DaysOfTheWeek day); // checks whether or not the item is supposed to happen on the given day
    void addRecurringDay(DaysOfTheWeek day); // adds a single day to the list of recurring days
    void removeRecurringDay(DaysOfTheWeek day); // removes a single day from the list of recurring days

}


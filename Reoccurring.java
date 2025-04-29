import java.time.LocalDate;

interface Reoccurring {
    FrequencyType getFrequencyType(); //DAILY, WEEKLY, MONTHLY, YEARLY
    LocalDate getStartDate(); // returns date recurring event started
    LocalDate getNextOccurence(LocalDate currentDate); // the next day an event 
    boolean isActive(); // returns if the recurring event is currently active

    
}


interface Reoccurring {
    FrequencyType getFrequencyType(); //DAILY, WEEKLY, MONTHLY, YEARLY
}

// enums to define frequency of reoccurrance 
public enum FrequencyType {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}

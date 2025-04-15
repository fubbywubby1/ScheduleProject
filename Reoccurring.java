interface Reoccurring {
    FrequencyType getFrequencyType(); //DAILY, WEEKLY, MONTHLY, YEARLY
}

public enum FrequencyType {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}

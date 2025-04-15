public class ReoccurringTask extends Task implements Reoccuring {
   private FrequencyType frequencyType;

    // other stuff tba

    // getter for frequency type
    @Override
    public FrequencyType getFrequencyType() {
       return this.frequencyType;
    }

    // setter for frequency type
    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    } 
}

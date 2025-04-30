/**
 * An extension of TimeBlockable, except is has a label
 * Though this doesn't have direct functionality, it is extremely 
 * relevant to the functionality of SelfCareScheduler
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
class Event extends TimeBlockable {
    private Label label;
    private Boolean isTask;

    /**
     * Initializes all instance vars
     * @param name is set to name
     * @param description is set to description
     * @param label is set to label
     * @param isTask is set to istask
     */
    public Event(String name, String description, Label label, Boolean isTask) {
        super(name, description);
        this.label = label;
        this.isTask = isTask;
    }

    /**
     * returns label
     * @return label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * returns istask
     * @return istask
     */
    public Boolean getIsTask() {
        return isTask;
    }
}

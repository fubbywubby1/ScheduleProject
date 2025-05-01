import java.io.Serializable;
/**
 * An abstract class that details what can be put in a schedule.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
abstract class TimeBlockable implements Serializable {
    private String name;
    private String description;

    /**
     * Initializes instance vars
     * @param name is set to instance var name
     * @param description is set to instance var description
     */
    public TimeBlockable(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * returns instance var name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * returns instance var description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets name to a new valid name
     * @param name is setting instance var name
     */
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    /**
     * sets description to a new valid description
     * @param description is setting instance var description
     */
    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }


}

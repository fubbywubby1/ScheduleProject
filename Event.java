class Event implements TimeBlockable {
    private String name;
    private String description;
    private int priority;
    private Label label;
    private Boolean isTask;

    public Event(String name, String description, int priority, Label label, Boolean isTask) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.label = label;
        this.isTask = isTask;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Label getLable() {
        return label;
    }

    public Boolean getIsTask() {
        return isTask;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public void setPriority(int priority) {
        if (priority > 0 && priority < 101) {
            this.priority = priority;
        }
    }

    /* 
    public void setStartTime(int startTime) throws Exception {
        if (startTime >= 0 && startTime < 2400) {
            if (TimeHandler.checkTimeConflict(new Event(this.name, this.description, this.priority, startTime, this.endTime)) == false) {
                throw new Exception("Time Conflict");
            } else {
                this.startTime = startTime;
            }
        } else {
            throw new Exception("Invalid Start time");
        }
    }

    public void setEndTime(int endTime) throws Exception{
        if (endTime >= 0 && endTime < 2400) {
            if (TimeHandler.checkTimeConflict(new Event(this.name, this.description, this.priority, this.startTime, endTime)) == false) {
                throw new Exception("Time Conflict");
            } else {
                this.endTime = endTime;
            }
        } else {
            throw new Exception("Invalid end time");
        }
    }
    */
}

class Event extends TimeBlockable {
    private Label label;
    private Boolean isTask;

    public Event(String name, String description, Label label, Boolean isTask) {
        super(name, description);
        this.label = label;
        this.isTask = isTask;
    }

    public Label getLabel() {
        return label;
    }

    public Boolean getIsTask() {
        return isTask;
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

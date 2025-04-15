class Event implements TimeBlockable {
    private String name;
    private String description;
    private int priority;
    private int startTime;
    private int endTime;

    public Event(String name, String description, int priority, int startTime, int endTime) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
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
}

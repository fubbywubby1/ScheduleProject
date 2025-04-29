import java.time.LocalTime;

public class TimeChunk {
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeChunk(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setStartTime(LocalTime startTime) throws Exception{
        if (startTime != null) {
            this.startTime = startTime;
        } else {
            throw new Exception("Invalid time");
        }
    }

    public void setEndTime(LocalTime endTime) throws Exception{
        if (endTime != null) {
            this.endTime = endTime;
        } else {
            throw new Exception("Invalid time");
        }
    }
}

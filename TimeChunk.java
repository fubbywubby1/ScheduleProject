import java.time.LocalTime;
import java.util.Date;

public class TimeChunk {
    private LocalTime startTime;
    private LocalTime endTime;
    private Date date;

    public TimeChunk(LocalTime startTime, LocalTime endTime, Date date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Date getDate() {
        return date;
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

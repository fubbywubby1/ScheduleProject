import java.time.LocalTime;
import java.io.Serializable;

import java.util.logging.Logger;

public class TimeChunk implements Serializable {
    private LocalTime startTime;
    private LocalTime endTime;

    private static final Logger logger = Logger.getLogger("TimeChunk");

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
            logger.warning("Null startTime.");
            throw new Exception("Invalid time");
        }
    }

    public void setEndTime(LocalTime endTime) throws Exception{
        if (endTime != null) {
            this.endTime = endTime;
        } else {
            logger.warning("Null endtime.");
            throw new Exception("Invalid time");
        }
    }
}

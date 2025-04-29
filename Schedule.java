import java.util.HashMap;

public class Schedule {
    public HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> scheduleMap;

    public Schedule(HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public HashMap<TimeChunk, Event> getDaySchedule(DaysOfTheWeek day) throws Exception {
        if (day != null) {
            return scheduleMap.get(day);
        } else {
            throw new Exception("Invalid value for inputted day");
        }
    }
    
}

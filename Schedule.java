import java.util.HashMap;

public class Schedule {
    /*
     * Should scheduleMap be static because it should only have 7 entries?
     * If it isn't, how do we ensure that nobody adds more entries?
     * By being private, nobody else can access it, sure
     * But if I'm inputting a schedule from a file
     */
    public static HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> scheduleMap;
    public static HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> testScheduleMap;

    static {
        scheduleMap
    }

    public Schedule(HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public Schedule(DaysOfTheWeek day, HashMap<TimeChunk, Event> daySchedule) {
        
    }

    public HashMap<TimeChunk, Event> getDaySchedule(DaysOfTheWeek day) throws Exception {
        if (day != null) {
            return scheduleMap.get(day);
        } else {
            throw new Exception("Invalid value for inputted day");
        }
    }
    
}

import java.util.HashMap;

public class Schedule {

    public static HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> scheduleMap = new HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>>();
    private static HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> testScheduleMap;

    static {
        scheduleMap.put(DaysOfTheWeek.Sunday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Monday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Tuesday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Wednesday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Thursday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Friday, new HashMap<>());
        scheduleMap.put(DaysOfTheWeek.Saturday, new HashMap<>());
        testScheduleMap = scheduleMap;
    }

    public static void refreshTestSchedule() {
        testScheduleMap = scheduleMap;
    }

    public static HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> getTestSchedule() {
        Schedule.refreshTestSchedule();
        return testScheduleMap;
    } 
}

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class SelfCareScheduler {
    static Map<Predicate<Double>, List<Event>> stressToEvents = new HashMap<>();

    static {
        stressToEvents.put(
            StressAVG -> StressAVG >= 0 && StressAVG <= 1,
            List.of(new Event("Do a Craft", "Create something new!", Label.LEISURE, false), 
                    new Event("Hit the gym", "It's leg day!", Label.SELFCARE, false),
                    new Event("Organize your space", "...your room is quite messy", Label.SELFCARE, false))
        );
        stressToEvents.put(
            StressAVG -> StressAVG >= 2 && StressAVG <= 3,
            List.of(new Event("Journal", "Write your feelings down", Label.SELFCARE, false), 
                    new Event("Meal with friends", "It's always a good time to hangout.", Label.LEISURE, false),
                    new Event("Do a Craft", "Create something new!", Label.LEISURE, false))
        );
        stressToEvents.put(
            StressAVG -> StressAVG >= 4 && StressAVG <= 5,
            List.of(new Event("Spend time outdoors", "It's beautiful outside!", Label.SELFCARE, false), 
                    new Event("Read", "As long as its not Shakespeare.", Label.LEISURE, false),
                    new Event("Journal", "Write your feelings down", Label.SELFCARE, false),
                    new Event("Go for a walk", "Take your dog too.", Label.SELFCARE, false))
        );
        stressToEvents.put(
            StressAVG -> StressAVG >= 6 && StressAVG <= 7,
            List.of(new Event("Watch a show", "...maybe binge watch an entire series", Label.LEISURE, false), 
                    new Event("Yoga", "It helps with your back pain.", Label.SELFCARE, false),
                    new Event("Journal", "Write your feelings down", Label.SELFCARE, false),
                    new Event("Go for a walk", "Take your dog too.", Label.SELFCARE, false))
        );
        stressToEvents.put(
            StressAVG -> StressAVG >= 8 && StressAVG <= 10,
            List.of(new Event("Spend time outdoors", "It's beautiful outside!", Label.SELFCARE, false), 
                    new Event("Meditate", "Sometimes you just have to breathe.", Label.SELFCARE, false),
                    new Event("Call a Loved One", "...maybe Aunt Jude isn't so evil after all", Label.SELFCARE, false),
                    new Event("Go for a walk", "Take your dog too.", Label.SELFCARE, false),
                    new Event("Rest and Reflect", "Just...think about it", Label.SELFCARE, false))
        );
        }

        public void scheduleSelfCareActivities(HashMap<Label, Integer> labelStress) throws UnableToScheduleException {
            double StressAVG = optimize(labelStress);
            List<Event> activitiesToTest = recommendActivities(StressAVG);
            activitiesToTest = testActivities(activitiesToTest);
            if (activitiesToTest.isEmpty()) {
                System.out.println("Every event was able to be placed in");
            } else {
                throw new UnableToScheduleException(activitiesToTest);
            }
        }

        private List<Event> recommendActivities(double StressAVG) {
            List<Event> recommendedEvents = stressToEvents.entrySet().stream()
                                                                     .filter(entry -> entry.getKey().test(StressAVG))
                                                                     .flatMap(entry -> entry.getValue().stream())
                                                                     .collect(Collectors.toList());
            return recommendedEvents;
        }

        private List<Event> testActivities(List<Event> activitiesToTest) throws UnableToScheduleException{          
            HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> testSchedule = Schedule.getTestSchedule();
            for (int i = 0; i < 6 - activitiesToTest.size(); i++) {
                removeLargestValue(testSchedule);
            }
            for (Event testEvent: activitiesToTest) {
                for (DaysOfTheWeek key: testSchedule.keySet()) {
                    for (int k = 8; k < 22; k++) {
                        TimeChunk testChunk = new TimeChunk(LocalTime.of(k, 0), LocalTime.of(k + 1, 0));
                        if (TimeHandler.checkNoTimeConflict(testChunk, testSchedule.get(key)).isEmpty()) {
                            try {
                            TimeHandler.addToTimeBlock(testChunk, testEvent, testSchedule.get(key));
                            } catch (UnableToScheduleException e) {
                                break;
                            }
                            activitiesToTest.remove(testEvent);
                            break;
                        }
                    }
                    break;
                }
                break;
            }
            return activitiesToTest;
        }

        private void removeLargestValue(HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> testSchedule) {
            testSchedule.entrySet().removeIf(entry -> entry.getValue().size() ==
                                                                      testSchedule.values().stream()
                                                                     .mapToInt(HashMap::size)
                                                                     .max()
                                                                     .getAsInt());
        }

        public double optimize(HashMap<Label, Integer> labelStress) {;
            double stressAVG = labelStress.values()
                                .stream()
                                .mapToInt(Integer::intValue)
                                .average()
                                .getAsDouble();
            return stressAVG;
        }


    }
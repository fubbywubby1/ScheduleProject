import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class holds a static hashmap of events to recommend for a certain stresslevel
 * which is given by the GUI
 * It uses a variety of methods to help calculate and test these events in the user's schedule
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class SelfCareScheduler {
    //This is private and static so it cannot be altered
    private static Map<Predicate<Double>, List<Event>> stressToEvents = new HashMap<>();

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

        /**
         * This is the driver method of the class, it references the other methods
         * @look at all the methods below this.
         * @param labelStress is the total stress of the user per label
         * @throws UnableToScheduleException in case we cannot apply all the events to the user's schedule
         */
        public void scheduleSelfCareActivities(ArrayList<Integer> stressLevels) throws UnableToScheduleException {
            double StressAVG = 0;
            for (int i = 0; i < stressLevels.size(); i++) {
                StressAVG += stressLevels.get(i);
            }
            StressAVG = StressAVG / stressLevels.size();
            List<Event> activitiesToTest = recommendActivities(StressAVG);
            activitiesToTest = testActivities(activitiesToTest);
            if (activitiesToTest.isEmpty()) {
                System.out.println("Every event was able to be placed in");
            } else {
                List<TimeBlockable> activitiesToBlocks = new ArrayList<>();
                activitiesToTest.forEach(a -> activitiesToBlocks.add(a));
                throw new UnableToScheduleException(activitiesToBlocks);
            }
        }

        /**
         * A simple stream that solely tests StressAVG to find the Events to recommend
         * @param StressAVG is used to find what to recommend
         * @return a List<Event> that we use later to test in the user's schedule
         */
        private List<Event> recommendActivities(double StressAVG) {
            List<Event> recommendedEvents = stressToEvents.entrySet().stream()
                                                                     .filter(entry -> entry.getKey().test(StressAVG))
                                                                     .flatMap(entry -> entry.getValue().stream())
                                                                     .collect(Collectors.toList());
            return recommendedEvents;
        }

        /**
         * This method takes in the activities to test, removes the most stressful days,
         * then tests each individual event throughout the given timeblocks in each respective day
         * If there are still events leftover that cannot work, we through an error
         * @param activitiesToTest is the activities we want to test
         * @return a List<Event>, if its empty, everything was successful
         * @throws UnableToScheduleException if we cannot apply all events to the user's schedule
         */
        private List<Event> testActivities(List<Event> activitiesToTest) throws UnableToScheduleException{          
            HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> testSchedule = Schedule.getTestSchedule();
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
                            Schedule.add(testChunk, testEvent, key);
                            System.out.println("Event was added successfully");
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

        /**
         * Using a stream, this removes the largest value of a HashMap
         * @param testSchedule is what we are removing the largest value of
         */
        private void removeLargestValue(HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> testSchedule) {
            testSchedule.entrySet().removeIf(entry -> entry.getValue().size() ==
                                                                      testSchedule.values().stream()
                                                                     .mapToInt(HashMap::size)
                                                                     .max()
                                                                     .getAsInt());
        }


    }
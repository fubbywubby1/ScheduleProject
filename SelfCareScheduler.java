import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
public class SelfCareScheduler {
    Double StressAVG = Optimizer.optimize();
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
        public List<Event> recommendActivities() {
            List<Event> recommendedEvents = stressToEvents.entrySet().stream()
            .filter(entry -> entry.getKey().test(StressAVG))
            .flatMap(entry -> entry.getValue().stream())
            .collect(Collectors.toList());
            return recommendedEvents;
        }
    }
    /*  this handles all of the self care processes. takes in stress
    info from the RateStressPage.
     ideally, when we create the actual schedule,
     we would somehow evaluate the stress level of the user.
     probably by asking them a series of questions about event Labels WORK,
     SCHOOL, STUDY, CLUB, CHORES - how would you rate your stress level on a
     scale of 1-10? You take that average and then use that to determine
     how many self care activities you need to schedule. It creates a random schedule full
     of self-care activities, and compares that to the user's schedule. Those that overlap 
     are removed from the self-care schedule. If the amount of self-care activites is not reached,
     it tries again, and again, until that number is reached and nothing is overlapping.
     The user, when seeing their new schedule, should have the option to delete self-care tasks 
     (as they would be able to delete any other Events if they so choose) and also an
     option to re-generate the self-care schedule
    1. We need to create a list of self care activities
    2. We need to create a random schedule of self-care activities
    3. We need to compare the self-care schedule to the user's schedule
    4. We need to remove any overlapping self-care activities
    5. We need to check if the number of self-care activities is reached
    6. If not, we need to try again
    7. If so, we need to return the self-care schedule
    8. We need to give the user the option to manually re-generate the self-care schedule.


    AVERAGE RATINGS AND THEIR SELF CARE EVENTS
    0-1: Do a craft (2 hours), Hit the gym (2 hours), Organize your space (1 hour)
    2-3: Journal (1 hour), Meal with friends (2 hours), Do a craft (2 hours)
    4-5: Spend time outdoors (1 hour), Read (1 hour), Journal (1 hour), Go for a walk (1 hour)
    6-7: Watch a show (1 hour), Yoga (1 hour), Journal (1 hour), Go for a walk (1 hour)
    8-10: Meditate (1 hour), Call a loved one (1 hour), Go for a walk (1 hour), Rest and Reflect (1 hour), Spend time outdoors (1 hour)
    */

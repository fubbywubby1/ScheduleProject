public class SelfCareScheduler {
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
    */
    

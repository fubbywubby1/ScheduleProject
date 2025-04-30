import javafx.stage.Stage;

public class ScheduleGUI {
    // we start with the Start Page
    // if a user has a valid schedule, we can go to the Schedule Page
    // if not, they make a new page!
    // this will be built once the functionality to make the schedule has been implemented

    public static void main(String[] args) {
        // Launch the application
        StartPage startPage = new StartPage();
        startPage.start(new Stage());
    }

    public void startSchedulePage(Stage primaryStage) {
        SchedulePage schedulePage = new SchedulePage();
        schedulePage.start(primaryStage);
    }

    public void startFindExistingSchedulePage(Stage primaryStage) {
        FindExistingSchedulePage findExistingSchedulePage = new FindExistingSchedulePage();
        findExistingSchedulePage.start(primaryStage);
    }

    public void startCreateSchedulePage(Stage primaryStage) {
        CreateSchedulePage createSchedulePage = new CreateSchedulePage();
        createSchedulePage.start(primaryStage);
    }

    public void startRateStressPage(Stage primaryStage) {
        RateStressPage rateStressPage = new RateStressPage();
        rateStressPage.start(primaryStage);
    }


}

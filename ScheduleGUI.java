import javafx.application.Application;
import javafx.stage.Stage;
/**
 * This is the ScheduleGUI class.
 * It is the main class for the application.
 * It launches the application and creates the GUIs for the user to interact with.
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class ScheduleGUI extends Application{
    /**
     * This is the main method.
     * It launches the application and creates the StartPage.
     * @param args
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Start the application with the StartPage
        StartPage startPage = new StartPage();
        startPage.start(primaryStage);
    }

    /**
     * This method starts the SchedulePage.
     * @param primaryStage
     */
    public static void startSchedulePage(Stage primaryStage) {
        SchedulePage schedulePage = new SchedulePage();
        schedulePage.start(primaryStage);
    }

    /**
     * This method starts the FindExistingSchedulePage.
     * @param primaryStage
     */
    public static void startFindExistingSchedulePage(Stage primaryStage) {
        FindExistingSchedulePage findExistingSchedulePage = new FindExistingSchedulePage();
        findExistingSchedulePage.start(primaryStage);
    }

    /**
     * This method starts the CreateSchedulePage.
     * @param primaryStage
     */
    public static void startCreateSchedulePage(Stage primaryStage) {
        CreateSchedulePage createSchedulePage = new CreateSchedulePage();
        createSchedulePage.start(primaryStage);
    }

    /**
     * This method starts the RateStressPage.
     * @param primaryStage
     */
    public static void startRateStressPage(Stage primaryStage) {
        RateStressPage rateStressPage = new RateStressPage();
        rateStressPage.start(primaryStage);
    }


}

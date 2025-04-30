import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This is the FindExistingSchedulePage class.
 * It generates the GUI for the user to choose an existing schedule.
 * When the user selects a schedule, it will pass the user to the SchedulePage.
 * If the user does not see their desired schedule, they can create a new one.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class FindExistingSchedulePage extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Choose Existing Schedule");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Button newScheduleButton = new Button("Create New Schedule");
        newScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        newScheduleButton.setOnAction(e -> {
             ScheduleGUI.startCreateSchedulePage(primaryStage);
        });
        


    }

    public static void main(String[] args) {
        launch(args);
    }
}

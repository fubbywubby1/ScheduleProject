import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * StartPage.java
 * This creates the start page for the application, which
 * will then lead to either an existing schedule or the necessary screens to build a new schedule.
 * @WIP
 * @author Emily Schwartz
 */
public class StartPage extends Application {
    boolean existingSchedule;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to Your Schedule!");
        Button newScheduleButton = new Button("Create New Schedule");
        newScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        newScheduleButton.setOnAction(e -> {
             ScheduleGUI.startCreateSchedulePage(primaryStage);
        });
        Button loadScheduleButton = new Button("Load Existing Schedule");
        loadScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        loadScheduleButton.setOnAction(e -> {
            ScheduleGUI.startFindExistingSchedulePage(primaryStage);
        });
       VBox layout = new VBox(10);
        layout.getChildren().addAll(newScheduleButton, loadScheduleButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
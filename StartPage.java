import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.logging.Logger;

/*
 * StartPage.java
 * This creates the start page for the application, which
 * will then lead to either an existing schedule or the necessary screens to build a new schedule.
 * 
 * @author Emily Schwartz
 */
public class StartPage extends Application {
    boolean existingSchedule;

    private static final Logger logger = Logger.getLogger("StartPage");

    @Override
    /**
     * This method creates the start page for the application.
     * It contains two buttons: one to create a new schedule and one to load an existing schedule.
     * @param primaryStage
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome to the Self-Care Scheduler!");
        // Create buttons
        Button newScheduleButton = new Button("🌸 Create New Schedule");
        Button loadScheduleButton = new Button("📂 Load Existing Schedule");

        // Apply styles to buttons
        String buttonStyle = """
            -fx-background-color: linear-gradient(to right, #fbb1bd, #fbc2eb);
            -fx-text-fill: #5c5c5c;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 10px 20px;
            -fx-background-radius: 20;
            -fx-border-radius: 20;
            """;

        newScheduleButton.setStyle(buttonStyle);
        loadScheduleButton.setStyle(buttonStyle);

        // Button actions
        newScheduleButton.setOnAction(e -> {
            ScheduleGUI.startCreateSchedulePage(primaryStage);
        });

        loadScheduleButton.setOnAction(e -> {
            ScheduleGUI.startFindExistingSchedulePage(primaryStage);
        });

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(newScheduleButton, loadScheduleButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #ffe4ec; -fx-padding: 40px;");

        // Scene
        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        logger.info("Succssfully started startpage.");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
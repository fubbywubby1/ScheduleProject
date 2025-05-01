import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Set;

import java.util.logging.Logger;

public class FindExistingSchedulePage extends Application {

    private static final Logger logger = Logger.getLogger("FindExistingSchedulePage");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Choose Existing Schedule");

        // Initialize files from disk
        FileReader.initializeFileList();
        Set<String> savedSchedules = FileReader.getSavedScheduleNames();

        Label title = new Label("Select an Existing Schedule");
        title.setStyle("-fx-font-size: 18px; -fx-text-fill: #444;");

        ComboBox<String> scheduleComboBox = new ComboBox<>();
        scheduleComboBox.getItems().addAll(savedSchedules);
        scheduleComboBox.setPromptText("Choose your schedule");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");
        logger.info("Created scheduleComboBox and Label");

        Button loadButton = new Button("Load Schedule");
        loadButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #f4b6c2; -fx-text-fill: white; -fx-background-radius: 8;");
        loadButton.setOnAction(e -> {
            String selectedName = scheduleComboBox.getValue();
            if (selectedName == null) {
                messageLabel.setText("Please select a schedule.");
                return;
            }

            try {
                boolean success = FileReader.load(selectedName);
                if (success) {
                    Schedule.setName(selectedName);
                    ScheduleGUI.startSchedulePage(primaryStage);
                    logger.info("Loaded schedule.");
                } else {
                    logger.warning("Couldn't load schedule.");
                    messageLabel.setText("Failed to load schedule.");
                }
            } catch (IOException ex) {
                logger.warning("Could not load schedule.");
                messageLabel.setText("Error loading schedule: " + ex.getMessage());
            }
        });

        Button newScheduleButton = new Button("Create New Schedule");
        newScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #eec3dc; -fx-text-fill: #333; -fx-background-radius: 8;");
        newScheduleButton.setOnAction(e -> ScheduleGUI.startCreateSchedulePage(primaryStage));

        VBox layout = new VBox(15, title, scheduleComboBox, loadButton, messageLabel, newScheduleButton);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color: #ffe6f0;");
        Scene scene = new Scene(layout, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

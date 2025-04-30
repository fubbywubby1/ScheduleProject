import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalTime;
import java.util.HashMap;

public class CreateSchedulePage extends Application {
    // stuff go here once we have more functionality done
    // should we create the empty schedule at the beginning?
    public void start(Stage primaryStage)
    {
        String scheduleName;
        String eventName;
        String day;
        String startTime;
        String endTime;
        Label label;
        Event event;
        boolean firstRun = true;

        primaryStage.setTitle("Create a New Schedule");
        TextField nameTextBox = new TextField("Enter Schedule Name");
        TextField eventTextBox = new TextField("Enter Event Name");
        
        ComboBox<String> dayComboBox = new ComboBox<>();
        dayComboBox.getItems().addAll(DaysOfTheWeek.values());
        dayComboBox.setPromptText("Choose Day");

        TextField hourTextBoxA = new TextField("Enter Start Hour");
        TextField minuteTextBoxA = new TextField("Enter Start Minute");
        TextField hourTextBoxB = new TextField("Enter End Hour");
        TextField minuteTextBoxB = new TextField("Enter End Minute");

        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.getItems().addAll(Label.values());
        labelComboBox.setPromptText("Choose Label");

        Button addEventButton = new Button("Add Event");
        addEventButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        addEventButton.setOnAction(e -> {
            if (firstRun) {
                // Check if the schedule name is empty
                if (nameTextBox.getText().isEmpty()) {
                    scheduleName = "Default Schedule";
                    return;
                }
                scheduleName = nameTextBox.getText();
                // create a new schedule object with this name
                Schedule schedule = new Schedule(scheduleName);
                firstRun = false;
                nameTextBox.setDisable(true);
            }
            

            eventName = eventTextBox.getText();
            day = dayComboBox.getValue();
            startTime = hourTextBoxA.getText() + ":" + minuteTextBoxA.getText();
            endTime = hourTextBoxB.getText() + ":" + minuteTextBoxB.getText();
            Label label = labelComboBox.getValue();

            // Create LocalTimes
            LocalTime startTime = LocalTime.parse(startTime);
            LocalTime endTime = LocalTime.parse(endTime);
            // Create a new event object
            event = new Event(eventName, startTime, endTime, label);

            // WRITE IN: passing it to the hashmap
            TimeHandler.addToTimeBlock(new TimeChunk(startTime, EndTime), event, Schedule.scheduleMap.get(day));

            // Clear the text fields
            nameTextBox.clear();
            eventTextBox.clear();
            hourTextBoxA.clear();
            minuteTextBoxA.clear();
            hourTextBoxB.clear();
            minuteTextBoxB.clear();
            
        });

        Button rateStressButton = new Button("Continue to Rate Stress");
        rateStressButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        rateStressButton.setOnAction(e -> {
            ScheduleGUI.startRateStressPage(primaryStage);
        });
    }
}
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
        private static String scheduleName;
        private String eventName;
        private String day;
        private String startTime;
        private String endTime;
        private Label label;
        private Event event;
        private LocalTime startTime;
        private LocalTime endTime;
        private boolean firstRun = true;
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
            // maybe add a for each: for every aspect, if thing is null, spitout error)
            scheduleName = nameTextBox.getText();
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

            //disable schedule name text box
            firstRun = false;
            if(!firstRun) {
                nameTextBox.setDisable(true);
            }

            // Show success message
            label.setText("Event added successfully!");
        });

        Button rateStressButton = new Button("Continue to Rate Stress");
        rateStressButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        rateStressButton.setOnAction(e -> {
            ScheduleGUI.startRateStressPage(primaryStage);
        });
    }
    // textboxOne: name of schedule
    // textbox: name of event
    // dropdown: choose day
    // dropdown: choose label
    // radio buttons: Task? Timed Tasks?
    // textbox a and b: start time
    // textbox c and d: end time
    // button: add event - on first click, create a new schedule
    // and its hashmap, named the name, and add the event to it.

    // if it takes in a non time task, input the designated "null" time
    // into the constructor.
}
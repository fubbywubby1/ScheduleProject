import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Arrays;

public class CreateSchedulePage extends Application {
    // stuff go here once we have more functionality done
    // should we create the empty schedule at the beginning?
    @Override
    public void start(Stage primaryStage)
    {
        String scheduleName;
        String eventName;
        String eventDescription;
        String day;
        String startTime;
        String endTime;

        private static final String[] LABELS = Arrays.stream(Label.values())
                                               .map(Enum::name)
                                               .toArray(String[]::new);

        String label;
        Event event;
        boolean firstRun = true;

        primaryStage.setTitle("Create a New Schedule");
        TextField nameTextBox = new TextField("Enter Schedule Name");
        TextField eventTextBox = new TextField("Enter Event Name");
        TextField eventDescriptionTextBox = new TextField("Enter Event Description");
        
        ComboBox<String> dayComboBox = new ComboBox<>();
        // for each DaysOfTheWeek enum, cast as a string and put it into an array
        for (DaysOfTheWeek d : DaysOfTheWeek.values()) {
            dayComboBox.getItems().add(day.toString());
        }
        dayComboBox.setPromptText("Choose Day");

        TextField hourTextBoxA = new TextField(" (24 hour format) Enter Start Hour");
        TextField minuteTextBoxA = new TextField("Enter Start Minute");
        TextField hourTextBoxB = new TextField("Enter End Hour");
        TextField minuteTextBoxB = new TextField("Enter End Minute");

        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.getItems().addAll(
            Arrays.stream(Label.values()).map(Label::name).toList()
        );
        labelComboBox.setPromptText("Choose Label");

        // Text area to output error messages
        TextArea errorTextArea = new TextArea();

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
                Schedule.setName(scheduleName);
                firstRun = false;
                nameTextBox.setDisable(true);
            }
            

            eventName = eventTextBox.getText();
            day = dayComboBox.getValue();
            startTime = hourTextBoxA.getText() + ":" + minuteTextBoxA.getText();
            endTime = hourTextBoxB.getText() + ":" + minuteTextBoxB.getText();
            label = labelComboBox.getValue();

            // relabel the label
            Label enumLabel = new Label();
            for (Label l : Label.values())
            {
            if (label.equals(// what do i even put here))
            {
                enumLabel = l;
                break;
            }
            }

            // Create LocalTimes
            LocalTime localStartTime = LocalTime.parse(startTime);
            LocalTime localEndTime = LocalTime.parse(endTime);

            TimeChunk eventChunk = new TimeChunk(localStartTime, localEndTime);
            // Create a new event object
            event = new Event(eventName, eventDescription, enumLabel, false);

            // passing it to the hashmap, and if it fails, catch the exception and print it
            try {
                TimeHandler.addToTimeBlock(eventChunk, event, Schedule.scheduleMap.get(day));
            } catch (UnableToScheduleException e) {
                errorTextArea(e.toString());
            }

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
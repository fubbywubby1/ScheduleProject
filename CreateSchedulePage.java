import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the CreateSchedulePage class.
 * It generates the GUI for the user to input their own schedule, and it passes
 * that information to the Schedule class, which populates their schedule.
 * When its done, it will pass the user to the RateStressPage.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class CreateSchedulePage extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        String scheduleName;
        String eventName;
        String eventDescription;
        String day;
        String startTime;
        String endTime;
        ArrayList<String> labelStrings = new ArrayList<>();
        int numLabels = Label.values().length;

        for (Label l : Label.values())
        {
            labelStrings.add(l.getName());
        }
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
            labelStrings.toArray(new String[numLabels])
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
            Label enumLabel;
            for (String l : labelStrings)
            {
                for (Label labelEnum : Label.values())
                {
                    if (labelEnum.getName().equals(l))
                    {
                        enumLabel = labelEnum;
                        break;
                    }
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
                errorTextArea.appendText(e.toString() + "\n");
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
        rateStressButton.setOnAction(a -> {
            ScheduleGUI.startRateStressPage(primaryStage);
        });
    }
}
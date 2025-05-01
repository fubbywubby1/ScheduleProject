import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This is the CreateSchedulePage class.
 * It generates the GUI for the user to input their own schedule, and it passes
 * that information to the Schedule class, which populates their schedule.
 * When it's done, it will pass the user to the RateStressPage.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class CreateSchedulePage extends Application {
    private String scheduleName = null; 
    private boolean firstRun;


    @Override
    public void start(Stage primaryStage) {
        firstRun = true;

        primaryStage.setTitle("Create a New Schedule");

        TextField nameTextBox = new TextField("Enter Schedule Name");
        TextField eventTextBox = new TextField("Enter Event Name");
        TextField eventDescriptionTextBox = new TextField("Enter Event Description");

        ComboBox<String> dayComboBox = new ComboBox<>();
        for (DaysOfTheWeek d : DaysOfTheWeek.values()) {
            dayComboBox.getItems().add(d.name());
        }
        dayComboBox.setPromptText("Choose Day");

        TextField hourTextBoxA = new TextField("Start Hour (24hr)");
        TextField minuteTextBoxA = new TextField("Start Minute");
        TextField hourTextBoxB = new TextField("End Hour (24hr)");
        TextField minuteTextBoxB = new TextField("End Minute");

        ArrayList<String> labelStrings = new ArrayList<>();
        for (Label l : Label.values()) {
            labelStrings.add(l.getName());
        }

        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.getItems().addAll(labelStrings);
        labelComboBox.setPromptText("Choose Label");

        TextArea errorTextArea = new TextArea();
        errorTextArea.setEditable(false);
        errorTextArea.setWrapText(true);

        Button addEventButton = new Button("Add Event");
        addEventButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        addEventButton.setOnAction(e -> {
            if (firstRun) {
                if (nameTextBox.getText().isEmpty()) {
                    scheduleName = "Default Schedule";
                } else {
                    scheduleName = nameTextBox.getText();
                }
                Schedule.setName(scheduleName);
                firstRun = false;
                nameTextBox.setDisable(true);
            }

            String eventName = eventTextBox.getText();
            String eventDescription = eventDescriptionTextBox.getText();
            String day = dayComboBox.getValue();
            String labelName = labelComboBox.getValue();

            if (day == null || labelName == null || eventName.isEmpty()) {
                errorTextArea.appendText("Please fill all required fields.\n");
                return;
            }

            DaysOfTheWeek enumDay;
            try {
                enumDay = DaysOfTheWeek.valueOf(day);
            } catch (IllegalArgumentException ex) {
                errorTextArea.appendText("Invalid day selected.\n");
                return;
            }

            Label enumLabel = null;
            for (Label l : Label.values()) {
                if (l.getName().equals(labelName)) {
                    enumLabel = l;
                    break;
                }
            }
            if (enumLabel == null) {
                errorTextArea.appendText("Invalid label selected.\n");
                return;
            }

            try {
                LocalTime localStartTime = LocalTime.parse(hourTextBoxA.getText() + ":" + minuteTextBoxA.getText());
                LocalTime localEndTime = LocalTime.parse(hourTextBoxB.getText() + ":" + minuteTextBoxB.getText());

                TimeChunk eventChunk = new TimeChunk(localStartTime, localEndTime);
                Event event = new Event(eventName, eventDescription, enumLabel, false);

                TimeHandler.addToTimeBlock(eventChunk, event, Schedule.scheduleMap.get(enumDay));
            } catch (UnableToScheduleException ex) {
                errorTextArea.appendText("Scheduling error: " + ex.getMessage() + "\n");
            } catch (Exception ex) {
                errorTextArea.appendText("Error: " + ex.getMessage() + "\n");
            }

            // Clear fields for next event (except schedule name)
            eventTextBox.clear();
            eventDescriptionTextBox.clear();
            dayComboBox.getSelectionModel().clearSelection();
            labelComboBox.getSelectionModel().clearSelection();
            hourTextBoxA.clear();
            minuteTextBoxA.clear();
            hourTextBoxB.clear();
            minuteTextBoxB.clear();
        });

        Button rateStressButton = new Button("Continue to Rate Stress");
        rateStressButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        rateStressButton.setOnAction(a -> ScheduleGUI.startRateStressPage(primaryStage));

        VBox layout = new VBox(10, nameTextBox, eventTextBox, eventDescriptionTextBox, dayComboBox,
                hourTextBoxA, minuteTextBoxA, hourTextBoxB, minuteTextBoxB,
                labelComboBox, addEventButton, rateStressButton, errorTextArea);
        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 450, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
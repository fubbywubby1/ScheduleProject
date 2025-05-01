import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the CreateSchedulePage class.
 * It generates the GUI for the user to input their own schedule, including recurring events.
 * When complete, it sends the user to the RateStressPage.
 * 
 * @author Team
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

        // One-time Day selection (for non-recurring)
        ComboBox<String> dayComboBox = new ComboBox<>();
        for (DaysOfTheWeek d : DaysOfTheWeek.values()) {
            dayComboBox.getItems().add(d.name());
        }
        dayComboBox.setPromptText("Choose Day");

        // Recurring toggle + checkboxes
        CheckBox isRecurringCheckBox = new CheckBox("Is this a recurring event?");
        VBox dayCheckBoxes = new VBox();
        HashMap<DaysOfTheWeek, CheckBox> dayCheckBoxMap = new HashMap<>();
        for (DaysOfTheWeek d : DaysOfTheWeek.values()) {
            CheckBox cb = new CheckBox(d.name());
            dayCheckBoxMap.put(d, cb);
            dayCheckBoxes.getChildren().add(cb);
        }
        dayCheckBoxes.setDisable(true);

        isRecurringCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            dayCheckBoxes.setDisable(!newVal);
            dayComboBox.setDisable(newVal);
        });

        // Time inputs
        TextField hourTextBoxA = new TextField("Start Hour (24hr)");
        TextField minuteTextBoxA = new TextField("Start Minute");
        TextField hourTextBoxB = new TextField("End Hour (24hr)");
        TextField minuteTextBoxB = new TextField("End Minute");

        // Label dropdown
        ArrayList<String> labelStrings = new ArrayList<>();
        for (Label l : Label.values()) {
            labelStrings.add(l.getName());
        }
        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.getItems().addAll(labelStrings);
        labelComboBox.setPromptText("Choose Label");

        // Error messages
        TextArea errorTextArea = new TextArea();
        errorTextArea.setEditable(false);
        errorTextArea.setWrapText(true);

        // Add Event Button
        Button addEventButton = new Button("Add Event");
        addEventButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        addEventButton.setOnAction(e -> {
            if (firstRun) {
                scheduleName = nameTextBox.getText().isEmpty() ? "Default Schedule" : nameTextBox.getText();
                Schedule.setName(scheduleName);
                nameTextBox.setDisable(true);
                firstRun = false;
            }

            String eventName = eventTextBox.getText();
            String eventDescription = eventDescriptionTextBox.getText();
            String labelName = labelComboBox.getValue();

            if (eventName.isEmpty() || labelName == null ||
                (!isRecurringCheckBox.isSelected() && dayComboBox.getValue() == null)) {
                errorTextArea.appendText("Please fill all required fields.\n");
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
                LocalTime localStartTime;
                LocalTime localEndTime;
                if (hourTextBoxA.getText().length() == 1) {
                    if (minuteTextBoxA.getText().length() == 1) {
                        localStartTime = LocalTime.parse("0" + hourTextBoxA.getText() + ":" + "0" + minuteTextBoxA.getText());
                    } else {
                        localStartTime = LocalTime.parse("0" + hourTextBoxA.getText() + ":" + minuteTextBoxA.getText());
                    }
                } else {
                    localStartTime = LocalTime.parse((hourTextBoxA.getText()) + ":" + minuteTextBoxA.getText());
                }
                if (hourTextBoxB.getText().length() == 1) {
                    if (minuteTextBoxB.getText().length() == 1) {
                        localEndTime = LocalTime.parse("0" + hourTextBoxB.getText() + ":" + "0" + minuteTextBoxB.getText());
                    } else {
                        localEndTime = LocalTime.parse("0" + hourTextBoxB.getText() + ":" + minuteTextBoxB.getText());
                    }
                } else {
                    localEndTime = LocalTime.parse((hourTextBoxB.getText()) + ":" + minuteTextBoxB.getText());
                }
                TimeChunk eventChunk = new TimeChunk(localStartTime, localEndTime);

                if (isRecurringCheckBox.isSelected()) {
                    Set<DaysOfTheWeek> selectedDays = new HashSet<>();
                    for (DaysOfTheWeek d : dayCheckBoxMap.keySet()) {
                        if (dayCheckBoxMap.get(d).isSelected()) {
                            selectedDays.add(d);
                        }
                    }

                    if (selectedDays.isEmpty()) {
                        errorTextArea.appendText("Select at least one day for recurring event.\n");
                        return;
                    }

                    ReoccurringEvent recurringEvent = new ReoccurringEvent(eventName, eventDescription, enumLabel, false, selectedDays);
                    for (DaysOfTheWeek d : selectedDays) {
                        try {
                            TimeHandler.addToTimeBlock(eventChunk, recurringEvent, Schedule.scheduleMap.get(d));
                        } catch (UnableToScheduleException ex) {
                            errorTextArea.appendText("Error on " + d.name() + ": " + ex.getMessage() + "\n");
                        }
                    }
                } else {
                    DaysOfTheWeek enumDay = DaysOfTheWeek.valueOf(dayComboBox.getValue());
                    Event event = new Event(eventName, eventDescription, enumLabel, false);
                    TimeHandler.addToTimeBlock(eventChunk, event, Schedule.scheduleMap.get(enumDay));
                }
            } catch (UnableToScheduleException ex) {
                errorTextArea.appendText("Scheduling error: " + ex.toString() + "\n");
            } catch (Exception ex) {
                errorTextArea.appendText("Error: " + ex.getMessage() + "\n");
            }

            // Clear all but name
            eventTextBox.clear();
            eventDescriptionTextBox.clear();
            dayComboBox.getSelectionModel().clearSelection();
            labelComboBox.getSelectionModel().clearSelection();
            hourTextBoxA.clear();
            minuteTextBoxA.clear();
            hourTextBoxB.clear();
            minuteTextBoxB.clear();
            for (CheckBox cb : dayCheckBoxMap.values()) cb.setSelected(false);
        });

        // Navigation
        Button rateStressButton = new Button("Continue to Rate Stress");
        rateStressButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        rateStressButton.setOnAction(a -> ScheduleGUI.startRateStressPage(primaryStage));

        // Layout
        VBox layout = new VBox(10,
            nameTextBox, eventTextBox, eventDescriptionTextBox,
            isRecurringCheckBox, dayComboBox, dayCheckBoxes,
            hourTextBoxA, minuteTextBoxA, hourTextBoxB, minuteTextBoxB,
            labelComboBox, addEventButton, rateStressButton, errorTextArea
        );
        layout.setStyle("-fx-padding: 20; -fx-background-color: #ffe6f0;");

        Scene scene = new Scene(layout, 500, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

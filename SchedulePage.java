import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.HashMap;
/*
 * SchedulePage.java
 * This class creates a schedule page for a week.
 * It displays the days of the week and the TimeChunks within each day.
 * 
 * 
 * @author Emily Schwartz
 */
public class SchedulePage extends Application {
    private static final String[] DAYS = Arrays.stream(DaysOfTheWeek.values())
                                               .map(Enum::name)
                                               .toArray(String[]::new);

    private static final int START_HOUR = 0;
    private static final int END_HOUR = 24;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Your Schedule");

        // Create the main GridPane for the calendar layout
        GridPane calendarGrid = new GridPane();
        calendarGrid.setGridLinesVisible(true);
        calendarGrid.setPadding(new Insets(20));  // Adds padding around the grid
        calendarGrid.setHgap(10);  // Horizontal gap between cells
        calendarGrid.setVgap(10);  // Vertical gap between cells

        // Header row: Days of the week
        for (int i = 0; i < DAYS.length; i++) {
            Label dayLabel = new Label(DAYS[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 10px; -fx-background-color: #F6B2D6; -fx-alignment: center;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            calendarGrid.add(dayLabel, i + 1, 0); // +1 because column 0 is time
        }

        // Time column and empty cells
        for (int hour = START_HOUR; hour <= END_HOUR; hour++) {
            Label timeLabel = new Label(String.format("%02d:00", hour));
            timeLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #D9E9D2; -fx-alignment: center;");
            calendarGrid.add(timeLabel, 0, hour - START_HOUR + 1);  // +1 for header row

            for (int day = 0; day < DAYS.length; day++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(150, 70); // Slightly larger cells for better event visibility
                cell.setStyle("-fx-background-color: #E4F4E0; -fx-border-color: #B8D8B1; -fx-border-width: 1px;");
                calendarGrid.add(cell, day + 1, hour - START_HOUR + 1);
            }
        }

        // Populate cells with events from Schedule class
        HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> schedule = Schedule.scheduleMap;

        // Loop through scheduleMap to place events in the grid
        for (DaysOfTheWeek day : schedule.keySet()) {
            HashMap<TimeChunk, TimeBlockable> events = schedule.get(day);
            for (TimeChunk timeChunk : events.keySet()) {
                TimeBlockable timeBlock = events.get(timeChunk);
                int startHour = timeChunk.getStartTime().getHour();
                int startMinute = timeChunk.getStartTime().getMinute();
                int endHour = timeChunk.getEndTime().getHour();
                int endMinute = timeChunk.getEndTime().getMinute();

                int startRow = startHour - START_HOUR + 1; // Adjust row index for start hour
                int endRow = endHour - START_HOUR + 1;  // Adjust row index for end hour

                if (startMinute > 0) {
                    startRow++; // Adjust if the event starts in the middle of an hour
                }

                if (endMinute > 0) {
                    endRow++; // Adjust if the event ends in the middle of an hour
                }

                int dayIndex = day.ordinal() + 1; // +1 because column 0 is time

                // Place the event in the appropriate cells
                for (int row = startRow; row <= endRow; row++) {
                    StackPane cell = new StackPane();
                    // Set background color based on the event's label
                    if (timeBlock instanceof Event) {
                        Event event = (Event) timeBlock;
                        if (event.getLabel() != null) {
                            // Set background color directly on StackPane
                            String eventColor = event.getLabel().getColor();
                            cell.setStyle("-fx-background-color: " + eventColor + "; -fx-border-color: #B8D8B1; -fx-border-width: 1px;");
                        }
                    } else {
                        cell.setStyle("-fx-background-color: #A0A2A1; -fx-border-color: #B8D8B1; -fx-border-width: 1px;");
                    }

                    // Create a label to be placed on top of the cell
                    Label eventLabel = new Label(timeBlock.getName());
                    eventLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333;");
                    eventLabel.setPadding(new Insets(5));
                    StackPane.setAlignment(eventLabel, Pos.CENTER); // Align label to the center of the cell

                    cell.getChildren().add(eventLabel);

                    // Add the cell to the grid
                    calendarGrid.add(cell, dayIndex, row);
                }
            }
        }

        // Scrollable view for the calendar grid
        ScrollPane scrollPane = new ScrollPane(calendarGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Set the scene and stage
        Scene scene = new Scene(scrollPane, 1000, 700);  // Increased width for better readability
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
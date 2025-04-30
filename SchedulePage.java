import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDate;
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
    /* Builds the Schedule screen. 
     * @param primaryStage The primary stage for this application
     * @param date The date for the schedule
     */
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Your Schedule");
        GridPane calendarGrid = new GridPane();
        calendarGrid.setGridLinesVisible(true);
        calendarGrid.setPadding(new Insets(10));
        calendarGrid.setHgap(1);
        calendarGrid.setVgap(1);

        // Header row: Days of the week
        for (int i = 0; i < DAYS.length; i++) {
            Label dayLabel = new Label(DAYS[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-padding: 5px;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setStyle("-fx-background-color:rgb(229, 164, 202); -fx-font-weight: bold; -fx-alignment: center;");
            calendarGrid.add(dayLabel, i + 1, 0); // +1 because column 0 is time
        }

        // Time column and empty cells
        for (int hour = START_HOUR; hour <= END_HOUR; hour++) {
            Label timeLabel = new Label(String.format("%02d:00", hour));
            timeLabel.setPadding(new Insets(5));
            timeLabel.setStyle("-fx-background-color:rgb(196, 230, 199); -fx-alignment: center;");
            calendarGrid.add(timeLabel, 0, hour - START_HOUR + 1);

            for (int day = 0; day < DAYS.length; day++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(100, 50);
                cell.setStyle("-fx-background-color:rgb(200, 222, 205);");
                calendarGrid.add(cell, day + 1, hour - START_HOUR + 1);
            }
        }

        // Populate cells with events from Schedule class
        HashMap<DaysOfTheWeek, HashMap<TimeChunk, TimeBlockable>> schedule = Schedule.scheduleMap;

        for (DaysOfTheWeek day : schedule.keySet()) {
            HashMap<TimeChunk, TimeBlockable> events = schedule.get(day);
            for (TimeChunk timeChunk : events.keySet()) {
                TimeBlockable TimeBlock = events.get(timeChunk);
                int startHour = timeChunk.getStartTime().getHour();
                int endHour = timeChunk.getEndTime().getHour();
                int dayIndex = day.ordinal() + 1; // +1 because column 0 is time

                for (int hour = startHour; hour < endHour; hour++) {
                    StackPane cell = (StackPane) calendarGrid.getChildren().get((hour - START_HOUR + 1) * DAYS.length + dayIndex);
                    // color the event block based on the color given in its labe
                    if (TimeBlock.getLabel() != null) {
                        cell.setStyle("-fx-background-color: " + TimeBlock.getLabel().getColor() + ";");
                    } else {
                        cell.setStyle("-fx-background-color: rgb(156, 162, 157);");
                    
                    }
                    Label eventLabel = new Label(event.getName());
                    eventLabel.setStyle("-fx-font-weight: bold;");
                    cell.getChildren().add(eventLabel);
                }
            }
        }

        ScrollPane scrollPane = new ScrollPane(calendarGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

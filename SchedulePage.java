import java.lang.classfile.Label;
import java.time.LocalDate;

import com.apple.eawt.Application;
/*
 * SchedulePage.java
 * This class creates a schedule page for a week.
 * It displays the days of the week and time slots.
 * 
 */
public class SchedulePage extends Application {
    private static final String[] DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    private static final int START_HOUR = 0;
    private static final int END_HOUR = 24;
    @Override
    /* Builds the Schedule screen. 
     * @param primaryStage The primary stage for this application
     * @param date The date for the schedule
     */
    public void start(Stage primaryStage, LocalDate date)
    {
        primaryStage.setTitle("Your Schedule for Week" + date);
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
            timeLabel.setStyle("-fx-background-color:rgb(246, 226, 240); -fx-alignment: center;");
            calendarGrid.add(timeLabel, 0, hour - START_HOUR + 1);

            for (int day = 0; day < DAYS.length; day++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(100, 50);
                cell.setStyle("-fx-background-color:rgb(200, 222, 205);");
                calendarGrid.add(cell, day + 1, hour - START_HOUR + 1);
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

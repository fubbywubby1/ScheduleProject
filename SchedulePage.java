import java.lang.classfile.Label;
import java.time.LocalDate;
/*
 * SchedulePage.java
 * This class creates a schedule page for a week.
 * It displays the days of the week and the TimeChunks within each day.
 * @WIP
 * 
 * @author Emily Schwartz
 */
public class SchedulePage extends Application {
    private static final String[] DAYS = {
        DaysOfTheWeek.values();
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
        HashMap<DaysOfTheWeek, HashMap<TimeChunk, Event>> schedule = Schedule.scheduleMap;

        for (DaysOfTheWeek day : schedule.keySet()) {
            HashMap<TimeChunk, Event> events = schedule.get(day);
            for (TimeChunk timeChunk : events.keySet()) {
                Event event = events.get(timeChunk);
                int startHour = timeChunk.getStartHour();
                int endHour = timeChunk.getEndHour();
                int dayIndex = day.ordinal() + 1; // +1 because column 0 is time

                for (int hour = startHour; hour < endHour; hour++) {
                    StackPane cell = (StackPane) calendarGrid.getChildren().get((hour - START_HOUR + 1) * DAYS.length + dayIndex);
                    // depending on label, set the color of the cell in a switch statement
                    switch (event.getLabel()) {
                        case SELFCARE:
                            cell.setStyle("-fx-background-color:rgb(154, 187, 246);");
                            break;
                        case SCHOOL:
                            cell.setStyle("-fx-background-color:rgb(245, 91, 155);");
                            break;
                        case WORK:
                            cell.setStyle("-fx-background-color:rgb(249, 199, 64);");
                            break;
                        case CHORES:
                            cell.setStyle("-fx-background-color:rgb(196, 252, 217);");
                            break;
                        case STUDY:
                            cell.setStyle("-fx-background-color:rgb(230, 160, 196);");
                            break;
                        case CLUB:
                            cell.setStyle("-fx-background-color:rgb(238, 211, 121);");
                            break;
                        case LEISURE:
                            cell.setStyle("-fx-background-color:rgb(229, 196, 252);");
                            break;
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

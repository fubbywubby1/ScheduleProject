public class FindExistingSchedulePage extends Application {
    // stuff go here once we have more functionality done
    // this is the page where a user can find their existing schedule
    // if they have one, we can go to the Schedule Page
    // if not, they make a new page!
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Choose Existing Schedule");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Button newScheduleButton = new Button("Create New Schedule");
        newScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        newScheduleButton.setOnAction(e -> {
             ScheduleGUI.startCreateSchedulePage(primaryStage);
        });
        //add layout.getchildren


    }

    public static void main(String[] args) {
        launch(args);
    }
}

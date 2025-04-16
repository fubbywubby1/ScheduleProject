/*
 * StartPage.java
 * This creates the start page for the application, which
 * will then lead to either an existing schedule or the necessary screens to build a new schedule.
 * @WIP
 * @author Emily Schwartz
 */
public class StartPage extends Application {
    boolean existingSchedule;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Start Page");
        Button newScheduleButton = new Button("Create New Schedule");
        newScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        newScheduleButton.setOnAction(e -> {
             startCreateSchedulePage(primaryStage);
        });
        Button loadScheduleButton = new Button("Load Existing Schedule");`
        loadScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        loadScheduleButton.setOnAction(e -> {
            startFindExistingSchedulePage(primaryStage);
        });
    `   VBox layout = new VBox(10);
        layout.getChildren().addAll(newScheduleButton, loadScheduleButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void startFindExistingSchedulePage(Stage primaryStage) {
        FindExistingSchedulePage findExistingSchedulePage = new FindExistingSchedulePage();
        findExistingSchedulePage.start(primaryStage);
    }

    private void startCreateSchedulePage(Stage primaryStage) {
        CreateSchedulePage createSchedulePage = new CreateSchedulePage();
        createSchedulePage.start(primaryStage);
    }
}
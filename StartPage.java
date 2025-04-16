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
            existingSchedule = false;
        });
        Button loadScheduleButton = new Button("Load Existing Schedule");`
        loadScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        loadScheduleButton.setOnAction(e -> {
            existingSchedule = true;
        });
    `   VBox layout = new VBox(10);
        layout.getChildren().addAll(newScheduleButton, loadScheduleButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        try {
            if(existingSchedule) {
                // Go to FindExistingSchedulePage
                FindExistingSchedulePage findExistingSchedulePage = new FindExistingSchedulePage();
                findExistingSchedulePage.start(new Stage());
            } else {
                // Go to CreateSchedulePage
                CreateSchedulePage createSchedulePage = new CreateSchedulePage();
                createSchedulePage.start(new Stage());
            }
        } catch (Exception e) {
            // fill this in later
            e.printStackTrace();
        }
    }
}
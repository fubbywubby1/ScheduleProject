public class StartPage extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Start Page");
        Button newScheduleButton = new Button("Create New Schedule");
        newScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        newScheduleButton.setOnAction(e -> {
            // Handle button click
        });
        Button loadScheduleButton = new Button("Load Existing Schedule");`
        loadScheduleButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        loadScheduleButton.setOnAction(e -> {
            // Handle button click
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
}
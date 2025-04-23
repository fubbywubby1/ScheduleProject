import com.apple.eawt.Application;

public class CreateSchedulePage extends Application {
    // stuff go here once we have more functionality done

    public void start(Stage primaryStage)
    {
        public static String scheduleName;
        public String eventName;
        public String day;
        public String startTime;
        public String endTime;
        public Label label;
        primaryStage.setTitle("Create a New Schedule");
        TextField nameTextBox = new TextField("Enter Schedule Name");
        TextField eventTextBox = new TextField("Enter Event Name");
        
        ComboBox<String> dayComboBox = new ComboBox<>();
        dayComboBox.getItems().addAll(DaysOfTheWeek.values());
        dayComboBox.setPromptText("Choose Day");

        TextField hourTextBoxA = new TextField("Enter Start Hour");
        TextField minuteTextBoxA = new TextField("Enter Start Minute");
        TextField hourTextBoxB = new TextField("Enter End Hour");
        TextField minuteTextBoxB = new TextField("Enter End Minute");

        ComboBox<String> labelComboBox = new ComboBox<>();
        labelComboBox.getItems().addAll(Label.values());
        labelComboBox.setPromptText("Choose Label");

        Button addEventButton = new Button("Add Event");
        addEventButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        addEventButton.setOnAction(e -> {
            scheduleName = nameTextBox.getText();
            eventName = eventTextBox.getText();
            day = dayComboBox.getValue();
            startTime = hourTextBoxA.getText() + ":" + minuteTextBoxA.getText();
            endTime = hourTextBoxB.getText() + ":" + minuteTextBoxB.getText();
            String label = labelComboBox.getValue();

            // WRITE IN: passing it to the hashmap

            // Clear the text fields
            nameTextBox.clear();
            eventTextBox.clear();
            hourTextBoxA.clear();
            minuteTextBoxA.clear();
            hourTextBoxB.clear();
            minuteTextBoxB.clear();

            //disable schedule name text box
            nameTextBox.setDisable(true);

            // Show success message
            label.setText("Event added successfully!");
        });

        Button rateStressButton = new Button("Continue to Rate Stress");
        rateStressButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        rateStressButton.setOnAction(e -> {
            ScheduleGUI.startRateStressPage(primaryStage);
        });
    }
    // textboxOne: name of schedule
    // textbox: name of event
    // dropdown: choose day
    // dropdown: choose label
    // radio buttons: Task? Timed Tasks?
    // textbox a and b: start time
    // textbox c and d: end time
    // button: add event - on first click, create a new schedule
    // and its hashmap, named the name, and add the event to it.
    // the textboxOne should disappear, and then the add Event functionality
    // must remain.
    // if it takes in a non time task, input the designated "null" time
    // into the constructor.
}
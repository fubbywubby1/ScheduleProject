import com.apple.eawt.Application;

public class CreateSchedulePage extends Application {
    // stuff go here once we have more functionality done

    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Create a New Schedule");
        TextField nameTextBox = new TextField("Enter Schedule Name");
        TextField eventTextBox = new TextField("Enter Event Name");
        
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
import java.util.ArrayList;
import java.util.HashMap;

import com.apple.eawt.Application;

public class RateStressPage extends Application{
    // one page, and a Slider for each Label.
    static ArrayList<Integer> stressLevels = new ArrayList<>();
    public int averageStress = 0;
public void start(Stage primaryStage) {
    primaryStage.setTitle("Rate Your Stress Levels");
    // create a grid layout
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10));
    grid.setVgap(8);
    grid.setHgap(10);

    // sliders for each Label's stress levels
    Label workLabel = new Label("How stressful is work?");
    Slider workSlider = new Slider();

    Label schoolLabel = new Label("How stressful is school?");
    Slider schoolSlider = new Slider();

    Label clubLabel = new Label("How stressful are your extracurriculars?");
    Slider clubSlider = new Slider();

    Label choresLabel = new Label("How stressful are your chores?");
    Slider choresSlider = new Slider();

    Label personalLabel = new Label("How stressful is your personal life?");
    Slider personalSlider = new Slider();

    // set the min and max values for the sliders
    workSlider.setMin(0);
    workSlider.setMax(10); 
    schoolSlider.setMin(0);
    schoolSlider.setMax(10);
    clubSlider.setMin(0);
    clubSlider.setMax(10);
    choresSlider.setMin(0);
    choresSlider.setMax(10);
    personalSlider.setMin(0);
    personalSlider.setMax(10);

    // add the sliders to the grid
    grid.add(workLabel, 0, 0);
    grid.add(workSlider, 1, 0);
    grid.add(schoolLabel, 0, 1);
    grid.add(schoolSlider, 1, 1);
    grid.add(clubLabel, 0, 2);
    grid.add(clubSlider, 1, 2);
    grid.add(choresLabel, 0, 3);
    grid.add(choresSlider, 1, 3);
    grid.add(personalLabel, 0, 4);
    grid.add(personalSlider, 1, 4);

    // create a button to submit the stress levels
    Button submitButton = new Button("Submit");
    submitButton.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
    submitButton.setOnAction(e -> {
        // get the stress levels from the sliders
        int workStress = (int) workSlider.getValue();
        int schoolStress = (int) schoolSlider.getValue();
        int clubStress = (int) clubSlider.getValue();
        int choresStress = (int) choresSlider.getValue();
        int personalStress = (int) personalSlider.getValue();

        // add the stress levels to the array list
        stressLevels.add(workStress);
        stressLevels.add(schoolStress);
        stressLevels.add(clubStress);
        stressLevels.add(choresStress);
        stressLevels.add(personalStress);

        // average all stress levels together
        int totalStress = 0;
        for (int i = 0; i < stressLevels.size(); i++) {
            totalStress += stressLevels.get(i);
        }

        averageStress = totalStress / stressLevels.size();

        // close the window
        primaryStage.close();

        // pass this information to the Self Care scheduler.
        // EMILY DO THIS
    });
    // add the button to the grid
    grid.add(submitButton, 0, 5, 2, 1);
    // create a scene and add the grid to it
    Scene scene = new Scene(grid, 400, 300);
    primaryStage.setScene(scene);
    primaryStage.show();



    // pass this information to the Self Care scheduler.

}

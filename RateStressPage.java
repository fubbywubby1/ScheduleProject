import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;

import java.util.logging.Logger;

/**
 * This is the RateStressPage class.
 * It generates the GUI for the user to rate their stress levels.
 * The user can rate their stress levels on a scale of 0 to 10.
 * When the user clicks the submit button, the stress levels are passed to the Self Care scheduler, which
 * generates a new schedule with additional self-care activities.
 * 
 * @author Alexander Simonson, Emily Schwartz, Douglas Tranz and Molly O'Brien
 */
public class RateStressPage extends Application {
    static ArrayList<Integer> stressLevels = new ArrayList<>();
    public int averageStress = 0;

    private static final Logger logger = Logger.getLogger("RateStressPage");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rate Your Stress Levels");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setStyle("-fx-background-color: #ffe6f0;");

        String[] labels = {
            "How stressful is work?",
            "How stressful is school?",
            "How stressful are your extracurriculars?",
            "How stressful are your chores?",
            "How stressful is your personal life?"
        };

        Slider[] sliders = new Slider[labels.length];

        for (int i = 0; i < labels.length; i++) {
            Label questionLabel = new Label(labels[i]);
            questionLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");
            sliders[i] = createStyledSlider();

            grid.add(questionLabel, 0, i);
            grid.add(sliders[i], 1, i);
        }

        Button submitButton = new Button("Submit");
        submitButton.setStyle(
            "-fx-font-size: 16px; -fx-padding: 10px 20px; " +
            "-fx-background-color: #f4b6c2; -fx-text-fill: white; -fx-background-radius: 8;"
        );
        submitButton.setOnAction(e -> {
            stressLevels.clear();
        for (Slider s : sliders) {
        stressLevels.add((int) s.getValue());
         }
            try {
        SelfCareScheduler scheduler = new SelfCareScheduler();
        scheduler.scheduleSelfCareActivities(stressLevels);
        logger.info("Scheduled SelfCare Activites");
        } catch (Exception ex) {
            logger.warning("Could not schedule selfcare activities");
        ex.printStackTrace();
    }

    // Transition to the SchedulePage using the same stage
    ScheduleGUI.startSchedulePage(primaryStage);
    });

        grid.add(submitButton, 0, labels.length, 2, 1);
        Scene scene = new Scene(grid, 550, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createStyledSlider() {
        Slider slider = new Slider(0, 10, 0);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setStyle("-fx-control-inner-background: #f8d6e0;");
        return slider;
    }
}



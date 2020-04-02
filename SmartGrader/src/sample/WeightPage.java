package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WeightPage {

    public TextField QuizWeightTextField;
    public TextField HomeworkWeightTextField;
    public TextField ProjectWeightTextField;
    public TextField ExamWeightTextField;
    public Label TotalPercentageLabel;
    public Button CancelButton;
    public Button SaveButton;

    public void clickedSave(ActionEvent event) {

    }

    public void clickedCancel(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void addTextFields(KeyEvent keyEvent) {
        //Adds up values of all integers entered in text field
        int i = (Integer.parseInt(QuizWeightTextField.getText()) +
                Integer.parseInt(HomeworkWeightTextField.getText()) + Integer.parseInt(ProjectWeightTextField.getText()) +
                Integer.parseInt(ExamWeightTextField.getText()));
        TotalPercentageLabel.setTextFill(Color.color(.65, .71, .72));
        TotalPercentageLabel.setText("Total Percentage: " + i + " %");
        if (i != 100) {
            TotalPercentageLabel.setTextFill(Color.color(.5, .2, .2));
            TotalPercentageLabel.setText(TotalPercentageLabel.getText() + ". Percentage must be 100.");
        }
    }

}


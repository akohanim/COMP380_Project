package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class WeightPage {

    public TextField QuizWeightTextField;
    public TextField HomeworkWeightTextField;
    public TextField ProjectWeightTextField;
    public TextField ExamWeightTextField;
    public Label TotalPercentageLabel;
    public Button CancelButton;
    public Button SaveButton;
    public CheckBox weightCheckbox;

    private String userEmail, courseName;

    public void clickedSave(ActionEvent event) throws IOException {
        try {
            if (TotalPercentageLabel.getText().equals("Total Percentage: 100 %")) {

                Weights weights = new Weights(getUserEmail());

                weights.edit_Weight(getCourseName(), "Quiz", QuizWeightTextField.getText());
                weights.edit_Weight(getCourseName(), "Homework", HomeworkWeightTextField.getText());
                weights.edit_Weight(getCourseName(), "Exam", ExamWeightTextField.getText());
                weights.edit_Weight(getCourseName(), "Projects", ProjectWeightTextField.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Weights have been saved.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();

                clickedCancel(event);
            } else {
                //Show a message that percentage is not 100
                Alert alert = new Alert(Alert.AlertType.ERROR, "Percentage must equal 100.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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


    public void loadWeightsForClass() throws IOException {
        Weights weights = new Weights(getUserEmail());
        if (weights.is_Using_Weights_On(getCourseName()) == true) {
            try {
                //Make sure weights exist before entering default weights
                if (weights.does_this_Weight_Exists(getCourseName(), "Quiz")) {

                    //fill all weights with stored data
                    QuizWeightTextField.setText(weights.load_Weight_Percentage(getCourseName(), "Quiz"));
                    HomeworkWeightTextField.setText(weights.load_Weight_Percentage(getCourseName(), "Homework"));
                    ExamWeightTextField.setText(weights.load_Weight_Percentage(getCourseName(), "Exam"));
                    ProjectWeightTextField.setText(weights.load_Weight_Percentage(getCourseName(), "Projects"));

                    TotalPercentageLabel.setText("Total Percentage: " + weights.get_Total_Weight_Percentage(getCourseName()) + " %");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void checkboxChecked(ActionEvent actionEvent) {
        Weights weights = new Weights(getUserEmail());
        if (weightCheckbox.isSelected()){
            weights.set_Using_Weights_On_Or_Off(getCourseName(),"On" );
        } else {
            weights.set_Using_Weights_On_Or_Off(getCourseName(),"Off" );
        }
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


}


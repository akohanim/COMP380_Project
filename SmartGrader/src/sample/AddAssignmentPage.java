package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAssignmentPage {

    public ComboBox comboBox;
    public TextField PointsTextField;
    public TextField NameTextField;
    public Button CancelButton;
    public Button AddButton;


    @FXML
    public void initialize() {
        //use to add multiple items to combo box upon initializing screen
        comboBox.getItems().addAll("Quizzes", "Homework", "Projects", "Exams");

        //use for individual items
        //comboBox.getItems().add()
    }

    public void clickedCancel(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void clickedAddButton(ActionEvent event) {
        //TODO:we can either set the inputs for the callback or add the assignment here and refresh the tableview in classOverview
        NameTextField.getText();
        PointsTextField.getText();
        comboBox.getValue();
    }
}

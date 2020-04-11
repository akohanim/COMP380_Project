package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentPage {
    @FXML
    private Button AddButton, CancelButton;
    @FXML
    private TextField FirstNameTextField, LastNameTextField, StudentIDNumberTextField;

    //close popup window
    public void clickedCancel(ActionEvent event) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }


    public void clickedAddButton(ActionEvent event) {
        //TODO Add Button must add Student to backend spreadsheet, then reset the tableview in classOverview
        FirstNameTextField.getText();
        LastNameTextField.getText();
        StudentIDNumberTextField.getText();

    }
}

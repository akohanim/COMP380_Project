package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCoursePage {

    @FXML
    private TextField CourseNameTextField;

    @FXML
    private TextField SectionNumberTextField;


    public void CancelButtonClicked(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void AddButtonClicked(ActionEvent event) {
        //TODO: not sure what needs to be done when the button is added
        CourseNameTextField.getText();
        SectionNumberTextField.getText();


     }
}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAssignmentPage {

    public javafx.scene.control.ComboBox ComboBox;
    public TextField PointsTextField;
    public TextField NameTextField;
    public Button CancelButton;
    public Button AddButton;

    @FXML
    public void initialize() {
        //use to add multiple items to combo box upon initializing screen
        //ComboBox.getItems().addAll()

        //use for individual items
        //ComboBox.getItems().add()
    }
    public void clickedCancel(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void clickedAddButton(ActionEvent event) {
    }
}

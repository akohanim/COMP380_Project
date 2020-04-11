package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SetCurvePage {

    @FXML
    private TextField SetCurveTextField;
    @FXML
    private Button SubtractButton;
    @FXML
    private Button AddButton;
    @FXML
    private Button CancelButton;
    @FXML
    private Button ResultsButton;

    private boolean isValidInput() {
        //make sure textfield is not empty.
        if (SetCurveTextField.getText() == null || SetCurveTextField.getText().trim().isEmpty()) {
            return false;
        } else {
            try {
                //check if text field has an integer less than or equal to 100
                int i = Integer.parseInt(SetCurveTextField.getText());
                if (i > 100 || i < 0) {
                    SetCurveTextField.setText("0");
                    return false;
                }
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return true;
    }

    public void clickedSubtract(ActionEvent event) {
        if (isValidInput()) {
            int i = Integer.parseInt(SetCurveTextField.getText());
            if (i >= 5) {
                SetCurveTextField.setText(Integer.toString(i - 5));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Value too low.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();
        }
    }

    public void clickedAdd(ActionEvent event) {
        if (isValidInput()) {
            int i = Integer.parseInt(SetCurveTextField.getText());
            if (i <= 95) {
                SetCurveTextField.setText(Integer.toString(i + 5));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Value too high.", ButtonType.OK);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void clickedCancel(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void clickedResults(ActionEvent event) {
    }
}

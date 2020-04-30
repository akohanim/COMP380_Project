package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetCurvePage {

    @FXML
    private TextField SetCurveTextField;

    private String userEmail, classname;

    private boolean wantsCurve;

    public double getCurvePercentage() {
        return curvePercentage;
    }

    public void setCurvePercentage(double curvePercentage) {
        this.curvePercentage = curvePercentage;
    }

    private double curvePercentage;


    @FXML
    public void initialize() {
        setCurve(false);
    }


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

    public void clickedCancel(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void clickedResults(ActionEvent event) {
        if (isValidInput()) {
            curvePercentage=Double.parseDouble(SetCurveTextField.getText());
            setCurve(true);

            // close window
            clickedCancel(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();

        }


    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public boolean isCurved() {
        return wantsCurve;
    }

    public void setCurve(boolean wantsCurve) {
        this.wantsCurve = wantsCurve;
    }

}

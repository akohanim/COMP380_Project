package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountSettingsPane {
    @FXML
    private TextField firstNameTextField, lastNameTextField, passwordTextField, emailTextField;
    private String newEmail;
    private String newPassword;
    private String newFirstName;
    private String newLastName;


    public String getNewEmail() {
        return newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    @FXML
    public void initialize() {
        //initialize Fields as empty
        newEmail = "";
        newPassword = "";
        newFirstName = "";
        newLastName = "";
    }

    //set inputs for when i call them back to homepage
    public void setCallbackInputs(String newFirstName, String newLastName, String newEmail, String newPassword) {
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newEmail = newEmail;
        this.newPassword = newPassword;

    }

    //close page
    public void clickedCancel(ActionEvent event) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }


    public void clickedSave(ActionEvent event) {
        //validate inputs
        if (firstNameTextField.getText() == null || lastNameTextField.getText() == null || passwordTextField.getText() == null || emailTextField.getText() == null
                || firstNameTextField.getText().trim().isEmpty() || lastNameTextField.getText().trim().isEmpty() || passwordTextField.getText().trim().isEmpty() || emailTextField.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();

            //set callback inputs and close window
        } else {
            setCallbackInputs(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), passwordTextField.getText());

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.close();

        }
    }
}

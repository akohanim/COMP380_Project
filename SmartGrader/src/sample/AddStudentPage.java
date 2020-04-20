package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddStudentPage {
    @FXML
    private Button AddButton, CancelButton;
    @FXML
    private TextField FirstNameTextField, LastNameTextField, StudentIDNumberTextField, DOBTextField;

    private String userEmail, classname;

    //close popup window
    public void clickedCancel(ActionEvent event) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }


    public void clickedAddButton(ActionEvent event) throws IOException {
        // Add Button must add Student to backend spreadsheet, then reset the tableview in classOverview

        //Input Validation
        if (FirstNameTextField.getText().trim().isEmpty() || LastNameTextField.getText().trim().isEmpty() || StudentIDNumberTextField.getText().trim().isEmpty() || DOBTextField.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();

        } else {
            Students students = new Students(getUserEmail());

            if (students.does_This_Student_Exists(getClassname(), StudentIDNumberTextField.getText()) == true) {
                //user already exists
                Alert alert = new Alert(Alert.AlertType.ERROR, "Student Already Exists", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();

            } else {
                students.add_Student(getClassname(), FirstNameTextField.getText(), LastNameTextField.getText(), DOBTextField.getText(), StudentIDNumberTextField.getText());
                //student is added
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student has been added.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();

                clickedCancel(event);

            }

        }
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

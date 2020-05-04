package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditStudentPage {
    public TextField FirstNameTextField;
    public TextField LastNameTextField;
    public TextField StudentIDNumberTextField;
    public TextField DOBTextField;

    private String userEmail, courseName, oldStudentIDNumber;

    public void clickedCancel(ActionEvent actionEvent) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void clickedEditButton(ActionEvent actionEvent) throws IOException {
        try {
            if (FirstNameTextField.getText().trim().isEmpty() || LastNameTextField.getText().trim().isEmpty() || StudentIDNumberTextField.getText().trim().isEmpty() || DOBTextField.getText().trim().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();

            } else {
                Students students = new Students(getUserEmail());

                students.update_This_Student_Information(getCourseName(),getOldStudentIDNumber(),FirstNameTextField.getText(),LastNameTextField.getText(),DOBTextField.getText(), StudentIDNumberTextField.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Selected Student information has been updated.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();

                clickedCancel(actionEvent);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void clickedDeleteStudent(ActionEvent actionEvent) throws IOException {
        try {
            Students students = new Students(getUserEmail());
            students.delete_Student(getCourseName(),getOldStudentIDNumber());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Selected Student has been deleted.", ButtonType.OK);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
        alert.showAndWait();

        clickedCancel(actionEvent);

    }

    public void fillData(String first, String last, String DOB) {
        FirstNameTextField.setText(first);
        LastNameTextField.setText(last);
        StudentIDNumberTextField.setText(getOldStudentIDNumber());
    }

    public String getOldStudentIDNumber() {
        return oldStudentIDNumber;
    }

    public void setOldStudentIDNumber(String oldStudentIDNumber) {
        this.oldStudentIDNumber = oldStudentIDNumber;
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
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class EditAssignmentPage {
    public TextField NameTextField;
    public TextField PointsTextField;
    public ChoiceBox choiceBox;

    private String userEmail, courseName;
    int columnNumber;

    @FXML
    public void initialize() {
        //use to add multiple items to combo box upon initializing screen
        choiceBox.getItems().addAll("Quiz", "Homework", "Projects", "Exam");

    }

    public void clickedEditButton(ActionEvent actionEvent) throws IOException {
        try {
            if (PointsTextField.getText().trim().isEmpty() || NameTextField.getText().trim().isEmpty() || choiceBox.getValue().toString().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all fields.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            } else {
                Assignments assignments = new Assignments(getUserEmail());

                assignments.edit_Assignment(getCourseName(), (getColumnNumber() + 1) , NameTextField.getText(), PointsTextField.getText(),choiceBox.getValue().toString());

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student has been Edited.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void clickedCancel(ActionEvent actionEvent) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    //delete assignment
    public void clickedDelete(ActionEvent actionEvent) throws IOException, InvalidFormatException {
        try {
            Assignments assignments = new Assignments(getUserEmail());
            assignments.delete_Assignment(getCourseName(), (getColumnNumber()+1));

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student has been Deleted.", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();

            clickedCancel(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
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

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
}

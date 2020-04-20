package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddAssignmentPage {

    public ChoiceBox choiceBox;
    public TextField PointsTextField;
    public TextField NameTextField;
    public Button CancelButton;
    public Button AddButton;
    private String userEmail, courseName;

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

    @FXML
    public void initialize() {
        //use to add multiple items to combo box upon initializing screen
        choiceBox.getItems().addAll("Quiz", "Homework", "Projects", "Exam");

    }

    public void clickedCancel(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void clickedAddButton(ActionEvent event) throws IOException {
        try {
            if (PointsTextField.getText().trim().isEmpty() || NameTextField.getText().trim().isEmpty() || choiceBox.getValue().toString().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            } else {
                Assignments assignments = new Assignments(getUserEmail());

                if (assignments.does_This_Assignment_Exists(getCourseName(), NameTextField.getText()) == true) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Assignment already exists", ButtonType.OK);
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                    alert.showAndWait();
                } else {
                    assignments.add_Assignment(getCourseName(), NameTextField.getText(), PointsTextField.getText(), choiceBox.getValue().toString());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Assignment has been added.", ButtonType.OK);
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                    alert.showAndWait();

                    clickedCancel(event);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

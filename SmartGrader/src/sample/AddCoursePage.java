package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCoursePage {

    //returnable strings
    String courseName;
    String sectionNumber;

    /* The add course page works by setting 2 local variables form the initialize method, and then
    returning those filled strings back to the homepage where the data is added to a course spreadsheet
    */
    @FXML
    private TextField SectionNumberTextField;

    @FXML
    private TextField CourseNameTextField;

    //initialize return variables
    @FXML
    public void initialize() {
        courseName = "";
        sectionNumber = "";
    }

    //return course name
    public String getCourseName() {
        return courseName;
    }

    //return section number string
    public String getSectionNumber() {
        return sectionNumber;
    }

    //set variables that will be returned
    public void setCallbackInputs(String courseName, String sectionNumber) {
        this.courseName = courseName;
        this.sectionNumber = sectionNumber;

    }

    //close popup window when cancel is clicked
    public void CancelButtonClicked(ActionEvent event) {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    //set inputs that will be called back as long as input is valid
    public void AddButtonClicked(ActionEvent event) throws IOException {
        //Input Validation
        if (CourseNameTextField.getText().trim().isEmpty() || SectionNumberTextField.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();

        } else {
            //set inputs to return to homepage
            setCallbackInputs(CourseNameTextField.getText(), SectionNumberTextField.getText());

            // close window
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.close();

        }
    }

    //getters & Setters
    public TextField getCourseNameTextField() {
        return CourseNameTextField;
    }

    public void setCourseNameTextField(TextField courseNameTextField) {
        CourseNameTextField = courseNameTextField;
    }

    public TextField getSectionNumberTextField() {
        return SectionNumberTextField;
    }

    public void setSectionNumberTextField(TextField sectionNumberTextField) {
        SectionNumberTextField = sectionNumberTextField;
    }
}

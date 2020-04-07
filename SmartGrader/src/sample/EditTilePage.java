package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class EditTilePage {
    public TextField courseNumberTextfield, courseNameTextfield;
    public ColorPicker colorPicker;
    public Button uploadImageButton, saveButton, deleteButton;

    public String courseName, userName, courseNumber;
    public File file;
    Color color;

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @FXML
    public void initialize() {
        userName = "";
        file = null;
        color = null;

    }

    public void clickedSave(ActionEvent event) throws IOException {
        CourseSettings courseSettings = new CourseSettings(userName, courseName);

        try {
            if (!courseNumberTextfield.getText().trim().isEmpty() || !(courseNumberTextfield.getText().equals(""))) {
                courseSettings.change_The_Course_Section_Number_To(courseNumberTextfield.getText());
                setCourseNumber(courseNumberTextfield.getText());
            }
            if (file != null) {
                courseSettings.save_The_Course_Icon(file.getAbsolutePath());
            }
            if (color != null) {
                System.out.println("Red: " + color.getRed() + " Green: " + color.getGreen() + " Blue: " + color.getBlue());
                courseSettings.save_The_Course_Color((int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
            }
            //Change name last, so as to not mess with other inputs
            if (!courseNameTextfield.getText().trim().isEmpty() || !(courseNameTextfield.getText().equals(""))) {
                courseSettings.rename_The_Course_To(courseNameTextfield.getText());
                setCourseName(courseNameTextfield.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        clickedCancel(event);

    }

    public void clickedDelete(ActionEvent event) {
        CourseSettings courseSettings = new CourseSettings(userName, courseName);
        courseSettings.delete_The_Course();
        //close window
        clickedCancel(event);
    }

    public void clickedCancel(ActionEvent event) {
        //close window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }

    public void clickedUploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Photo");
        file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());


    }


    public void clickedColorPicker(ActionEvent event) {
        color = colorPicker.getValue();

    }
}

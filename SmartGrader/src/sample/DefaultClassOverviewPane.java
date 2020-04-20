package sample;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class DefaultClassOverviewPane {
    @FXML
    public TableView<ObservableList<StringProperty>> tableView;

    // SwingNode container;
    //public JTable jTable;
    public HBox buttonBox;
    public Button AddStudentButton;
    public Button AddAssignmentButton;
    public Region fillerRegion;
    public VBox mainContainer;
    private String className, userEmail;

    @FXML
    public void initialize() {
    }

    public void clickedAddStudent(ActionEvent event) throws IOException {
        System.out.println("Click Add: class name: " + getClassName());
        System.out.println(getUserEmail());

        try {
            //Open up Add student page
            FXMLLoader addStudentPageLoader = new FXMLLoader();
            addStudentPageLoader.setLocation(getClass().getResource("AddStudentPage.fxml"));
            Parent parent = addStudentPageLoader.load();
            //assign addStudentPageController
            AddStudentPage addStudentPageController = addStudentPageLoader.getController();
            //set  Email for use add page window
            addStudentPageController.setClassname(getClassName());
            addStudentPageController.setUserEmail(getUserEmail());

            //Change window
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("SmartGrader");
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickedAddAssignment(ActionEvent event) throws IOException {
        try {
            //Open up addAssignment page
            FXMLLoader addAssignmentPageLoader = new FXMLLoader();
            addAssignmentPageLoader.setLocation(getClass().getResource("AddAssignmentPage.fxml"));
            Parent parent = addAssignmentPageLoader.load();
            //assign addAssignmentPageController
            AddAssignmentPage addAssignmentPageController = addAssignmentPageLoader.getController();
            //set  Email for use add page window
            addAssignmentPageController.setCourseName(getClassName());
            addAssignmentPageController.setUserEmail(getUserEmail());


            //Change window
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("SmartGrader");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillDefaultTableView() {
    }

    public void fillCurveTableView(CurvedResults curvedResults) throws IOException {

    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}

package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.print.PrinterException;
import java.io.IOException;

public class ClassOverview {

    @FXML
    public DefaultClassOverviewPane defaultClassOverviewPaneController;
    //variables for main screen
    @FXML
    private BorderPane mainView;
    @FXML
    private Label classNameLabel, courseNumberLabel;

    private String username, courseName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        defaultClassOverviewPaneController.setUserEmail(username);
    }

    public String getCourseName() {
        return courseName;
    }


    @FXML
    public void initialize() {
    }

    //clicking home button takes you back to homepage.
    public void homeButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader homePageLoader = new FXMLLoader();
            homePageLoader.setLocation(getClass().getResource("HomePage.fxml"));
            Parent parent = homePageLoader.load();
            //assign homePageController
            HomePage homePageController = homePageLoader.getController();
            //set keep Email for use in next text field
            homePageController.setUsername(getUsername());
            //Fill homepage with tiles
            homePageController.fillClassTiles(getUsername());
            homePageController.moveAddButtonToEnd();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("SmartGrader");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchButtonClicked(ActionEvent event) throws IOException {
        // switch main view to search view
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchClassOverviewPane.fxml"));
            mainView.getChildren().clear();
            mainView.setCenter(loader.load());
            //use any functions in searchClassOverviewPane controller
            SearchClassOverviewPane searchController = loader.getController();
            searchController.setCourseName(getCourseName());
            searchController.setUserName(getUsername());
            searchController.setBorderPane(mainView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader printLoader = new FXMLLoader(getClass().getResource("PrintPopUp.fxml"));
        Parent root = printLoader.load();
        //intiialize vairable in popup
        PrintPopUp printPopUpController = printLoader.getController();
        printPopUpController.setCourseName(getCourseName());
        printPopUpController.setUserName(getUsername());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void curveButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader curveLoader = new FXMLLoader(getClass().getResource("SetCurvePage.fxml"));
            Parent root = curveLoader.load();
            //assign curvePage controller
            SetCurvePage curvePageController = curveLoader.getController();
            //open curve page
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //if they set a curve
            if (curvePageController.isCurved()) {
                defaultClassOverviewPaneController.fillCurveTableView(curvePageController.getCurvePercentage());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void weightButtonClicked(ActionEvent event) throws IOException {
        try {
            //Open up HomePage
            FXMLLoader weightPageLoader = new FXMLLoader();
            weightPageLoader.setLocation(getClass().getResource("WeightPage.fxml"));
            Parent parent = weightPageLoader.load();
            //assign weightPage
            WeightPage weightPageController = weightPageLoader.getController();
            //set keep Email for use in next text field
            weightPageController.setCourseName(getCourseName());
            weightPageController.setUserEmail(getUsername());
            weightPageController.loadWeightsForClass();

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

    public void graphButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader graphPageLoader = new FXMLLoader(getClass().getResource("ChooseGraphPage.fxml"));
            Parent root = graphPageLoader.load();
            //assign curvePage controller
            ChooseGraphPage chooseGraphPageController = graphPageLoader.getController();
            //assign Variables
            chooseGraphPageController.setCourseName(getCourseName());
            chooseGraphPageController.setUserEmail(getUsername());
            //open curve page
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillTable() throws IOException {
        LoadCourseData loadCourseData = new LoadCourseData(getUsername());
        String[][] data = loadCourseData.get_2D_Array_Loaded_With_The_Course_Data(getCourseName());
        defaultClassOverviewPaneController.fillGrid(data,true);


    }


    public void setClassNameLabel(String name) {
        classNameLabel.textProperty().bind(new SimpleStringProperty("Course Name: " + name));
        this.courseName = name;
        defaultClassOverviewPaneController.setClassName(name);

    }

    public void setCourseNumberLabel(String number) {
        courseNumberLabel.textProperty().bind(new SimpleStringProperty("Course Number: " + number));
    }

}

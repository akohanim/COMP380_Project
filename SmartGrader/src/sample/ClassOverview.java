package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
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

import java.io.IOException;

public class ClassOverview {

    @FXML
    public DefaultClassOverviewPane defaultClassOverviewPaneController;
    @FXML
    private VBox defaultClassOverviewPane;
    //variables for main screen
    @FXML
    private BorderPane mainView;


    @FXML
    private TableView tableView;
    @FXML
    private Button GraphButton;
    @FXML
    private Button WeightButton;
    @FXML
    private Button CurveButton;
    @FXML
    private Button PrintButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button SaveButton;
    @FXML
    private Button SearchButton;
    @FXML
    private Button HomeButton;
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

    //Class Overview Methods
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


    public void saveButtonClicked(ActionEvent event) {
        //Might be redundant if we make the table save as you edit it
    }

    public void searchButtonClicked(ActionEvent event) throws IOException {
        // switch main view to search view
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchClassOverviewPane.fxml"));
            mainView.getChildren().clear();
            mainView.setCenter(loader.load());

            //use any functions in searchClassOverviewPane controller
            SearchClassOverviewPane SearchController = loader.getController();
            SearchController.setBorderPane(mainView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printButtonClicked(ActionEvent event) {
        //this is a basis for printing, as long as there is data in the tableview
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(tableView);
            if (success) {
                job.endJob();
            }
        }
    }

    public void deleteButtonClicked(ActionEvent event) {
        //TODO highlight row or column in table view then delete the contents
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
                defaultClassOverviewPaneController.fillCurveTableView(curvePageController.getCurvedResults());
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

    public void graphButtonClicked(ActionEvent event) {
        //TODO create graph window and figure out functionality
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

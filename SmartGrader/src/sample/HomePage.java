package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class HomePage {


    CourseTile tile;
    @FXML
    private TilePane tilePane;
    @FXML
    private Button AddCourseButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button AccountButton;

    //initialize method lets you do setup for the window that is opening
    //all methods and listeners can be written in the initialize method
    @FXML
    public void initialize() {

        //set Window to full screen
        Stage stage = Main.getPrimaryStage();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());


    }


    public void clickAdd(ActionEvent event) throws IOException {
        //open add course popup window to fill in data
        FXMLLoader AddCourseLoader = new FXMLLoader();
        AddCourseLoader.setLocation(getClass().getResource("AddCoursePage.fxml"));
        Parent root = AddCourseLoader.load();
        AddCoursePage addCourseController = AddCourseLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        if (addCourseController.getCourseName() != "" && addCourseController.getSectionNumber() != "") {
            createCourseTile(addCourseController.getCourseName());

        }
    }

    public void createCourseTile(String name) throws IOException {

        //point FXML loader to CourseTile
        FXMLLoader tileLoader = new FXMLLoader();
        tileLoader.setLocation(getClass().getResource("CourseTile.fxml"));
        Parent myPane = tileLoader.load();
        //use the courseTile controller to set a name
        CourseTile tileController = tileLoader.getController();
        tileController.setClassNameLabel(name);
        //add tile to pane
        tilePane.getChildren().add(myPane);
        tilePane.setTileAlignment(Pos.TOP_LEFT);


        //puts the button at the end
        tilePane.getChildren().remove(AddCourseButton);
        tilePane.getChildren().add(AddCourseButton);
    }

    public void clickedLogout(ActionEvent event) throws IOException {
        //open Sign in page
        Parent parent = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(parent);
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.centerOnScreen();
        stageTheEventSourceNodeBelongs.show();
    }
}

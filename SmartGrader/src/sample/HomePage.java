package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.css.converter.LadderConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class HomePage {

    @FXML
    Button AddCourseButton;

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

        //set Addbutton image as button
        Image image = new Image(getClass().getResource("AddButton.png").toExternalForm(), 200, 400, true, true);
        AddCourseButton.setGraphic(new ImageView(image));

    }

}

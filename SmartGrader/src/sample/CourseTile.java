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
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class CourseTile extends Node {

    @FXML
    private Button classButton;

    @FXML
    private Label classNameLabel;

    @FXML
    public Button settingsButton;
    public String className;

    public String getClassName() {
        return className;
    }

    public void initialize() {
        className = "";
    }

    public void clickSettings(ActionEvent event) {
    }

    public void clickClassButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ClassOverview.fxml"));
        Scene scene = new Scene(parent);
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
    }

    //set name to label
    public void setClassNameLabel(String name) {
        className = name;
        classNameLabel.textProperty().bind(new SimpleStringProperty(name));
    }

    public void setTileColor(Color color) {
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        System.out.println(hex);
        classButton.setStyle("-fx-background-color: " + hex);
    }

    public void setTileIcon(String name) {
        classButton.setStyle("-fx-background-image: url(' " + name + " ')");
    }

}

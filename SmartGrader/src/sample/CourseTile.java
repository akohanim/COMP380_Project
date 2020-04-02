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

import java.io.IOException;

public class CourseTile extends Node {

    @FXML
    private Button classButton;

    @FXML
    private Label classNameLabel;

    @FXML
    private Button settingsButton;

    public void initialize() {
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
        classNameLabel.textProperty().bind(new SimpleStringProperty(name));
    }

}

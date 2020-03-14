package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

//TODO:need to make custom alretbox to fit program theme

public class AlertBox {

    @FXML
    private Label displayText;

    public void initialize(String message) {
        displayText.setText(message);
    }


    public void closeButton(ActionEvent event) {
    }
}

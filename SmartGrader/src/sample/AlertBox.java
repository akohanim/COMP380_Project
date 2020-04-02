package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AlertBox {

    @FXML
    private Label displayText;

    public void initialize(String message) {
        displayText.setText(message);
    }


    public void closeButton(ActionEvent event) {
    }
}

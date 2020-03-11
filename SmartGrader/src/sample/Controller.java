package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    Stage prevStage;

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    public void openSignUpPage() throws IOException {
        Stage stage = new Stage();
        Pane mypane = null;
        mypane = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(mypane);
        stage.setScene(scene);
        stage.show();

    }
}

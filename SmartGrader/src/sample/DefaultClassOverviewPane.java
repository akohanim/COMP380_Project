package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DefaultClassOverviewPane {

    public TableView tableView;
    @FXML
    private Button AddAssignmentButton;
    @FXML
    private Button AddStudentButton;

    public void clickedAddStudent(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddStudentPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickedAddAssignment(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddAssignmentPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

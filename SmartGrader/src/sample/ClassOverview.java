package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ClassOverview {

    //variables for main screen
    public BorderPane mainView;
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


    //Class Overview Methods
    @FXML
    public void initialize() throws IOException {
    }

    //clicking home button takes you back to homepage.
    public void homeButtonClicked(ActionEvent event) throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene scene = new Scene(parent);
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(scene);
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
    }

    public void deleteButtonClicked(ActionEvent event) {
    }

    public void curveButtonClicked(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SetCurvePage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void weightButtonClicked(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("WeightPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void graphButtonClicked(ActionEvent event) {
    }


}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SearchClassOverviewPane {
    public Button ClearButton;

    //variables for search button screen

    BorderPane mainView;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private TextField IDField;
    @FXML
    private TextField DOBField;
    @FXML
    private Button SearchStudentButton;
    @FXML
    private Button xButtton;
    @FXML
    private TableView resultsTable;

    //search class Methods
    public void clickedSearchStudent(ActionEvent event) {
        //TODO Search button will update resultsTable according to results

    }

    //clear TextFields on search pane
    public void clickedClear(ActionEvent event) {
        FirstNameField.clear();
        LastNameField.clear();
        IDField.clear();
        DOBField.clear();

    }

    //clicked x, go back to main view
    public void xButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DefaultClassOverviewPane.fxml"));
            mainView.getChildren().clear();
            mainView.setCenter(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setBorderPane(BorderPane mainView) {
        this.mainView = mainView;
    }

}

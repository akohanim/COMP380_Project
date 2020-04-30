package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StudentInfoPane {

    private BorderPane mainView;
    public Label nameLabel;
    public Label DOBLabel;
    public Label IDLabel;
    public Button backButton;
    public ChoiceBox classChoiceBox;
    public GridPane assignmentGrid;
    public GridPane gradeGrid;

    public void clickedBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchClassOverviewPane.fxml"));
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

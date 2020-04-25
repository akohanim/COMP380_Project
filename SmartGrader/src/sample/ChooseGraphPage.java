package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ChooseGraphPage {
    public ToggleGroup GradeSelectionToggle;
    public TextField fGradeMinTextfield;
    public TextField DGradeMinTextfield;
    public TextField CGradeMinTextfield;
    public TextField BfGradeMinTextfield;
    public TextField AGradeMinTextfield;

    public void clickedPieChartButton(ActionEvent actionEvent) {
        //TODO get data and build chart, pass chart to method in GraphPopup
    }

    public void clickedBarChartButton(ActionEvent actionEvent) {
        //TODO get data and build chart, pass chart to method in GraphPopup
    }

    public void clickedCancelButton(ActionEvent actionEvent) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }
}

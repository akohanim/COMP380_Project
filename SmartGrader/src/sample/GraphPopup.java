package sample;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphPopup {
    public StackPane stackPane;
    public PieChart pieChart;
    public BarChart barChart;
    public Label topLabel;
    public Pane pane;

    //set PieChart
    public void displayPieChart(PieChart chart){
        pane.getChildren().add(chart);
    }

    public void displayBarChart(BarChart chart){
        pane.getChildren().add(chart);
    }

    public void clickedClose(ActionEvent event) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }
}

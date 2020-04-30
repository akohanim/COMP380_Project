package sample;

import javafx.event.ActionEvent;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
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

    public void clickedPrint(ActionEvent actionEvent) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(pane.getScene().getWindow())){
            boolean success = job.printPage(pane);
            if (success) {
                job.endJob();
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Graph has been printed.", ButtonType.OK);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
        alert.showAndWait();

    }
}

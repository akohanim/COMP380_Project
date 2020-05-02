package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.print.PrinterException;

public class PrintPopUp {

    public RadioButton allRadioButton;
    public ToggleGroup PrintToggleGroup;
    public RadioButton nameRadioButton;
    public RadioButton IDRadioButton;
    public RadioButton DOBRadioButton;
    private String userName, courseName;

    public void clickedCancel(ActionEvent actionEvent) {
        //close popup window when cancel is clicked
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();

    }

    public void clickedPrint(ActionEvent actionEvent) throws PrinterException {
        Print print = new Print(getUserName());
        if (PrintToggleGroup.getSelectedToggle() == allRadioButton){
            print.all_Is_Selected(getCourseName());
        } else if (PrintToggleGroup.getSelectedToggle() == nameRadioButton){
            print.name_Is_Selected(getCourseName());
        } else if (PrintToggleGroup.getSelectedToggle() == IDRadioButton){
            print.id_Number_Is_Selected(getCourseName());
        } else if (PrintToggleGroup.getSelectedToggle() == DOBRadioButton){
            print.DOB_Is_Selected(getCourseName());
        }

        printTable(fillTable(print.get_Printing_Data_In_A_2D_Array()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Document Printed", ButtonType.OK);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
        alert.show();

        clickedCancel(actionEvent);
    }

    private GridPane fillTable(String[][] data) {
        GridPane gridPane = new GridPane();

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                TextField field = new TextField();
                field.setPrefHeight(Control.USE_COMPUTED_SIZE);
                field.setMaxWidth(Control.USE_COMPUTED_SIZE);
                field.setStyle("-fx-border-width: 5");
                field.setText(data[row][col]);

                //style cells appropriately
                field.setAlignment(Pos.CENTER);
                if (row % 2 == 0 && row != 0) {
                    field.setStyle("-fx-background-color: white");
                } else if (row != 0) {
                    field.setStyle("-fx-background-color: lightgray");
                }
                if (row == 0) {
                    field.setDisable(true);
                    field.setStyle("-fx-opacity: 1;");
                    field.getStyleClass().add("customTableHeader");

                }

                //add to grid
                gridPane.add(field, col, row);
            }
        }

        return gridPane;
    }

    public void printTable(GridPane gridPane) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);



        /* Print pages down and then across like so:
           1    3
           2    4
           Swop the for loops around if you want to print pages across first and then down.
        */
        gridPane.setMaxSize( pageLayout.getPrintableHeight(),pageLayout.getPrintableWidth());
        PrinterJob job = PrinterJob.createPrinterJob();
        job.printPage(gridPane );
        job.endJob();



    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

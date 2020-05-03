package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    @FXML
    private GridPane theGrid;

    @FXML

    public void clickedCancel(ActionEvent actionEvent) {
        //close popup window when cancel is clicked
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();

    }

    public void clickedPrint(ActionEvent actionEvent) throws PrinterException {
        Print print = new Print(getUserName());
        if (PrintToggleGroup.getSelectedToggle() == allRadioButton) {
            print.all_Is_Selected(getCourseName());
        } else if (PrintToggleGroup.getSelectedToggle() == nameRadioButton) {
            print.name_Is_Selected(getCourseName());
        } else if (PrintToggleGroup.getSelectedToggle() == IDRadioButton) {
            print.id_Number_Is_Selected(getCourseName());
        } else if (PrintToggleGroup.getSelectedToggle() == DOBRadioButton) {
            print.DOB_Is_Selected(getCourseName());
        }

        fillTable(print.get_Printing_Data_In_A_2D_Array());
        printTable();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Document Printed", ButtonType.OK);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
        alert.show();

        clickedCancel(actionEvent);
    }

    private void fillTable(String[][] data) {
        theGrid.getChildren().clear();
        theGrid.setAlignment(Pos.TOP_CENTER);

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
                    field.getStyleClass().add("customTableHeader");

                }

                //add to grid
                theGrid.add(field, col, row);
            }
        }
    }


    public void printTable() {
        try {
            Printer printer = Printer.getDefaultPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

            // Get the print page size:
            final double prnW = pageLayout.getPrintableWidth();
            final double prnH = pageLayout.getPrintableHeight();

            // Work out how many pages across and down are needed (This code may not work?):
            final int pagesAcross = (int) Math.ceil(theGrid.getWidth() / prnW);
            final int pagesDown = (int) Math.ceil(theGrid.getHeight() / prnH);

            /* Print pages down and then across like so:
               1    3
               2    4
               Swop the for loops around if you want to print pages across first and then down.
            */
            PrinterJob job = PrinterJob.createPrinterJob();
            for (int pgCol = 0; pgCol < pagesAcross; pgCol++) {
                for (int pgRow = 0; pgRow < pagesDown; pgRow++) {
                    theGrid.setTranslateX(-(prnW * pgCol));
                    theGrid.setTranslateY(-(prnH * pgRow));
                    job.printPage(pageLayout, theGrid);
                }
            }

            job.endJob();
        } catch (Exception e) {
            e.printStackTrace();
        }


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

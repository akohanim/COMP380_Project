package sample;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class DefaultClassOverviewPane {

    public HBox buttonBox;
    public Button AddStudentButton;
    public Button AddAssignmentButton;
    public VBox mainBox;
    public GridPane theGrid;
    public ScrollPane scrollPane;
    private String className, userEmail;
    private int rowQuantity, columnQuantity;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @FXML
    public void initialize() throws InvocationTargetException, InterruptedException {
    }

    public void clickedAddStudent(ActionEvent event) throws IOException {
        System.out.println("Click Add: class name: " + getClassName());
        System.out.println(getUserEmail());

        try {
            //Open up Add student page
            FXMLLoader addStudentPageLoader = new FXMLLoader();
            addStudentPageLoader.setLocation(getClass().getResource("AddStudentPage.fxml"));
            Parent parent = addStudentPageLoader.load();
            //assign addStudentPageController
            AddStudentPage addStudentPageController = addStudentPageLoader.getController();
            //set  Email for use add page window
            addStudentPageController.setClassname(getClassName());
            addStudentPageController.setUserEmail(getUserEmail());

            //Change window
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("SmartGrader");
            stage.setScene(scene);
            stage.showAndWait();

            refreshTable();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickedAddAssignment(ActionEvent event) throws IOException {
        try {
            //Open up addAssignment page
            FXMLLoader addAssignmentPageLoader = new FXMLLoader();
            addAssignmentPageLoader.setLocation(getClass().getResource("AddAssignmentPage.fxml"));
            Parent parent = addAssignmentPageLoader.load();
            //assign addAssignmentPageController
            AddAssignmentPage addAssignmentPageController = addAssignmentPageLoader.getController();
            //set  Email for use add page window
            addAssignmentPageController.setCourseName(getClassName());
            addAssignmentPageController.setUserEmail(getUserEmail());

            //Change window
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("SmartGrader");
            stage.setScene(scene);
            stage.showAndWait();

            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillCurveTableView(Double curve) throws IOException {
        CurvedResults curvedResults = new CurvedResults(getUserEmail(), curve);
        boolean withbuttons = false;
        String[][] data = new String[curvedResults.get_Last_Row(getClassName())-6][4];
        data[0][0] = "Full Name";
        data[0][1] = "Student Id";
        data[0][2] = "Overall Grade";
        data[0][3] = "Curved Grade";
        for (int excelrow = 8, arrayRow = 1; excelrow <= curvedResults.get_Last_Row(getClassName()); excelrow++, arrayRow++){
            //fill table
            data[arrayRow][0] = (curvedResults.get_Student_First_Name(getClassName(), excelrow) + " " + curvedResults.get_Student_Last_Name(getClassName(), excelrow));
            data[arrayRow][1] = (curvedResults.get_Student_Id_Number(getClassName(), excelrow));
            data[arrayRow][2] = (curvedResults.get_Student_Overall_Grade(getClassName(), excelrow));
            data[arrayRow][3] = String.valueOf((curvedResults.get_Student_Curved_Grade(getClassName(), excelrow)));
        }

        fillGrid(data , withbuttons);
    }

    public void fillGrid(String[][] data, boolean withbuttons) {
        theGrid.getChildren().clear();
        theGrid.setAlignment(Pos.TOP_CENTER);
        rowQuantity = data.length;
        columnQuantity = data[0].length;
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                //create textfield
                TextField field = new TextField("0");
                field.setPrefHeight(Control.USE_COMPUTED_SIZE);
                field.setPrefWidth(Control.USE_COMPUTED_SIZE);
                field.setStyle("-fx-border-width: 5");
                field.setText(data[row][col]);

                //style cells appropriately
                field.setAlignment(Pos.CENTER);
                if (col < 4) {
                    field.setDisable(true);
                    field.setStyle("-fx-opacity: 1;");
                }
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

                //create listener for textfields
                if (col >3 && row > 0){
                    int finalRow1 = row;
                    int finalCol1 = col;
                    field.setOnAction(e->{
                        //check for integer
                        if (field.getText().matches("-?(0|[1-9]\\d*)")){
                            //change grade
                            sample.Grading grading = new sample.Grading(getUserEmail());

                            grading.update_The_Grade(getClassName(),Integer.parseInt(field.getText()), finalRow1+ 7, finalCol1+1);

                            grading.calculate_The_Overall_Grade(getClassName(),finalRow1+7);
                            //refill table
                            refreshTable();
                        } else {
                            //Not an integer
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input.", ButtonType.OK);
                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                            alert.showAndWait();

                            refreshTable();
                        }


                        //
                    });
                }




                //add to grid
                theGrid.add(field, col, row);

                if (row == data.length - 1 && col > 3 && withbuttons) {
                    Button button = new Button(" Edit ");
                    button.setMaxSize(100, 200);
                    button.getStyleClass().add("customTableButton");

                    int finalCol = col;
                    //add listeners to edit assignment Button
                    button.setOnAction(e ->{
                        try {
                            //open up the edit assignment page
                            FXMLLoader editAssignmentLoader = new FXMLLoader();
                            editAssignmentLoader.setLocation(getClass().getResource("EditAssignmentPage.fxml"));
                            Parent parent = editAssignmentLoader.load();
                            //assign the edit page controller
                            EditAssignmentPage editAssignmentPageController = editAssignmentLoader.getController();
                            //set variables using controller;
                            editAssignmentPageController.setColumnNumber(finalCol);
                            editAssignmentPageController.setCourseName(getClassName());
                            editAssignmentPageController.setUserEmail(getUserEmail());
                            editAssignmentPageController.fillInfo();
                            //Change window
                            Scene scene = new Scene(parent);
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setTitle("SmartGrader");
                            stage.setScene(scene);
                            stage.showAndWait();


                            Grading grading = new Grading(getUserEmail());
                            grading.recalculate_The_Grades_Of_All_Students(getClassName());
                            refreshTable();

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                    HBox hBox = new HBox();
                    HBox.setHgrow(button, Priority.ALWAYS);
                    hBox.getChildren().add(button);
                    hBox.setAlignment(Pos.TOP_CENTER);
                    theGrid.add(hBox, col, row + 1);
                }

                if (col == data[row].length - 1 && row > 0 && withbuttons) {
                    Button button = new Button("Edit Student");
                    button.getStyleClass().add("customTableButton");
                    button.setStyle("-fx-font-size: 12;");
                    theGrid.add(button, col + 1, row);
                    int finalRow = row;
                    button.setOnAction(e ->{

                        try {
                            //Load FXML for editStudentPage
                            FXMLLoader editStudentLoader = new FXMLLoader();
                            editStudentLoader.setLocation(getClass().getResource("EditStudentPage.fxml"));
                            Parent parent = editStudentLoader.load();
                            //assign edit student controller
                            EditStudentPage editStudentPageController = editStudentLoader.getController();
                            //assign variables
                            editStudentPageController.setCourseName(getClassName());
                            editStudentPageController.setOldStudentIDNumber(data[finalRow][2]);
                            editStudentPageController.setUserEmail(getUserEmail());
                            editStudentPageController.fillData(data[finalRow][0], data[finalRow][1], data[finalRow][3], finalRow + 7);

                            //Change window
                            Scene scene = new Scene(parent);
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setTitle("SmartGrader");
                            stage.setScene(scene);
                            stage.showAndWait();

                            refreshTable();

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                }

            }
        }

    }

    private void refreshTable(){
        //clear table
        theGrid.getChildren().clear();
        //reset the table
        LoadCourseData loadCourseData = new LoadCourseData(getUserEmail());
        String[][] updatedData = loadCourseData.get_2D_Array_Loaded_With_The_Course_Data(getClassName());
        fillGrid(updatedData, true);
    }

    public void clickedRefresh(ActionEvent actionEvent) {
        refreshTable();
    }

    public void saveEnteredDataAndUpdateFinalGrades() {
        Grading grading = new sample.Grading(getUserEmail());
        for (int row = 0; row < rowQuantity; row++) {
            for (int col = 0; col < columnQuantity; col++) {
                if (col > 3 && row > 0) {
                    TextField field = getNodeByRowColumnIndex(row, col, theGrid);
                    //check for integer
                    if (field.getText().matches("-?(0|[1-9]\\d*)") || field.getText().equals("")) {
                        //change grade
                        grading.update_The_Grade(getClassName(), Integer.parseInt(field.getText()), row + 7, col + 1);
                    } else {
                        //Not an integer
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input.", ButtonType.OK);
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                        alert.showAndWait();

                    }
                }
            }

            grading.calculate_The_Overall_Grade(getClassName(), row + 7);

        }

        //refill table
        refreshTable();
    }


    private TextField getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return (TextField) result;
    }
}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchClassOverviewPane {
    public Button ClearButton;
    public GridPane resultsTable;

    //variables for search button screen

    BorderPane mainView;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField IDField;
    @FXML
    private TextField DOBField;
    @FXML
    private String courseName, userName;



    //search class Methods
    public void clickedSearchStudent(ActionEvent event) {
        Search search = new Search(getUserName());

        String[][] data = search.get_Results_In_2D_Array(getCourseName(), firstNameField.getText(), lastNameField.getText(), IDField.getText(), DOBField.getText());

        fillTable(data);

    }

    //clear TextFields on search pane
    public void clickedClear(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        IDField.clear();
        DOBField.clear();

    }

    //clicked x, go back to main view
    public void xButtonClicked(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DefaultClassOverviewPane.fxml"));
            mainView.getChildren().clear();
            mainView.setCenter(loader.load());
            //load controller
            DefaultClassOverviewPane defaultClassOverviewPaneController = loader.getController();
            defaultClassOverviewPaneController.setClassName(getCourseName());
            defaultClassOverviewPaneController.setUserEmail(getUserName());
            //fill table and transfer it back
            LoadCourseData loadCourseData = new LoadCourseData(getUserName());
            String[][] data = loadCourseData.get_2D_Array_Loaded_With_The_Course_Data(getCourseName());
            defaultClassOverviewPaneController.fillGrid(data,true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void fillTable(String[][] data){
        resultsTable.getChildren().clear();
        resultsTable.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(resultsTable,Priority.ALWAYS);

        Label name = new Label("Name");
        Label IDNumber = new Label("ID");
        Label DOB = new Label("Date Of Birth");

        resultsTable.add(name , 0,0);
        resultsTable.add(IDNumber, 1,0);
        resultsTable.add(DOB, 2,0);


        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                //create textfield
                TextField field = new TextField();
                field.setPrefHeight(Control.USE_COMPUTED_SIZE);
                field.setPrefWidth(Control.USE_COMPUTED_SIZE);
                field.setStyle("-fx-border-width: 5");
                field.setText(data[row][col]);
                field.setDisable(true);
                field.setStyle("-fx-opacity: 1;");

                //style cells appropriately
                field.setAlignment(Pos.CENTER);
                if (row % 2 == 0 && row != 0) {
                    field.setStyle("-fx-background-color: white");
                } else if (row != 0) {
                    field.setStyle("-fx-background-color: lightgray");
                }

                if (col == data[row].length - 1){
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setPrefHeight(Control.USE_COMPUTED_SIZE);
                    hBox.setPrefWidth(Control.USE_COMPUTED_SIZE);

                    Button button = new Button("Student Profile");
                    button.getStyleClass().add("customTableButton");
                    int finalRow = row;
                    button.setOnAction(e ->{
                        try {
                            //Load FXML for editStudentPage
                            FXMLLoader studentInfoPaneloader = new FXMLLoader();
                            studentInfoPaneloader.setLocation(getClass().getResource("StudentInfoPane.fxml"));
                            mainView.getChildren().clear();
                            mainView.setCenter(studentInfoPaneloader.load());

                            //controller
                            //assign edit student controller
                            StudentInfoPane studentInfoPane = studentInfoPaneloader.getController();
                            //assign variables
                            studentInfoPane.setBorderPane(mainView);
                            studentInfoPane.setUserName(getUserName());
                            studentInfoPane.setCourseName(getCourseName());
                            studentInfoPane.setNameLabel(data[finalRow][0]);
                            studentInfoPane.setIDLabel(data[finalRow][1]);
                            studentInfoPane.setDOBLabel(data[finalRow][2]);
                            Search search = new Search(getUserName());
                            studentInfoPane.setClassChoiceBox(search.get_Names_Of_All_The_Courses_That_The_Student_Is_In_It_In_Array(getCourseName(), data[finalRow][1] ));
                            studentInfoPane.fillGrid(search.get_2D_Array_Loaded_With_The_Student_Data_For_The_Course(getCourseName(),data[finalRow][1]));

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                    hBox.getChildren().addAll(field,button);
                    //add to grid
                    resultsTable.add(hBox, col, row +1);

                } else {
                    //add to grid
                    resultsTable.add(field, col, row+1);
                }
            }
        }
    }





    public void setBorderPane(BorderPane mainView) {
        this.mainView = mainView;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

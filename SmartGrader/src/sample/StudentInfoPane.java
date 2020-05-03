package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class StudentInfoPane {

    private BorderPane mainView;
    public Label nameLabel;
    public Label DOBLabel;
    public Label IDLabel;
    public Button backButton;
    public ChoiceBox classChoiceBox;
    public GridPane assignmentGrid;
    public GridPane gradeGrid;
    private String courseName, userName;

    public void clickedBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchClassOverviewPane.fxml"));
            mainView.getChildren().clear();
            mainView.setCenter(loader.load());

            SearchClassOverviewPane searchClassOverviewPaneController = loader.getController();
            searchClassOverviewPaneController.setUserName(getUserName());
            searchClassOverviewPaneController.setCourseName(getCourseName());
            searchClassOverviewPaneController.setBorderPane(mainView);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillGrid(String[][] data){
        assignmentGrid.getChildren().clear();
        assignmentGrid.setAlignment(Pos.TOP_CENTER);
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                //create textfield
                TextField field = new TextField();
                field.setMinHeight(Control.USE_COMPUTED_SIZE);
                field.setMinWidth(Control.USE_COMPUTED_SIZE);
                field.setPrefSize(150, 30);
                field.setStyle("-fx-border-width: 5");
                field.setText(data[row][col]);
                field.setDisable(true);
                field.setStyle("-fx-opacity: 1");

                //style cells appropriately
                field.setAlignment(Pos.CENTER);
                if (row % 2 == 0) {
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
                assignmentGrid.add(field, col, row);
            }
        }
    }

    public void setBorderPane(BorderPane mainView) {
        this.mainView = mainView;
    }

    public void setNameLabel(String name) {
        this.nameLabel.setText(name);
    }

    public void setDOBLabel(String DOBLabel) {
        this.DOBLabel.setText(DOBLabel);
    }

    public void setIDLabel(String IDLabel) {
        this.IDLabel.setText(IDLabel);
    }

    public void setClassChoiceBox(String[] classes) {
        classChoiceBox.setValue(classes[0]);

        for (int i = 0; i < classes.length; i++){
            classChoiceBox.getItems().add(classes[i]);

        }
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

    public void clickedChoicebox(MouseEvent mouseEvent) {
        Search search = new Search(getUserName());
        String[][] data = search.get_2D_Array_Loaded_With_The_Student_Data_For_The_Course(classChoiceBox.getValue().toString(), IDLabel.getText());

        fillGrid(data);
    }

    public void clickedDelete(ActionEvent actionEvent) {
        try {
            Students students = new Students(getUserName());
            students.delete_Student(getCourseName(), IDLabel.getText());

            clickedBack(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

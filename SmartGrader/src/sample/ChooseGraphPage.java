package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseGraphPage {
    public ToggleGroup GradeSelectionToggle;
    public TextField DGradeMinTextfield;
    public TextField CGradeMinTextfield;
    public TextField BGradeMinTextfield;
    public TextField AGradeMinTextfield;
    public RadioButton totalGradeRadioButton;
    public RadioButton quizRadioButton;
    public RadioButton homeworkRadioButton;
    public RadioButton projectsRadioButton;
    public RadioButton examRadioButton;
    String userEmail, courseName;

    public void clickedPieChartButton(ActionEvent actionEvent) throws IOException {
        sample.Grading grading = new Grading(getUserEmail());
        int[] data = new int[0];
        String chartTitle = "";

        if(GradeSelectionToggle.getSelectedToggle() ==  totalGradeRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Overall_Grade(getCourseName());
            chartTitle = "Total Grade";
        } else if (GradeSelectionToggle.getSelectedToggle() ==  quizRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Quizes(getCourseName());
            chartTitle = "Quiz Grade";
        } else if(GradeSelectionToggle.getSelectedToggle() == homeworkRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Homeworks(getCourseName());
            chartTitle = "Homework Grade";
        } else if(GradeSelectionToggle.getSelectedToggle() == projectsRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Projects(getCourseName());
            chartTitle = "Project Grade";
        } else if (GradeSelectionToggle.getSelectedToggle() == examRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Exams(getCourseName());
            chartTitle = "Exam Grade";
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("A", data[0]),
                        new PieChart.Data("B", data[1]),
                        new PieChart.Data("C", data[2]),
                        new PieChart.Data("D", data[3]),
                        new PieChart.Data("F", data[4]));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle(chartTitle);
        chart.setLabelsVisible(true);
        chart.setLegendVisible(true);


        //point fxml loader
        FXMLLoader graphPopUpLoader = new FXMLLoader();
        graphPopUpLoader.setLocation(getClass().getResource("GraphPopup.fxml"));
        Parent root = graphPopUpLoader.load();
        //get controller
        GraphPopup graphPopupController = graphPopUpLoader.getController();
        graphPopupController.displayPieChart(chart);
        // Open Popup
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        clickedCancelButton(actionEvent);

    }

    public void clickedBarChartButton(ActionEvent actionEvent) throws IOException {
        Grading grading = new Grading(getUserEmail());
        int[] data = new int[0];
        String chartTitle = "";

        if(GradeSelectionToggle.getSelectedToggle() ==  totalGradeRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Overall_Grade(getCourseName());
            chartTitle = "Total Grade";
        } else if (GradeSelectionToggle.getSelectedToggle() ==  quizRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Quizes(getCourseName());
            chartTitle = "Quiz Grade";
        } else if(GradeSelectionToggle.getSelectedToggle() == homeworkRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Homeworks(getCourseName());
            chartTitle = "Homework Grade";
        } else if(GradeSelectionToggle.getSelectedToggle() == projectsRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Projects(getCourseName());
            chartTitle = "Project Grade";
        } else if (GradeSelectionToggle.getSelectedToggle() == examRadioButton){
            data = grading.get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Exams(getCourseName());
            chartTitle = "Exam Grade";
        }

        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Grade");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Students");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(chartTitle);

        dataSeries1.getData().add(new XYChart.Data("A", data[0]));
        dataSeries1.getData().add(new XYChart.Data("B", data[1]));
        dataSeries1.getData().add(new XYChart.Data("C", data[2]));
        dataSeries1.getData().add(new XYChart.Data("D", data[3]));
        dataSeries1.getData().add(new XYChart.Data("F", data[4]));
        barChart.getData().add(dataSeries1);

        //point fxml loader
        FXMLLoader graphPopUpLoader = new FXMLLoader();
        graphPopUpLoader.setLocation(getClass().getResource("GraphPopup.fxml"));
        Parent root = graphPopUpLoader.load();
        //get controller
        GraphPopup graphPopupController = graphPopUpLoader.getController();
        graphPopupController.displayBarChart(barChart);
        // Open Popup
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        clickedCancelButton(actionEvent);
    }

    public void clickedCancelButton(ActionEvent actionEvent) {
        //close popup window
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.close();
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void enteredA(ActionEvent actionEvent) {
        Grading grading = new Grading(getUserEmail());
        grading.update_Grade_Range(getCourseName(), "A", AGradeMinTextfield.getText());
    }

    public void enteredB(ActionEvent actionEvent) {
        Grading grading = new Grading(getUserEmail());
        grading.update_Grade_Range(getCourseName(), "B", BGradeMinTextfield.getText());
    }

    public void enteredC(ActionEvent actionEvent) {
        Grading grading = new Grading(getUserEmail());
        grading.update_Grade_Range(getCourseName(), "C", CGradeMinTextfield.getText());
    }

    public void enteredD(ActionEvent actionEvent) {
        Grading grading = new Grading(getUserEmail());
        grading.update_Grade_Range(getCourseName(), "D", DGradeMinTextfield.getText());
    }


}

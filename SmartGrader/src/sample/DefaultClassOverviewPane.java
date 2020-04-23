package sample;


import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class DefaultClassOverviewPane {

    public HBox buttonBox;
    public Button AddStudentButton;
    public Button AddAssignmentButton;
    public VBox mainBox;
    public SwingNode theNode;
    private String className, userEmail;

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
        createAndSetSwingContent();

    }

    public void createAndSetSwingContent() throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            //Set values for table
            String[] columnNames = {"First Name",
                    "Last Name",
                    "Cool",
                    "# of Years",
                    "Vegetarian"};

            Object[][] data = {
                    {"Kathy", "Smith",
                            "Snowboarding", new Integer(5), new Boolean(false)},
                    {"John", "Doe",
                            "Rowing", new Integer(3), new Boolean(true)},
                    {"Sue", "Black",
                            "Knitting", new Integer(2), new Boolean(false)},
                    {"Jane", "White",
                            "Speed reading", new Integer(20), new Boolean(true)},
                    {"Joe", "Brown",
                            "Pool", new Integer(10), new Boolean(false)}
            };

            //create table
            JTable jTable = new JTable(data, columnNames) {
                //cant edit cell
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

                //color table
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);

                    if (row % 2 == 0) {
                        c.setBackground(Color.white);
                    } else {
                        c.setBackground(Color.lightGray);
                    }

                    if (isCellSelected(row, column)) {
                        c.setBackground(Color.CYAN);
                    }

                    return c;
                }
            };

            jTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
            jTable.setFillsViewportHeight(true);

            JScrollPane jScrollPane = new JScrollPane(jTable);
            jScrollPane.revalidate();
            jScrollPane.repaint();

            theNode.setContent(jScrollPane);

            Platform.runLater(() -> theNode.requestFocus());
        });


    }


    private void fillTableTest() {

        /*jTable.setBackground(Color.GREEN);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(jScrollPane,BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
        swingNode.setContent(panel);
        swingNode.isResizable();*/

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
            stage.show();

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
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillCurveTableView(CurvedResults curvedResults) throws IOException {

        TableColumn FirstNameCol = new TableColumn("First Name");


        int columns = 2;
        int rows = 2;

        String[][] newArray = new String[columns][rows];
        newArray[0][0] = "France";
        newArray[0][1] = "Blue";

        newArray[1][0] = "Ireland";
        newArray[1][1] = "Green";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println(newArray[i][j]);
            }
        }


        //curvedResults.get_Last_Row(getClassName())
    /*    for (int i = 8, j = 0; i < 15; i++, j++) {//goes through each row
            //In Excel File, the data of the students starts from row 8, and for you the row starts at 0. let me know if this doesn't make sense
            //(curvedResults.get_Student_First_Name(getClassName(), i) + " " + curvedResults.get_Student_Last_Name(getClassName(), i));
            rows[1] = "2";//(curvedResults.get_Student_Id_Number(getClassName(), i));
            rows[2] = "8";//(curvedResults.get_Student_Overall_Grade(getClassName(), i));
            rows[3] = "43";//String.valueOf((curvedResults.get_Student_Curved_Grade(getClassName(), i)));
        }*/

    }

    public void fillDefaultTableView() {
    }

    public void printTable() throws PrinterException {
        try {
            JTable jTable = new JTable();
            boolean complete = jTable.print();
            if (complete) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Print Completed", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Printer Error", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }

    }
}

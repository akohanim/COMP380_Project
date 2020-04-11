package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class HomePage {

    @FXML
    private TilePane tilePane;
    @FXML
    private Button AddCourseButton;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button AccountButton;

    private String username;

    //initialize method lets you do setup for the window that is opening
    //all methods and listeners can be written in the initialize method
    @FXML
    public void initialize() throws IOException {
        //set Window to full screen
        /*
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());*/
    }

    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.setMaximized(true);
        window.show();
    }

    public void clickAdd(ActionEvent event) throws IOException {

        //open add course popup window to fill in data
        FXMLLoader AddCourseLoader = new FXMLLoader();
        AddCourseLoader.setLocation(getClass().getResource("AddCoursePage.fxml"));
        Parent root = AddCourseLoader.load();
        AddCoursePage addCourseController = AddCourseLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        //create tile and add to course list
        if (addCourseController.getCourseName() != "" && addCourseController.getSectionNumber() != "") {
            //createCourse
            CreateCourse createCourse = new CreateCourse(username, addCourseController.getCourseName(), addCourseController.getSectionNumber());

            if (createCourse.does_The_Course_Already_Exists()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Course already exists.", ButtonType.OK);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                alert.showAndWait();
            } else {
                fillClassTiles(username);
            }
        }
    }

    public void fillClassTiles(String user) throws IOException {
        //clear tilePane
        tilePane.getChildren().clear();

        //loadCourses
        LoadCourses loadCourses = new LoadCourses(user);

        //fill tiles
        for (int i = 0; i < loadCourses.get_Total_Number_Of_Courses(); i++) {
            //Create Tile
            FXMLLoader tileLoader = new FXMLLoader();
            tileLoader.setLocation(getClass().getResource("CourseTile.fxml"));
            Parent tile = tileLoader.load();
            //assign tileController
            CourseTile courseTileController = tileLoader.getController();
            //assign Name
            courseTileController.setClassNameLabel(loadCourses.get_Course_Name_For_Index(i));
            courseTileController.setClassNumberLabel(loadCourses.get_Course_Section_Number_For_Index(i));
            courseTileController.setUserName(getUsername());
            if (loadCourses.get_Course_Icon(loadCourses.get_Course_Name_For_Index(i)).equals("")) {
                //assign color
                courseTileController.setTileColor(loadCourses.get_Course_Color(loadCourses.get_Course_Name_For_Index((i))));
            } else {
                //assign Icon
                //only works for icons in the thumbnails directory!!!!
                courseTileController.setTileIcon(loadCourses.get_Course_Icon(loadCourses.get_Course_Name_For_Index(i)));
            }


            addCourseTile(tile);
            moveAddButtonToEnd();

            courseTileController.settingsButton.setOnAction(e -> {
                try {
                    FXMLLoader editTileLoader = new FXMLLoader();
                    editTileLoader.setLocation(getClass().getResource("EditTilePage.fxml"));
                    Parent root = editTileLoader.load();
                    EditTilePage editTilePageController = editTileLoader.getController();
                    editTilePageController.setUserName(username);
                    editTilePageController.setCourseName(courseTileController.getClassName());
                    System.out.println("Class Name:" + courseTileController.getClassName());
                    System.out.println("User Name:" + editTilePageController.getUserName());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

                    fillClassTiles(username);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            });
        }
    }

    public void addCourseTile(Node courseTile) {
        tilePane.getChildren().add(courseTile);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void moveAddButtonToEnd() {
        //puts the button at the end
        tilePane.getChildren().remove(AddCourseButton);
        tilePane.getChildren().add(AddCourseButton);
    }

    public void clickedLogout(ActionEvent event) throws IOException {
        //open Sign in page
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        oldStage.close();
    }

    public void clickedAccount(ActionEvent event) throws IOException {
        //open Account Settings popup window to fill in data
        FXMLLoader AddCourseLoader = new FXMLLoader();
        AddCourseLoader.setLocation(getClass().getResource("AccountSettingsPane.fxml"));
        Parent root = AddCourseLoader.load();
        AccountSettingsPane accountSettingsPaneController = AddCourseLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();


        if (!accountSettingsPaneController.getNewEmail().equals("") && !accountSettingsPaneController.getNewFirstName().equals("")
                && !accountSettingsPaneController.getNewLastName().equals("") && !accountSettingsPaneController.getNewPassword().equals("")) {
            //save new account settings
            SaveAccountSettings saveAccountSettings = new SaveAccountSettings(getUsername(), accountSettingsPaneController.getNewFirstName(),
                    accountSettingsPaneController.getNewLastName(), accountSettingsPaneController.getNewEmail(), accountSettingsPaneController.getNewPassword());
            //set new username as the username for this homepage
            setUsername(accountSettingsPaneController.getNewEmail());

            //let user know settings were changed
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account settings have been changed.", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();
        }
    }

}

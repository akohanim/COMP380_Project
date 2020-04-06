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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignIn {

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField PasswordTextField;



    @FXML
    public void logInClicked(ActionEvent event) {
        try {
            SignInUser signInUser = new SignInUser(EmailTextField.getText(), PasswordTextField.getText());
            if (signInUser.DoesUserExists()) {
                if (signInUser.DoesUserPasswordMatches()) {
                    System.out.println("Logged in");/*
                This means the user is registered and the password matches
                Take the user to the home page (the page that will have the courses listed
                */

                    LoadCourses loadCourses = new LoadCourses(EmailTextField.getText());
                    //Open up HomePage
                    FXMLLoader homePageLoader = new FXMLLoader();
                    homePageLoader.setLocation(getClass().getResource("HomePage.fxml"));
                    Parent parent = homePageLoader.load();
                    //assign homePageController
                    HomePage homePageController = homePageLoader.getController();
                    //set keep Email for use in next text field
                    homePageController.setUsername(EmailTextField.getText());
                    //Fill homepage with tiles
                    homePageController.fillClassTiles(EmailTextField.getText());
                    homePageController.moveAddButtonToEnd();
                    //Change window
                    //Change window
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();

                    //close sign in window
                    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stageTheEventSourceNodeBelongs.close();
                } else {
                    //This means the user exists, and since the first "if-statement" was false that means
                    //the user password did not match, so show a message that the password does not match
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Password", ButtonType.OK);
                    alert.showAndWait();
                }
            } else {
                //Show a message that the email is not registered
                Alert alert = new Alert(Alert.AlertType.ERROR, "User Not Found.", ButtonType.OK);

                alert.showAndWait();
            }
        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }

    public void clickedSignUp(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(parent);
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);

        stageTheEventSourceNodeBelongs.show();

    }

}

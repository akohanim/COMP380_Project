package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SignIn {

    @FXML
    private static Button SignInButton;

    @FXML
    private static Button SignUpButton;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField PasswordTextField;

    @FXML
    public void logInClicked(ActionEvent event) {
        try {
            SignInUser logInUser = new SignInUser(EmailTextField.getText(), PasswordTextField.getText());
            if (logInUser.DoesUserPasswordMatches()) {
                System.out.println("Logged in");/*
                This means the user is registered and the password matches
                Take the user to the home page (the page that will have the courses listed)
                TODO: My code takes each account and takes us to the general homepage
                TODO: The initialize method in the controller HomePage.java will need to load their classes
                */
                Parent parent = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                Scene scene = new Scene(parent);
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stageTheEventSourceNodeBelongs.setScene(scene);
                stageTheEventSourceNodeBelongs.show();

            } else if (logInUser.DoesUserExists()) {
                //This means the user exists, and since the first "if-statement" was false that means
                //the user password did not match, so show a message that the password does not match
                Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Password", ButtonType.OK);
                alert.showAndWait();
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

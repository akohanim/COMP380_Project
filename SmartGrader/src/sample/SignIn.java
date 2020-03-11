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
    public void LogInClicked(ActionEvent event) {
        try {
            SignInUser logInUser = new SignInUser(EmailTextField.getText(), PasswordTextField.getText());
            if (logInUser.DoesUserPasswordMatches() == true) {
                /*
                This means the user is registered and the password matches
                Write code that will take the user to the home page (the page that will have the courses listed)
                TODO
                */
            } else if (logInUser.DoesUserExists() == true) {
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

    public void ClickedSignUp(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Pane mypane = null;
        mypane = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(mypane);
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();

    }

}

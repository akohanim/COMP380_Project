package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp{

    @FXML
    private TextField FirstNameTextField;

    @FXML
    private TextField LastNameTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField PasswordTextField;

    public void clickedSignIn(ActionEvent event) throws IOException {
        //switch to the Sign In Window
        Parent parent = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(parent);
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }


    public void loginClicked(ActionEvent event) {
        //some input validation
        if (FirstNameTextField.getText().equals("") |LastNameTextField.getText().equals("")|EmailTextField.getText().equals("") |PasswordTextField.getText().equals("")  ) {
            //Alert for incorrect data
            Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Input", ButtonType.OK);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
            alert.showAndWait();

        }else {

            try {
                SignUpUser signUpUser = new SignUpUser(FirstNameTextField.getText(), LastNameTextField.getText(), EmailTextField.getText(), PasswordTextField.getText());

                if (signUpUser.does_User_Exists()) {
                    //Show a Message that the user already exists
                    Alert alert = new Alert(Alert.AlertType.ERROR, "User already exists.", ButtonType.OK);
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "User has been created", ButtonType.OK);
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("/TealTeam.css").toExternalForm());
                    alert.showAndWait();
                }

                clickedSignIn(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

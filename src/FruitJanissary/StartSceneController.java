package FruitJanissary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable {
    @FXML
    private Button LogIn;
    @FXML
    private Button SignUp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onLogInPressed(MouseEvent e){
        Parent root;
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("LogIn.fxml"));
            Scene logIn = new Scene(root);
            Main.primaryStage.setScene(logIn);
        }
        catch (IOException ex)
        {
            System.out.println("LogIn.fxml not found..");
        }
    }

    @FXML
    private void onSignUpPressed(MouseEvent e){
        Parent root;
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("SignUp.fxml"));
            Scene signUp = new Scene(root);
            Main.primaryStage.setScene(signUp);
        }
        catch (IOException ex)
        {
            System.out.println("SignUp.fxml not found..");
        }
    }
}

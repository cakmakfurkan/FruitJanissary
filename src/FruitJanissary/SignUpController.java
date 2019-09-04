package FruitJanissary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController extends Database{

    @FXML
    Button SignUp;
    @FXML
    TextField EMail;
    @FXML
    TextField Name;
    @FXML
    PasswordField Password;
    @FXML
    PasswordField RePassword;
    @FXML
    Label Info;

    //kaydol butonuna tıklanıldığında çalışcak
    //kontrol eksiklikleri şifre aynı mı?
    //email email mi?

    public void onSignUpPressed() throws Exception {
        Pattern p = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.]+\\.(org|gov||edu|com)");
        Matcher m = p.matcher(EMail.getCharacters().toString());
        if (m.find()) {
            if (getConnection("SELECT * FROM `userinfo` WHERE userMail LIKE '"+EMail.getCharacters().toString()+"'",0).first() == false) {
                if (Password.getCharacters().toString().compareTo(RePassword.getCharacters().toString()) == 0) {
                    String passwordMD5 = new MD5().getMd5(Password.getCharacters().toString());
                    getConnection("INSERT INTO `userinfo` (`userName`, `userMail`, `userPassword`) VALUES ('" + Name.getCharacters().toString() + "', '" + EMail.getCharacters().toString() + "', '" + passwordMD5 + "')", 1);
                    Info.setText("Sign Up successful");
                    Info.setTextFill(Color.GREEN);
                    Info.setVisible(true);
                } else {
                    Info.setText("Passwords doesn't match!");
                    Info.setTextFill(Color.RED);
                    Info.setVisible(true);
                }
            }else{
                    Info.setText("E-mail in use!");
                    Info.setTextFill(Color.RED);
                    Info.setVisible(true);
                }

        }else {
            Info.setText("EMail address don't usable");
            Info.setTextFill(Color.RED);
            Info.setVisible(true);
        }
    }

    public void onBackClicked(){
        Parent root;
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("StartScene.fxml"));
            Scene StartScene = new Scene(root);
            Main.primaryStage.setScene(StartScene);
        }
        catch (IOException ex)
        {
            System.out.println("StartScene.fxml not found..");
        }
    }

}



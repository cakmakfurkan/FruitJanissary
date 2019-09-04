package FruitJanissary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.ResultSet;

public class LogInController extends Database{

    //login ekranının kontrol kodları
    @FXML
    Button LogIn;
    @FXML
    TextField EMail;
    @FXML
    PasswordField Password;
    @FXML
    Label Info;

    public static int logId;                                            //giriş yapan kullanıcının id si
    public static String logName;                                       //giriş yapan kullanıcının adı
    public static String logMail;                                       //giriş yapan kullanıcının Maili

    //login butonuna basılınca çalışacak
    @FXML
    private void onLogInPressed(MouseEvent e) throws Exception {
        if(isLogIn()){
            Parent root;
            try
            {
                root = FXMLLoader.load(this.getClass().getResource("InGameStartScene.fxml"));
                Scene Game = new Scene(root);
                Main.primaryStage.setScene(Game);
            }
            catch (IOException ex)
            {
                System.out.println("Game.fxml not found..");
            }
        }else{
            Info.setText("EMail or Password is incorrect");
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
            System.out.println("StartScen.fxml not found..");
        }
    }
    //giriş sorgusu burda yapılacak
    private boolean isLogIn() throws Exception {
        String pass = MD5.getMd5(Password.getCharacters().toString());
        ResultSet resultSet = getConnection("SELECT * FROM `userinfo` WHERE userMail LIKE '"+EMail.getCharacters().toString()+"'  AND userPassword LIKE '"+pass+"'",0);
        assert resultSet != null;
        while(resultSet.next()) {                                                  //giriş yapan kullanıcının id sini ve ismini çekip public değişkene atadık
            logId = resultSet.getInt("id");
            logName = resultSet.getString("userName");
            logMail = resultSet.getString("userMail");
            System.out.println(logId);
            System.out.println(logMail);
            System.out.println(logName);
        }
        boolean isLogIn = resultSet.first();
        return isLogIn;
    }

}

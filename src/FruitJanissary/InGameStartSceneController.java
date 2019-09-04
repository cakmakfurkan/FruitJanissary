package FruitJanissary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class InGameStartSceneController {
    @FXML
    ImageView LogOutButton;
    @FXML
    ImageView StartButton;
    @FXML
    ImageView HighScoresButton;
    Parent root;

    public void onStartButtonClicked(){
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("Game.fxml"));
            Scene Game = new Scene(root);
            Main.primaryStage.setScene(Game);
        }
        catch (IOException ex)
        {
            System.out.println("Game.fxml not found..");
        }
    }

    public void onLogOutButtonClicked(){
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("StartScene.fxml"));
            Scene Game = new Scene(root);
            Main.primaryStage.setScene(Game);
            LogInController.logId = 0;
        }
        catch (IOException ex)
        {
            System.out.println("StartScene.fxml not found..");
        }
    }

    public void onHighScoresButtonClicked(){
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("HighScores.fxml"));
            Scene Game = new Scene(root);
            Main.primaryStage.setScene(Game);
        }
        catch (IOException ex)
        {
            System.out.println("HighScores.fxml not found..");
        }
    }

}

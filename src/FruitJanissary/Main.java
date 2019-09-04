package FruitJanissary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    //Her yerden erişip stage i değiştirebilmek için public tanımlıyoruz
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.show();
        //burda referansımızı tutuyoruz
        primaryStage = stage;

    }


    public static void main(String[] args) {
        launch(args);
    }
}

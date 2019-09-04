package FruitJanissary;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import static javafx.scene.control.TreeTableColumn.SortType.DESCENDING;


public class HighScoresController extends Database implements Initializable {
    @FXML
    TableView list;
    @FXML
    Button back;
    @FXML
    CheckBox ownHighScores;
    Parent root;

    @FXML
    private void onBackClicked(){
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("InGameStartScene.fxml"));
            Scene Game = new Scene(root);
            Main.primaryStage.setScene(Game);
        }
        catch (IOException ex)
        {
            System.out.println("InGameStartScene.fxml not found..");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {                    //load fonksiyonu

        try {
            TableColumn<highScore,String> nameCol = new TableColumn("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory("name"));
            TableColumn<highScore,String> emailCol = new TableColumn("E-mail");
            emailCol.setCellValueFactory(new PropertyValueFactory("email"));
            TableColumn<highScore,String> scoreCol = new TableColumn("Score");
            scoreCol.setCellValueFactory(new PropertyValueFactory("score"));
            TableColumn<highScore,String> timeCol = new TableColumn("Time");
            timeCol.setCellValueFactory(new PropertyValueFactory("time"));
            list.getColumns().addAll(nameCol,emailCol,scoreCol,timeCol);
            list.getItems().addAll(getHighScores("SELECT * FROM `high_scores`"));
            ownHighScores.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    try {
                        if(newValue){
                            list.getItems().clear();
                            list.getItems().addAll(getHighScores("SELECT * FROM `high_scores` WHERE userMail LIKE'"+LogInController.logMail+"'"));
                        }else if(newValue == false){
                            list.getItems().clear();
                            list.getItems().addAll(getHighScores("SELECT * FROM `high_scores`"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private ArrayList<highScore> getHighScores(String cs) throws Exception {
        ArrayList<highScore> highScores = new ArrayList<>();
        ResultSet resultSet = getConnection(cs,0);
        while(resultSet.next()) {
            highScore highScore = new highScore();
            highScore.name = resultSet.getString("userName");
            highScore.email = resultSet.getString("userMail");
            highScore.score = resultSet.getInt("userScore");
            highScore.time = resultSet.getInt("time");
            highScores.add(highScore);
        }
        Collections.sort(highScores);
        return highScores;
    }

    public class highScore implements Comparable{
        private String email;
        private String name;
        private int score;
        private int time;

        public String getEmail() {
            return email;
        }


        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }


        public int getTime() {
            return time;
        }

        @Override
        public int compareTo(Object o) {
            int compareScore=((highScore)o).getScore();
            return compareScore-this.score;
        }
    }
}



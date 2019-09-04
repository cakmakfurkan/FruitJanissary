/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitJanissary;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.util.Duration;

import static javafx.scene.paint.Color.*;


public class GameController extends Database implements Initializable {



    @FXML
    private Label Score;
    @FXML
    private Label cann;
    @FXML
    private ImageView stop;
    @FXML
    private ImageView play;
    @FXML
    private AnchorPane PauseScreen;
    @FXML
    private Label statusLabel;
    @FXML
    private AnchorPane GameScreen2;
    @FXML
    private Label time;
    private boolean isBombSliced = false;
    int seconds = 0;

    //Fruit
    private Banana bn = new Banana();
    private Apple ap = new Apple();
    private Watermelon wm = new Watermelon();
    private Orange or = new Orange();
    private Kiwi kw = new Kiwi();
    private Banana bn2 = new Banana();
    private Bomb bomb;
    private LinkedList<Fruit> fruits = new LinkedList<>();
    //Veri
    private int score=0;
    private int health=3;
    private double x = 3.5;
    private double y = 5.5;
    private double z = 4.6;
    private double t = 6.2;
    private double a = 3.9;
    private double b = 3.8;
    private double c = 3.8;

    //Timeline
    private Timeline startApple;
    private Timeline startBanana;
    private Timeline startWatermelon;
    private Timeline startOrange;
    private Timeline startKiwi;
    private Timeline startBanana2;
    private Timeline startBomb;
    private Timeline isDropFruit;
    private Timeline isAlive;
    private Timeline timeSet;
    private Path path;
    private Timeline timeLine;




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        GameScreen2.setOnDragDetected(dragDetected -> GameScreen2.startFullDrag());
        path = new Path();
        path.setStrokeWidth(5);
        path.setStroke(	LIGHTSKYBLUE);
        GameScreen2.setOnMouseDragged(mouseHandler);
        GameScreen2.setOnMouseClicked(mouseHandler);
        GameScreen2.setOnMouseEntered(mouseHandler);
        GameScreen2.setOnMouseExited(mouseHandler);
        GameScreen2.setOnMouseMoved(mouseHandler);
        GameScreen2.setOnMousePressed(mouseHandler);
        GameScreen2.setOnMouseReleased(mouseHandler);
        GameScreen2.getChildren().addAll(path);
        run();

    }

    @FXML
    public void retry(){
        Parent root;
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("Game.fxml"));
            Scene Game = new Scene(root);
            Main.primaryStage.setScene(Game);


        }
        catch (IOException ex)
        {
            System.out.println("Game.fxml not found.");
        }
    }

    @FXML
    public void back(){
        Parent root;
        try
        {
            root = FXMLLoader.load(this.getClass().getResource("InGameStartScene.fxml"));
            Scene Game = new Scene(root);
            Main.primaryStage.setScene(Game);

        }
        catch (IOException ex)
        {
            System.out.println("InGameStartScene.fxml not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Başlama Fonksiyonu

    private void run(){

        startBanana();
        startBanana2();
        startApple();
        startWatermelon();
        startOrange();
        startKiwi();
        startBomb();
        isAlive();
        isDropFruit();
        timeSet();

    }

    @FXML
    private void pause() {

        or.Throw.stop();
        startOrange.stop();
        kw.Throw.stop();
        startKiwi.stop();
        bomb.Throw.stop();
        startBomb.stop();
        bn2.Throw.stop();
        startBanana2.stop();
        bn.Throw.stop();
        startBanana.stop();
        ap.Throw.stop();
        startApple.stop();
        wm.Throw.stop();
        startWatermelon.stop();
        isAlive.stop();
        stop.setVisible(false);
        PauseScreen.setVisible(true);
        isDropFruit.stop();
        timeSet.stop();
    }

    @FXML
    private void play() {

        or.Throw.play();
        startOrange.play();
        kw.Throw.play();
        startKiwi.play();
        bomb.Throw.play();
        startBomb.play();
        bn2.Throw.play();
        startBanana2.play();
        bn.Throw.play();
        startBanana.play();
        ap.Throw.play();
        startApple.play();
        wm.Throw.play();
        startWatermelon.play();
        isAlive.play();
        stop.setVisible(true);
        PauseScreen.setVisible(false);
        isDropFruit.play();
        timeSet.play();
    }

    private void startApple(){



        startApple = new Timeline(new KeyFrame(Duration.seconds(x), e->{
            ap=new Apple(GameScreen2);
            ap.Throw(GameScreen2);
            fruits.add(this.ap);

            ap.Fruit.setOnMouseDragEntered(elma -> {

                ap.HalfFruitsMoves(GameScreen2);
                ap.Slice(GameScreen2);
                score+=8;
                Score.setText(String.valueOf(score));
                ap.Y=-300;
                ap.Fruit.setTranslateY(-5000);
                ap.Throw.stop();

            });
        }));
        startApple.setCycleCount(Timeline.INDEFINITE);
        startApple.play();
        fruitRandom();
    }

    private void startBanana(){


        startBanana = new Timeline(new KeyFrame(Duration.seconds(y), e->{

            bn=new Banana(GameScreen2);
            bn.Throw(GameScreen2);
            fruits.add(this.bn);

            bn.Fruit.setOnMouseDragEntered(event -> {
                bn.HalfFruitsMoves(GameScreen2);
                bn.Slice(GameScreen2);
                score+=10;
                Score.setText(String.valueOf(score));
                bn.Y = -300;
                bn.Fruit.setTranslateY(-5000);
                bn.Throw.stop();
            });

        }));
        startBanana.setCycleCount(Timeline.INDEFINITE);
        startBanana.play();
        fruitRandom();
    }
    private void fruitRandom(){
        Timeline timeLine = new Timeline(
                new KeyFrame(Duration.millis(300), e -> {
                    x = ThreadLocalRandom.current().nextDouble(3,5);
                    y = ThreadLocalRandom.current().nextDouble(3,5);
                    z = ThreadLocalRandom.current().nextDouble(3,5);
                    t = ThreadLocalRandom.current().nextDouble(3,5);
                    a = ThreadLocalRandom.current().nextDouble(3,5);
                    b = ThreadLocalRandom.current().nextDouble(3,5);
                    c = ThreadLocalRandom.current().nextDouble(3,5);
                }));
        timeLine.setCycleCount(1);
        timeLine.play();
    }

    private void startWatermelon(){


        startWatermelon = new Timeline(new KeyFrame(Duration.seconds(z), e->{
            wm=new Watermelon(GameScreen2);
            wm.Throw(GameScreen2);
            fruits.add(this.wm);

            wm.Fruit.setOnMouseDragEntered(event -> {

                        wm.HalfFruitsMoves(GameScreen2);
                        wm.Slice(GameScreen2);
                        score+=15;
                        Score.setText(String.valueOf(score));
                        wm.Y=-300;
                        wm.Fruit.setTranslateY(-5000);
                        wm.Throw.stop();
                    });

        }));
        startWatermelon.setCycleCount(Timeline.INDEFINITE);
        startWatermelon.play();
    }

    private void startOrange(){



        startOrange = new Timeline(new KeyFrame(Duration.seconds(t), e->{
            or=new Orange(GameScreen2);
            or.Throw(GameScreen2);
            fruits.add(this.or);

            or.Fruit.setOnMouseDragEntered(event -> {

                        or.HalfFruitsMoves(GameScreen2);
                        or.Slice(GameScreen2);
                        score+=7;
                        Score.setText(String.valueOf(score));
                        or.Y=-300;
                        or.Fruit.setTranslateY(-5000);
                        or.Throw.stop();
                    });

        }));
        startOrange.setCycleCount(Timeline.INDEFINITE);
        startOrange.play();

    }

    private void startKiwi() {
        startKiwi = new Timeline(new KeyFrame(Duration.seconds(a), e->{

            kw = new Kiwi(GameScreen2);
            kw.Throw(GameScreen2);
            fruits.add(this.kw);

            kw.Fruit.setOnMouseDragEntered(event -> {

                        kw.HalfFruitsMoves(GameScreen2);
                        kw.Slice(GameScreen2);
                        score+=5;
                        Score.setText(String.valueOf(score));
                        kw.Y=-300;
                        kw.Fruit.setTranslateY(-5000);
                        kw.Throw.stop();
                    });

        }));
        startKiwi.setCycleCount(Timeline.INDEFINITE);
        startKiwi.play();

    }

    private void startBanana2(){


        startBanana2 = new Timeline(new KeyFrame(Duration.seconds(b), e->{

            bn2=new Banana(GameScreen2);
            bn2.Throw(GameScreen2);
            fruits.add(this.bn2);

            bn2.Fruit.setOnMouseDragEntered(event -> {

                bn2.HalfFruitsMoves(GameScreen2);
                bn2.Slice(GameScreen2);
                score+=10;
                Score.setText(String.valueOf(score));
                bn2.Y = -350;
                bn2.Fruit.setTranslateY(-5000);
                bn2.Throw.stop();
            });

        }));
        startBanana2.setCycleCount(Timeline.INDEFINITE);
        startBanana2.play();

    }

    private void startBomb(){

        startBomb = new Timeline(new KeyFrame(Duration.seconds(c), e->{

            bomb=new Bomb();
            bomb.makeBomb(GameScreen2);
            bomb.Throw(GameScreen2);

            bomb.Bomb.setOnMouseDragEntered(dragEntered ->
            {
                bomb.Slice(GameScreen2);
                bomb.Bomb.setVisible(false);
                FadeTransition ft = new FadeTransition(Duration.millis(300), GameScreen2);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.setCycleCount(1);
                ft.setAutoReverse(true);
                ft.play();
                isBombSliced = true;
            });

        }));

        startBomb.setCycleCount(Timeline.INDEFINITE);
        startBomb.play();

    }

    private void isDropFruit(){
        isDropFruit = new Timeline(new KeyFrame(Duration.millis(10), e-> {
            for(int i = 0 ; i < fruits.size() ; i++){
                if (fruits.get(i).Fruit.getTranslateY()<-3000){
                    GameScreen2.getChildren().remove(fruits.get(i).Fruit);
                    fruits.remove(i);
                }else if(fruits.get(i).Fruit.getTranslateY()>75){
                    //if (fruits.get(i).Fruit.getTranslateY()<-4500)
                    GameScreen2.getChildren().remove(fruits.get(i).Fruit);
                    fruits.remove(i);

                    health = health - 1;
                    cann.setText(String.valueOf(health));
                }
            }
        }));
        isDropFruit.setCycleCount(Timeline.INDEFINITE);
        isDropFruit.play();
    }

    private void isAlive(){

        isAlive = new Timeline(new KeyFrame(Duration.millis(50), e->{


            if(health<=0 || isBombSliced){
                pause();
                PauseScreen.setVisible(true);
                statusLabel.setText("Game Over");
                play.setVisible(false);
                try {
                    saveScore();                //skoru kaydediyoruz
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Life 0");

            }

        }));
        isAlive.setCycleCount(Timeline.INDEFINITE);
        isAlive.play();
    }

    private void saveScore() throws Exception {
        getConnection("INSERT INTO `high_scores` (`userName`, `userMail`, `userScore`,`time`) VALUES ('"+LogInController.logName+"', '"+LogInController.logMail+"', '"+score+"','"+seconds+"')",1);
    }


    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                path.getElements()
                        .add(new MoveTo(mouseEvent.getX(), mouseEvent.getY()));
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                path.getElements()
                        .add(new LineTo(mouseEvent.getX(), mouseEvent.getY()));
                }else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                timeLine = new Timeline(
                        new KeyFrame(Duration.millis(300), e -> { path.getElements().clear(); }));
                timeLine.setCycleCount(1);
                timeLine.play();
            }
            }
    };

    private void timeSet(){

        timeSet = new Timeline(new KeyFrame(Duration.seconds(1), e->{
            seconds++;
            time.setText(seconds+" Seconds");
        }));
        timeSet.setCycleCount(Timeline.INDEFINITE);
        timeSet.play();
    }


}





















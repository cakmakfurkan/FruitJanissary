package FruitJanissary;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class Bomb implements Sliceable{
    // Resimler
    public ImageView Bomb;
    public  int X=0,Y=500,Z=0;

    public int artışmiktarıY=2,artışmiktarıX=1;
    Timeline Throw, showExplosion;


    public void makeBomb(AnchorPane OyunEkranı){

        X= ThreadLocalRandom.current().nextInt(0,500);
        this.Bomb = new ImageView("Images/bomb.gif");
        this.Bomb.setVisible(false);
        this.Bomb.setFitHeight(70);
        this.Bomb.setFitWidth(80);
        this.Bomb.setLayoutX(X);
        this.Bomb.setLayoutY(Y);
        OyunEkranı.getChildren().add(Bomb);


    }

    public void Throw(AnchorPane OyunEkranı)
    {
        this.Bomb.setVisible(true);
        Throw = new Timeline(new KeyFrame(Duration.millis(10), e->{

            this.Bomb.setRotate(Z);
            this.Bomb.setLayoutX(X);
            this.Bomb.setLayoutY(Y);
            X+=artışmiktarıX;
            Y-=artışmiktarıY;
            Z++;
            Z++;
            if(Y==150){
                artışmiktarıX=1;
                artışmiktarıY=-2;
            }

        }));
        Throw.setCycleCount(Timeline.INDEFINITE);
        Throw.play();

    }

    public void Slice(AnchorPane pane)
    {

        ImageView trace = new ImageView("Images/bomb_explosion.gif");

        trace.setLayoutX(this.Bomb.getLayoutX() - 37);
        trace.setLayoutY(this.Bomb.getLayoutY());
        pane.getChildren().add(trace);

        showExplosion = new Timeline(
                new KeyFrame(Duration.ZERO, e -> { trace.setVisible(true); }),
                new KeyFrame(Duration.millis(400), e-> { trace.setVisible(false); }));
        showExplosion.setCycleCount(1);
        showExplosion.play();
    }


}

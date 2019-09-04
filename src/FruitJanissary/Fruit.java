
package FruitJanissary;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Fruit implements Sliceable{

    public ImageView Fruit;
    public ImageView HalfFruit;
    public ImageView HalfFruit2;
    public  int X=0,Z=0;
    public double Y=500;
    Timeline Throw,SlashAnimation,Slash;
    private  double velocity;
    private double gravity = 0.3;

    public void MakeFruit(AnchorPane OyunEkranı, ImageView FullImg, ImageView HalfImg1, ImageView HalfImg2){
        X= ThreadLocalRandom.current().nextInt(0,800);
        velocity = ThreadLocalRandom.current().nextInt(-16,-14);
        this.Fruit = FullImg;
        this.Fruit.setVisible(false);
        this.Fruit.setFitHeight(70);
        this.Fruit.setFitWidth(70);
        this.Fruit.setLayoutX(X);
        this.Fruit.setLayoutY(Y);
        OyunEkranı.getChildren().add(Fruit);
        HalfFruit = HalfImg1;
        HalfFruit2 = HalfImg2;

    }


    public void Throw(AnchorPane OyunEkranı)
    {
        this.Fruit.setVisible(true);
        Throw = new Timeline(new KeyFrame(Duration.millis(30), e->{
        this.Fruit.setRotate(Z);
        this.Fruit.setLayoutX(X);
        this.Fruit.setLayoutY(Y);
            Z++;
            Z++;
        this.velocity += this.gravity;
        this.Fruit.setTranslateY(this.Fruit.getTranslateY() + velocity);
        if(this.X<300)
            this.Fruit.setTranslateX(this.Fruit.getTranslateX() + 5);
        else
            this.Fruit.setTranslateX(this.Fruit.getTranslateX() - 5);
    }));
        Throw.setCycleCount(Timeline.INDEFINITE);
        Throw.play();
    
    }
    
    public void HalfFruitsMoves(AnchorPane anchorPane)
    {

        HalfFruit.setRotate(-167.5);
        HalfFruit2.setRotate(33.7);

        HalfFruit.setLayoutX(this.Fruit.getTranslateX() + 20 + X);
        HalfFruit.setLayoutY(Fruit.getTranslateY() + 484);
        
        HalfFruit2.setLayoutX(this.Fruit.getTranslateX() - 14 + X);
        HalfFruit2.setLayoutY(Fruit.getTranslateY() + 527);
        
        HalfFruit.setFitWidth(70);
        HalfFruit.setFitHeight(70);
        
        HalfFruit2.setFitWidth(70);
        HalfFruit2.setFitHeight(70);

        anchorPane.getChildren().add(HalfFruit);
        anchorPane.getChildren().add(HalfFruit2);

        this.Fruit.setVisible(false);
        HalfFruit.setVisible(true);
        HalfFruit2.setVisible(true);

        SlashAnimation = new Timeline(new KeyFrame(Duration.millis(5), e ->
        {
            SlashAnimation(this.HalfFruit, this.HalfFruit2);
        }));
        
        SlashAnimation.setCycleCount(Timeline.INDEFINITE);
        SlashAnimation.play();
    }
    
    public void SlashAnimation(ImageView HalfFruit, ImageView HalfFruit2)
    {

        HalfFruit.setRotate(HalfFruit.getRotate() + 1);
        HalfFruit2.setRotate(HalfFruit2.getRotate() + 1);
        
        HalfFruit.setX(HalfFruit.getX() + 1);
        HalfFruit.setY(HalfFruit.getY() + 1);
        
        HalfFruit2.setX(HalfFruit2.getX() - 1);
        HalfFruit2.setY(HalfFruit2.getY() + 1);
    }

       public void Slice(AnchorPane pane)
    {
       
        ImageView slash = new ImageView("Images/splash.png");
        slash.setFitHeight(70);
        slash.setFitWidth(70);
        slash.setLayoutX(this.Fruit.getTranslateX() - 37 + X);
        slash.setLayoutY(Fruit.getTranslateY() + 500);

        pane.getChildren().add(slash);

        Slash = new Timeline(
                new KeyFrame(Duration.ZERO, e -> { slash.setVisible(true); }),
                new KeyFrame(Duration.millis(500), e-> { slash.setVisible(false); })
        );
        Slash.setCycleCount(1);
        Slash.play();
    }
    
}

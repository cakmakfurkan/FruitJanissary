package FruitJanissary;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Apple extends FruitJanissary.Fruit implements Sliceable {


    private ImageView FullImg = new ImageView("Images/apple.png");
    private ImageView HalfImg1 = new ImageView("Images/apple_half1.png");
    private ImageView HalfImg2 = new ImageView("Images/apple_half2.png");

    public  Apple(){

    }

    public Apple(AnchorPane OyunEkranı){
        MakeFruit(OyunEkranı,FullImg,HalfImg1,HalfImg2);
    }
    
}
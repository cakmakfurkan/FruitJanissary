package FruitJanissary;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Watermelon extends FruitJanissary.Fruit implements Sliceable{


    private ImageView FullImg = new ImageView("Images/watermelon.png");
    private ImageView HalfImg1 = new ImageView("Images/watermelon_half.png");
    private ImageView HalfImg2 = new ImageView("Images/watermelon_half.png");

    public  Watermelon(){

    }

    public Watermelon(AnchorPane OyunEkranı){
        MakeFruit(OyunEkranı,FullImg,HalfImg1,HalfImg2);
    }


}

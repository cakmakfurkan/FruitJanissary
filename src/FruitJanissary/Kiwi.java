package FruitJanissary;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Kiwi extends FruitJanissary.Fruit implements Sliceable {
    

    private ImageView FullImg = new ImageView("Images/kiwi.png");
    private ImageView HalfImg1 = new ImageView("Images/kiwi_half1.png");
    private ImageView HalfImg2 = new ImageView("Images/kiwi_half2.png");

    public  Kiwi(){

    }

    public Kiwi(AnchorPane OyunEkranı){
        MakeFruit(OyunEkranı,FullImg,HalfImg1,HalfImg2);
    }
    
}
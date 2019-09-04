package FruitJanissary;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Banana extends FruitJanissary.Fruit implements Sliceable{

    private ImageView FullImg = new ImageView("Images/banana.png");
    private ImageView HalfImg1 = new ImageView("Images/banana_half.png");
    private ImageView HalfImg2 = new ImageView("Images/banana_half.png");

    public  Banana(){

    }

    public Banana(AnchorPane OyunEkranı){MakeFruit(OyunEkranı,FullImg,HalfImg1,HalfImg2);
    }


}

    

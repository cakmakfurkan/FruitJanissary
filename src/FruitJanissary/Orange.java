
package FruitJanissary;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Orange extends FruitJanissary.Fruit {

    private ImageView FullImg = new ImageView("Images/orange.png");
    private ImageView HalfImg1 = new ImageView("Images/orange_half.png");
    private ImageView HalfImg2 = new ImageView("Images/orange_half.png");

    public Orange(){

    }

    public Orange(AnchorPane OyunEkranı){
        MakeFruit(OyunEkranı,FullImg,HalfImg1,HalfImg2);
    }

}
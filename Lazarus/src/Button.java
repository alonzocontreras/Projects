import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Created by tmytrn on 12/10/17.
 */
public class Button extends GameObject {

    public Button(int x, int y, BufferedImage image, int dimensionW, int dimensionL) {
        super(x, y, image, dimensionW, dimensionL, 0);

    }

    public void update(Observable o, Object obj) {}
    public void collide(Boxes Box) {}
    public void collide(GameObject object) {}
    public void collide(Player player) {}
    public void collide(Walls wall) {}
}

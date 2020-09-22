import java.util.Observable;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Image;

/**
 * Created by alonzocontreras on 12/2/17.
 */

public class Walls extends GameObject {

    public Walls(int x, int y, BufferedImage wallImg, int dimensionW, int dimensionL) {
        super(x, y, wallImg, dimensionW, dimensionL, 0);

    }

    public void update(Observable o, Object obj) {

    }

    public void collide(Boxes box) {

    }

    public void collide(GameObject object) {

    }


    public void collide(Player player) {

    }

    public void collide(Walls wall) {

    }

}

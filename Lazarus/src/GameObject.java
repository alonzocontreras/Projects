/**
 * Created by alonzocontreras on 12/2/17.
 */

import java.awt.Point;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.*;

public abstract class GameObject extends Observable implements Observer {
    protected int x, y, dimensionW, dimensionL, speed;
    boolean isVisible, isSolid;
    BufferedImage image;
    int imageIndex = 0;

    public GameObject() {
    }

    public GameObject(int x, int y, BufferedImage image, int dimensionW, int dimensionL, int speed) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.dimensionL = dimensionL;
        this.dimensionW = dimensionW;
        this.speed = speed;
        this.isVisible = true;
        this.isSolid = true;
    }

    public BufferedImage getImage() {
        return this.image;
    }


    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.dimensionW, this.dimensionL);
    }
    public boolean getIsVisible(){
        return this.isVisible;
    }
    public boolean getIsSolid(){
        return this.isSolid;
    }

    public abstract void collide(GameObject object);

    public abstract void collide(Player player);

    public abstract void collide(Boxes box);

    public abstract void collide(Walls wall);

    public boolean hasBoxOntop(){
        return false;
    }
}

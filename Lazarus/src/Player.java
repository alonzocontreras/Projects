/**
 * Created by alonzocontreras on 12/2/17.
 */

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.nio.Buffer;
import java.util.*;
import java.awt.Rectangle;
import java.awt.Point;

public class Player extends GameObject {
    private Controller controls;
    private int safeX, safeY;
    private int moveCount = 1;
    private boolean collisionDetected;
    private boolean isVisible = false;
    private boolean win = false;

    public Player(int x, int y, BufferedImage image, int dimensionW, int dimensionL) {
        super(x, y, image, dimensionW, dimensionL, 3);
        this.isVisible = true;
        collisionDetected = false;
        saveNonCollidingCoordinates();
        controls = new Controller(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    }

    @Override
    public void update(Observable o, Object obj) {
        if (!collisionDetected) {
            saveNonCollidingCoordinates();
        } else {
            collisionDetected = false;
        }
    }

    private void saveNonCollidingCoordinates() {
        safeX = this.x;
        safeY = this.y;
    }

    public void collide(GameObject object) {
        collisionDetected = true;
        if (object instanceof Button){
            setPlayerWin();
        } else if (!object.hasBoxOntop()) {
            setY(safeY - 40);
        } else {
            setY(safeY);
            setX(safeX);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 40, 40);
    }

    public void collide(Player player) {}

    public void collide(Boxes box) {}

    public void collide(Walls wall) {
        collisionDetected = true;
        setX(safeX);
        setY(safeY);
    }

    public void mvLeft() {
        if (getX() > 80 && getY() > 200){
            setX(getX() - 40);
        }
        if (getY() <= 200 && getX() > 0){
            setX(getX() - 40);
        }
    }

    public void mvRight() {
        if (getX() < 520 && getY() > 200)
            setX((getX() + 40));
        if (getY() <= 200 && getX() < 600){
            setX(getX() + 40);
        }
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public void setMoveCount(int newCount) {
        this.moveCount = newCount;
    }

    public void getDeathAnimation() {
        isVisible = false;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setPlayerWin(){
        win = true;
    }

    public boolean getWin(){
        return win;
    }

    public void moveDown(){
        setY(this.y + 40);
    }
}

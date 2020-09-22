import java.awt.*;
import java.util.Observable;
import java.awt.image.BufferedImage;


public class Boxes extends GameObject {
    /**
     * There are four types of Boxes (from heaviest to lightest:
     * STONE, METAL, WOOD, CARDBOARD
     * <p>
     * the only difference among the boxes are the image and weight
     */
    private boolean isMoving;
    private int weight;
    private boolean ontop;
    private boolean soundFX;

    public Boxes(int x, int y, BufferedImage image, int dimensionW, int dimensionL, int weight) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.dimensionL = dimensionL;
        this.dimensionW = dimensionW;
        this.weight = weight;
        this.isMoving = true;
        this.isVisible = true;
        this.isSolid = true;
        this.ontop = false;
        this.soundFX = false;
    }

    public void update(Observable o, Object obj) {
        // while isMoving is true, the box will keep falling
        //write code to check for the weight of the box.
        if (getIsMoving()) {
            this.setCoordinates(x, y + 2);
        }
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

    public void collide(GameObject object) {
        //stop moving when box hits another box.
        this.isMoving = false;
        this.setCoordinates(this.x, this.y);
    }

    public void collide(Boxes box) {
        //check weights, if the current box is heavier, destroy box below.
        //else, stop
        if (this.weight > box.weight) {
            box.destroy();
            setSoundFX(true);
        } else {
            this.setCoordinates(this.x, box.getY() - 40);
            this.isMoving = false;
            setSoundFX(false);
        }
    }

    public void collide(Player player) {
        //collision below the box with player

        if (player.getY() - 40 < this.getY()) {
            this.setCoordinates(this.x, player.getY() - 40);
            this.isMoving = false;
        }
    }

    public void collide(Walls wall) {
        //if box collides with wall, y coordinate is constant.
        //stop the box from moving.
        this.isMoving = false;
        this.setCoordinates(this.x, wall.getY() - 40);
    }

    public boolean getIsMoving() {
        return this.isMoving;
    }

    public void destroy(){
        this.setIsVisible(false);
        this.setIsSolid(false);
    }

    public void setIsMoving(boolean b) {
        this.isMoving = b;
    }

    public boolean hasBoxOntop() {
        return ontop;
    }

    public void setIsVisible(boolean b){
        this.isVisible = b;
    }

    public void setIsSolid(boolean b){
        this.isSolid = b;
    }

    public void setBoxOntop(boolean b) {
        ontop = b;
    }

    public void setSoundFX(boolean b){
        soundFX = b;
    }

    public boolean getSoundFX(){
        return soundFX;
    }
}

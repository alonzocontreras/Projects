/**
 * Created by alonzocontreras on 12/2/17.
 */

import java.util.HashSet;
import java.util.Set;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class Controller extends Observable implements KeyListener {
    private Set<Integer> keysPressed;
    private int keyLeft, keyRight;
    private boolean[] keys;

    public Controller(int left, int right) {
        keysPressed = new HashSet<>();
        this.keyLeft = left;
        this.keyRight = right;
    }

    @Override
    public synchronized void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        keysPressed.add(key);
        setChanged();
        notifyObservers();
    }

    @Override
    public synchronized void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        keysPressed.remove(key);
        setChanged();
        notifyObservers();
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    public Set getKeysPressed() {
        return this.keysPressed;
    }

    public int getKeyLeft() {
        return this.keyLeft;
    }

    public int getKeyRight() {
        return this.keyRight;
    }
}

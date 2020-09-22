/**
 * Created by alonzocontreras on 12/2/17.
 */

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class GameClock extends Observable implements ActionListener {
    Timer timer;

    public GameClock() {
        timer = new Timer(15, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        setChanged();
        notifyObservers();
    }
}

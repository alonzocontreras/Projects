/**
 * Created by alonzocontreras on 12/2/17.
 */

import javax.swing.*;

class GUI extends JFrame {


    JPanel backgroundLabel;

    public GUI() {
        initUI();
    }

    private void initUI() {
        add(new GameWorld());
        pack();
        setTitle("Lazarus Pit");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

}

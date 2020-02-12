package Main;

import javax.swing.JFrame;

public class Game extends JFrame {
    Game () {
        // JFrame initialization
        setTitle("Dungeon Game");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void run() {
        this.setContentPane(new GamePanel());
        this.setVisible(true);
    }
}
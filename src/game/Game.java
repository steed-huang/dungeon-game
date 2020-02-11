package game;

import javax.swing.JFrame;

public class Game extends JFrame {
    Game () {
        setTitle("Dungeon Game");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void run() {
        // main game loop
    }
}
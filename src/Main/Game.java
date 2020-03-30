package Main;


import javax.swing.JFrame;

public class Game extends JFrame {
    Game () {
        // JFrame initialization
        setTitle("Dungeon Game");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void run() {
        this.setContentPane(new GamePanel());
        this.setVisible(true);
    }
}
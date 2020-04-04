package Main;

import Handler.Keys;
import Handler.Mouse;
import GameState.GameStateManager;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    // dimensions
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int SCALE = 1;

    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    // canvas image
    private BufferedImage image;
    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            addMouseListener(this);
            addMouseMotionListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        running = true;
        gsm = new GameStateManager();
    }

    public void run(){
        init();

        long start;
        long elapsed;
        long wait;

        // game loop
        while(running) {
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if(wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        gsm.update();
        Keys.update();
        Mouse.update();
    }

    private void draw() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE,null);
        g2.dispose();
    }

    // keyboard
    public void keyTyped(KeyEvent key) {}

    public void keyPressed(KeyEvent key) {
        Keys.keySet(key.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent key) {
        Keys.keySet(key.getKeyCode(), false);
    }

    // mouse
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) { Mouse.set(true, e.getX(), e.getY());}

    public void mouseReleased(MouseEvent e) { Mouse.set(false, e.getX(), e.getY()); }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) { Mouse.updatePos(e.getX(), e.getY()); }

    public void mouseMoved(MouseEvent e) {}
}

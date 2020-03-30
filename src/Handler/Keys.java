package Handler;

import java.awt.event.KeyEvent;

public class Keys {

    public static final int NUM_KEYS = 7;

    public static boolean[] keyState = new boolean[NUM_KEYS];
    public static boolean[] prevKeyState = new boolean[NUM_KEYS];

    public static int W = 0;
    public static int A = 1;
    public static int S = 2;
    public static int D = 3;
    public static int SPACE = 4;
    public static int ENTER = 5;
    public static int ESCAPE = 6;

    public static void keySet(int i, boolean b) {
        if(i == KeyEvent.VK_W) keyState[W] = b;
        else if(i == KeyEvent.VK_A) keyState[A] = b;
        else if(i == KeyEvent.VK_S) keyState[S] = b;
        else if(i == KeyEvent.VK_D) keyState[D] = b;
        else if(i == KeyEvent.VK_SPACE) keyState[SPACE] = b;
        else if(i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
        else if(i == KeyEvent.VK_ESCAPE) keyState[ESCAPE] = b;
    }

    public static void update() { System.arraycopy(keyState, 0, prevKeyState, 0, NUM_KEYS); }

    public static boolean isPressed(int i) { return keyState[i] && !prevKeyState[i]; }

    public static boolean isHeld(int i) {return keyState[i]; }
}
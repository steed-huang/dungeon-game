package Handler;

import java.awt.event.KeyEvent;

public class Mouse {
    public static boolean mouseState;
    public static boolean prevMouseState;

    public static int mouse_x;
    public static int mouse_y;

    public static void set(boolean b, int x, int y) {
        mouseState = b;
        mouse_x = x;
        mouse_y = y;
    }

    public static void update() {
        prevMouseState = mouseState;
    }

    public static int getMouse_x() { return mouse_x; }
    public static int getMouse_y() { return mouse_y; }

    public static boolean isPressed() { return mouseState && !prevMouseState; }

    public static boolean isHeld() {return mouseState; }
}

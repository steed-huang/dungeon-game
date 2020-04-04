package World;

import java.awt.image.BufferedImage;

public class Layout {
    public int[][] grid;
    public BufferedImage sprite;
    public BufferedImage door_hor;
    public BufferedImage door_ver;

    public Layout(int[][] g, BufferedImage s, BufferedImage dh, BufferedImage dv) {
        grid = g;
        sprite = s;
        door_hor = dh;
        door_ver = dv;
    }
}
package World;

import java.awt.image.BufferedImage;

public class Layout {
    public int[][] grid;
    public BufferedImage sprite;

    public Layout(int[][] g, BufferedImage s) {
        grid = g;
        sprite = s;
    }
}
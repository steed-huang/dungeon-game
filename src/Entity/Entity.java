package Entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    // sprite
    protected BufferedImage sprite;

    // positioning and speed
    protected double x;
    protected double y;
    protected double room_x;
    protected double room_y;
    protected int width;
    protected int height;

    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setRoomPosition(double x, double y) {
        this.room_x = x;
        this.room_y = y;
    }

    public int x_pos() { return (int)x; }
    public int y_pos() { return (int)y; }
    public int x_r_pos() { return (int)room_x; }
    public int y_r_pos() { return (int)room_y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setLeft(boolean b) { left = b; }
    public void setRight(boolean b) { right = b; }
    public void setUp(boolean b) { up = b; }
    public void setDown(boolean b) { down = b; }

    public boolean onScreen(){
        return true;
    }

    public void draw(java.awt.Graphics2D g){
        g.drawImage(sprite, (int)(x - width/2), (int)(y - height/2), null);
    }
}
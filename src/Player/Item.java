package Player;
import Entity.Entity;
import Handler.Mouse;
import Entity.Projectile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Item  extends Entity {
    protected String type;
    protected boolean picked_up = false;

    // projectile stuff
    protected BufferedImage proj_sprite;

    protected long last_shot;
    protected long shot_delay;

    public Item(String t, int room_x, int room_y){
        this.type = t;
        this.room_x = room_x;
        this.room_y = room_y;
        width = 75;
        height = 75;

        if (type.equals("weapon")) setPosition(810,675);
        else if (type.equals("ability")) setPosition(920,675);
    }

    public String getType() { return type; }
    public void pickUp() { picked_up = true; }
    public void drop() {}
    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y){}

    public double[] getVector() {
        // set center to 0,0
        double x = Mouse.getMouse_x() - 512;
        double y = Mouse.getMouse_y() - 384;
        double theta = Math.atan2(y, x);

        return new double[]{Math.cos(theta), Math.sin(theta)}; // dx, dy components
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        if (!picked_up) super.draw(g, x, y, (int)room_x, (int)room_y);
        else if (picked_up) super.draw(g);
    }
}
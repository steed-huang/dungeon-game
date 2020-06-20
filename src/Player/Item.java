package Player;
import Entity.Entity;
import Handler.Mouse;
import Entity.Projectile;
import Entity.CollisionBox;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Item  extends Entity {
    protected String type;
    protected boolean reachable;
    protected boolean picked_up;
    protected boolean ready;

    // projectile stuff
    protected BufferedImage proj_sprite;

    protected long last_shot;
    protected long shot_delay;

    public Item(String t, int room_x, int room_y, boolean pu){
        this.reachable = false;
        this.picked_up = pu;
        this.ready = true;
        this.type = t;
        this.room_x = room_x;
        this.room_y = room_y;
        this.width = 75;
        this.height = 75;

        cb = new CollisionBox("item", width, height, room_x, room_y);
        cb.setPosition(room_x, room_y);

        if (type.equals("weapon")) setPosition(848,713);
        else if (type.equals("ability")) setPosition(956,713);
    }

    public String getType() { return type; }
    public boolean getReady() { return ready; }
    public boolean getPickedUp() { return picked_up; }
    public boolean getReachable() { return reachable; }
    public void setReachable(boolean b) { reachable = b; }

    public void pickUp(Inventory inv, ArrayList<Item> items, Player player) {
        if (type.equals("weapon")){
            Item current = inv.getWeapon(); // drop old
            current.setRoomPosition(room_x, room_y);
            current.getCB().setPosition(room_x, room_y);
            current.drop(items);
            inv.setWeapon(this); // pick up new
            picked_up = true;
        } else if (type.equals("ability")){
            Item current = inv.getAbility(); // drop old
            current.setRoomPosition(room_x, room_y);
            current.getCB().setPosition(room_x, room_y);
            current.drop(items);
            inv.setAbility(this); // pick up new
            picked_up = true;
        }
    }

    public void drop(ArrayList<Item> items) {
        this.picked_up = false;
        items.add(this);
    }

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
        else super.draw(g);
    }
}
package Entity;

import Player.Player;
import Player.Item;

import java.awt.*;
import java.util.ArrayList;

public abstract class Enemy extends Entity {
    // stats
    protected boolean alive;

    protected double health;
    protected double maxHealth;
    protected double speed;
    protected double touch_dmg;
    protected double shot_dmg;

    protected long last_touch;
    protected final long touch_delay;

    protected boolean firing;
    protected long last_shot;
    protected long shot_delay;

    // keeping track of player
    protected double px;
    protected double py;
    protected double dist;

    public Enemy (int room_x, int room_y) {
        this.room_x = room_x;
        this.room_y = room_y;
        touch_delay = 1000;
        last_touch = 0;
        last_shot = 0;
    }

    public void update(ArrayList<CollisionBox> cbs, ArrayList<Projectile> projectiles, ArrayList<Enemy> enemies, ArrayList<Item> items, Player player){
        getNextPosition(cbs, enemies, player);
        cb.setPosition(room_x, room_y);
        checkPlayerCollision(player);
        shoot(projectiles);
        checkAlive(items);
    }

    protected void getNextPosition(ArrayList<CollisionBox> cbs, ArrayList<Enemy> enemies, Player player){
        // wall collision
        boolean left_wall = false;
        boolean right_wall = false;
        boolean up_wall = false;
        boolean down_wall = false;
        for (CollisionBox cb : cbs){
            if (cb.getType().equals("wall") || cb.getType().equals("door")) {
                if (this.cb.collidesWith(cb, room_x - speed, room_y) && !left_wall) left_wall = true;
                if (this.cb.collidesWith(cb, room_x + speed, room_y) && !right_wall) right_wall = true;
                if (this.cb.collidesWith(cb, room_x, room_y - speed) && !up_wall) up_wall = true;
                if (this.cb.collidesWith(cb, room_x, room_y + speed) && !down_wall) down_wall = true;
            }
        }

        updatePlayer(player);
        move(player); // sets dx & dy

        if (dx < 0 && !left_wall) room_x += dx * speed;
        else if (dx > 0 && !right_wall) room_x += dx * speed;
        if (dy < 0 && !up_wall) room_y += dy * speed;
        else if (dy > 0 && !down_wall) room_y += dy * speed;
    }

    protected void checkPlayerCollision(Player player){
        if (System.currentTimeMillis() - last_touch >= touch_delay) {
            if (this.cb.collidesWith(player.getCB())) player.hit(touch_dmg);
            last_touch = System.currentTimeMillis();
        }
    }

    public void move(Player player) { // default movement | add a* later if I add more obstacles
        dx = 0; dy = 0;
        if (dist < 400) { // within 400px
            dx = px - room_x;
            dy = py - room_y;

            if (dx != 0 || dy != 0) {
                double length = Math.sqrt(dx * dx + dy * dy);
                dx /= length;
                dy /= length;
            }
        }
    }

    public void updatePlayer(Player player) {
        px = player.x_r_pos();
        py = player.y_r_pos();
        dist = Math.sqrt((py - room_y) * (py - room_y) + (px - room_x) * (px - room_x));
    }

    public abstract void shoot( ArrayList<Projectile> projectiles);
    public void hit(double dmg) { health -= dmg; }
    public boolean getAlive() { return alive;}

    public void checkAlive(ArrayList<Item> items) {
        if (health <= 0){
            dropItem(items);
            alive = false;
        }
    }

    public abstract void dropItem(ArrayList<Item> items);

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y); // draw self
    }

    // draw health bar
    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) {
        g.setColor(new Color(170, 27, 27));
        g.fillRect(512-px+(x - (int)(width*0.75)/2), 384-py+(y - height/2)+height, (int)(width*0.75), 5); // red
        g.setColor(new Color(0, 169, 0));
        g.fillRect(512-px+(x - (int)(width*0.75)/2), 384-py+(y - height/2)+height, (int) ((width*0.75) * (health / maxHealth)), 5); // green
    }
}
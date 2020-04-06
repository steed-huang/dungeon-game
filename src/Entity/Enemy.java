package Entity;

import Player.Player;
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

    public Enemy (int room_x, int room_y) {
        this.room_x = room_x;
        this.room_y = room_y;
        touch_delay = 1000;
        last_touch = 0;
        last_shot = 0;
    }

    public void update(ArrayList<CollisionBox> cbs, ArrayList<Enemy> enemies, Player player){
        getNextPosition(cbs, enemies, player);
        cb.setPosition(room_x, room_y);
        checkPlayerCollision(player);
        shoot(player);
        checkAlive();
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
        dx = player.x_r_pos() - room_x;
        dy = player.y_r_pos() - room_y;

        if (dx != 0 || dy != 0) {
            double length = Math.sqrt(dx * dx + dy * dy);
            dx /= length;
            dy /= length;
        }
    }

    public abstract void shoot(Player player);

    public void hit(double dmg) { health -= dmg; }
    public boolean getAlive() { return alive;}
    public void checkAlive() { if (health <= 0) alive = false; }

    public void draw(java.awt.Graphics2D g, int x, int y) { super.draw(g, x, y, (int)room_x, (int)room_y); }
}
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
        move(player);
        shoot(player);
        getNextPosition(cbs, enemies);
        cb.setPosition(room_x, room_y);
        checkPlayerCollision(player);
        checkAlive();
    }

    public boolean getAlive() { return alive;}
    public void checkAlive() { if (health <= 0) alive = false; }

    public void getNextPosition(ArrayList<CollisionBox> cbs, ArrayList<Enemy> enemies){
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
        dx = 0; dy = 0;
        if (left && !left_wall) dx = -1;
        if (right && !right_wall) dx = 1;
        if (up && !up_wall) dy = -1;
        if (down && !down_wall) dy = 1;

        // normalize movement vector
        if (dx != 0 || dy != 0) {
            double length = Math.sqrt(dx * dx + dy * dy);
            dx /= length;
            dy /= length;
            room_x += dx * speed;
            room_y += dy * speed;
        }
    }

    public void checkPlayerCollision(Player player){
        if (System.currentTimeMillis() - last_touch >= touch_delay) {
            if (this.cb.collidesWith(player.getCB())) player.hit(touch_dmg);
            last_touch = System.currentTimeMillis();
        }
    }

    public abstract void move(Player player);
    public abstract void shoot(Player player);

    public void draw(java.awt.Graphics2D g, int x, int y) { super.draw(g, x, y, (int)room_x, (int)room_y); }
}
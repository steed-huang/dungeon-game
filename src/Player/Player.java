package Player;

import Entity.Entity;

import java.awt.*;
import java.util.ArrayList;
import Entity.Projectile;
import Entity.CollisionBox;
import Handler.Mouse;
import Player.Items.Magic_Stick;

import javax.imageio.ImageIO;

public class Player extends Entity {

    // room pos
    public int map_row;
    public int map_col;

    // stats
    private double health;
    private final double maxHealth;
    private boolean alive;
    private double speed;

    // shots
    private boolean firing;

    // inventory
    private Inventory inv;

    public Player(int r, int c){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Assets/player.png"));
        } catch(Exception e){
            e.printStackTrace();
        }

        map_row = r;
        map_col = c;

        setPosition(512, 384);
        setRoomPosition(750, 750);

        width = 50;
        height = 50;

        inv = new Inventory();
        inv.setWeapon(new Magic_Stick(0,0));

        cb = new CollisionBox("player", width, height, room_x, room_y);

        health = 100;
        maxHealth = 100;
        alive = true;
        speed = 5;

        firing = false;
    }

    public double health(){ return health; }
    public double maxHealth(){ return maxHealth; }

    public void setFiring(boolean b ){ firing = b;}

    public void update(ArrayList<CollisionBox> cbs, ArrayList<Projectile> projectiles){
        getNextPosition(cbs);
        cb.setPosition(room_x, room_y);
        setRoomPosition(room_x, room_y);
        shoot(projectiles);
        checkAlive();
    }

    private void getNextPosition(ArrayList<CollisionBox> cbs) {
        // wall collision
        boolean left_wall = false;
        boolean right_wall = false;
        boolean up_wall = false;
        boolean down_wall = false;
        for (CollisionBox cb : cbs){
            if (cb.getType().equals("wall")) {
                if (this.cb.collidesWith(cb, room_x - speed, room_y) && !left_wall) left_wall = true;
                if (this.cb.collidesWith(cb, room_x + speed, room_y) && !right_wall) right_wall = true;
                if (this.cb.collidesWith(cb, room_x, room_y - speed) && !up_wall) up_wall = true;
                if (this.cb.collidesWith(cb, room_x, room_y + speed) && !down_wall) down_wall = true;
            }
        }

        dx = 0; dy = 0;
        if (left && !left_wall) dx -= 1;
        if (right && !right_wall) dx += 1;
        if (up && !up_wall) dy -= 1;
        if (down && !down_wall) dy += 1;

        // normalize movement vector
        if (dx != 0 || dy != 0) {
            double length = Math.sqrt(dx * dx + dy * dy);
            dx /= length;
            dy /= length;
            room_x += dx * speed;
            room_y += dy * speed;
        }
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        inv.getWeapon().shoot(firing, projectiles, room_x, room_y);
    }

    public void hit(double dmg) { health -= dmg; }

    public void checkAlive() { if (health <= 0) alive = false; }

    public void draw(Graphics2D g) { super.draw(g); }
}
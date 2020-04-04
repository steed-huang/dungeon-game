package Player;

import Entity.Entity;

import java.awt.*;
import java.util.ArrayList;
import Entity.Projectile;
import Entity.CollisionBox;
import Handler.Mouse;

import javax.imageio.ImageIO;

public class Player extends Entity {

    // room pos
    public int map_row;
    public int map_col;

    // stats
    private double health;
    private double maxHealth;
    private boolean alive;
    private double speed;

    // shots
    private boolean firing;
    private long last_shot;
    private long shot_delay;

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

        cb = new CollisionBox("player", width, height, room_x, room_y);

        health = 100;
        maxHealth = 100;
        alive = true;
        speed = 5;

        firing = false;
        last_shot = 0;
        shot_delay = 500;
    }

    public double health(){ return health; }
    public double maxHealth(){ return maxHealth; }

    public void setFiring(boolean b ){ firing = b;}

    public void update(ArrayList<CollisionBox> cbs, ArrayList<Projectile> projectiles){
        getNextPosition(cbs);
        cb.setPosition(room_x, room_y);
        setRoomPosition(room_x, room_y);
        shoot(projectiles);
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
        if (left && !left_wall) room_x -= speed;
        if (right && !right_wall) room_x += speed;
        if (up && !up_wall) room_y -= speed;
        if (down && !down_wall) room_y += speed;
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", room_x, room_y, vec[0], vec[1], 10, 10, 10));
            last_shot = System.currentTimeMillis();
        }
    }

    public double[] getVector() {
        // set center to 0,0
        double x = Mouse.getMouse_x() - 512;
        double y = Mouse.getMouse_y() - 384;
        double theta = Math.atan2(y, x);

        return new double[]{Math.cos(theta), Math.sin(theta)}; // dx, dy components
    }

    public void draw(Graphics2D g) { super.draw(g); }
}
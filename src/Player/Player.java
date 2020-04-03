package Player;

import Entity.Entity;

import java.awt.*;
import java.util.ArrayList;
import Entity.Projectile;
import Entity.CollisionBox;

import javax.imageio.ImageIO;

public class Player extends Entity {

    // room pos
    public int map_row;
    public int map_col;

    // stats
    private int health;
    private int maxHealth;
    private boolean alive;
    private double speed;

    // shots
    private boolean firing;
    private ArrayList<Projectile> projectiles;

    public Player(int r, int c){
        try {
                sprite = ImageIO.read(getClass().getResourceAsStream("/Assets/player.jpg"));
        } catch(Exception e){
            e.printStackTrace();
        }

        map_row = r;
        map_col = c;

        setPosition(512, 384);
        setRoomPosition(750, 750);

        width = 50;
        height = 50;

        cb = new CollisionBox(width, height, room_x, room_y);

        health = 100;
        maxHealth = 100;
        alive = true;
        speed = 5;
    }


    public int getHealth(){ return health; }
    public int getMaxHealth(){ return maxHealth; }

    public void setFiring(){ firing = true; }

    public void update(ArrayList<CollisionBox> cbs){
        cb.setPosition(room_x, room_y);
        getNextPosition(cbs);
        setRoomPosition(room_x, room_y);
    }

    private void getNextPosition(ArrayList<CollisionBox> cbs) {
        for (CollisionBox cb : cbs){
            if (this.cb.collidesWith(cb)){ System.out.println(1); }
        }
        if (left) room_x -= speed;
        if (right) room_x += speed;
        if (up) room_y -= speed;
        if (down) room_y += speed;
    }

    public void draw(Graphics2D g) { super.draw(g);
    }
}
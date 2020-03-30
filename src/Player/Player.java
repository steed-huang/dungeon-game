package Player;

import Entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Entity.Projectile;

import javax.imageio.ImageIO;

public class Player extends Entity {
    // stats
    private int health;
    private int maxHealth;
    private boolean alive;
    private double speed;

    // shots
    private boolean firing;
    private ArrayList<Projectile> projectiles;

    public Player(){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Assets/player.jpg"));
        } catch(Exception e){
            e.printStackTrace();
        }

        setPosition(512, 384);

        width = 50;
        height = 50;

        health = 100;
        maxHealth = 100;
        alive = true;
        speed = 3;
    }

    public int getHealth(){ return health; }
    public int getMaxHealth(){ return maxHealth; }

    public void setFiring(){ firing = true; }

    public void update(){
        getNextPosition();
        checkCollision();
        setPosition(x, y);
    }

    private void getNextPosition() {
        if (left) x -= speed;
        if (right) x += speed;
        if (up) y -= speed;
        if (down) y += speed;
    }

    private void checkCollision() {}

    public void draw(Graphics2D g) {
        //setMapPosition();
        super.draw(g);
    }
}
package Entity.Enemies;


import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Player.Item;
import Player.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Minotaur extends Enemy {

    // moving sprite
    private BufferedImage charging_sprite;

    // stationary to simulate charging in between delays
    private boolean stationary;

    private long inbetween_stationary_delay; // delay between stationary
    private long last_inbetween_stationary;

    private long stationary_delay; // how long it is stationary
    private long last_stationary;

    public Minotaur(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("minotaur.png");
        if (charging_sprite == null) charging_sprite = ImageLoader.getImage("minotaur_charge.png");

        alive = true;
        health = 70;
        maxHealth = 70;
        speed = 5;
        touch_dmg = 10;

        width = 75;
        height = 75;

        inbetween_stationary_delay = 3500;
        last_inbetween_stationary = 0;

        stationary_delay = 1500;
        last_stationary = 0;

        stationary = false;

        cb = new CollisionBox("minotaur", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {}

    public void dropItem(ArrayList<Item> items) { }

    public void move(Player player) {
        super.move(player);
        if (dist < 500 && System.currentTimeMillis() - last_inbetween_stationary > inbetween_stationary_delay) {
            stationary = true;
            last_stationary = System.currentTimeMillis();
            last_inbetween_stationary = System.currentTimeMillis();
        }
        if (speed != 0 && stationary) speed = 0;
        updateStationary();
    }

    public void updateStationary() {
        if (stationary && System.currentTimeMillis() - last_stationary > stationary_delay) {
            stationary = false;
            speed = 5;
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        this.draw(g, x, y, (int)room_x, (int)room_y);
        this.drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void draw(java.awt.Graphics2D g, int px, int py, int x, int y) {
        if(onScreen(px, py)){
            if (!stationary) g.drawImage(charging_sprite,512-px+(x - width/2),384-py+(y - height/2), null);
            else g.drawImage(sprite,512-px+(x - width/2),384-py+(y - height/2), null);
        }
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}
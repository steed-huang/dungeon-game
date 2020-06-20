package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Item;
import Player.Items.Triple_Stick;
import Player.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Narwhal extends Enemy {

    private static BufferedImage shooting_sprite;

    private boolean stationary;

    private long inbetween_stationary_delay; // delay between stationary
    private long last_inbetween_stationary;

    private long stationary_delay; // how long it is stationary
    private long last_stationary;

    public Narwhal(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("narwhal.png");
        if (shooting_sprite == null) shooting_sprite = ImageLoader.getImage("yawn_narwhal.png");
        proj_sprite = ImageLoader.getImage("narwhalproj.png");

        alive = true;
        health = 50;
        maxHealth = 50;
        speed = 2;
        touch_dmg = 5;

        width = 75;
        height = 53; // weird dimension, I know

        shot_delay = 100;

        stationary = false;

        inbetween_stationary_delay = 5000;
        last_inbetween_stationary = 0;

        stationary_delay = 2000;
        last_stationary = 0;

        cb = new CollisionBox("zombie", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (stationary && System.currentTimeMillis() - last_shot >= shot_delay) {
            projectiles.add(new Projectile("narwhalproj", proj_sprite, 1, 600, room_x, room_y, dx, dy, 8, 15, 15));
            last_shot = System.currentTimeMillis();
        }
    }

    public void move(Player player) {
        super.move(player);
        if (dist < 320 && System.currentTimeMillis() - last_inbetween_stationary > inbetween_stationary_delay) {
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
            speed = 2;
            last_stationary = 0;
        }
    }

    public void dropItem(ArrayList<Item> items) {
        if (RandomGenerator.getRandom(1, 100) <= 20) {
            items.add(new Triple_Stick((int)room_x, (int)room_y));
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        this.draw(g, x, y, (int)room_x, (int)room_y);
        this.drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void draw(java.awt.Graphics2D g, int px, int py, int x, int y) {
        if(onScreen(px, py)){
            if (!stationary) g.drawImage(sprite,512-px+(x - width/2),384-py+(y - height/2), null);
            else g.drawImage(shooting_sprite,512-px+(x - width/2),384-py+(y - height/2), null);
        }
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

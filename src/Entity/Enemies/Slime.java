package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Item;
import Player.Items.Slime_Juice;

import java.util.ArrayList;

public class Slime extends Enemy {

    private long duplicate_delay;
    private long last_duplicate;

    public Slime(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("slime.png");

        alive = true;
        health = 90;
        maxHealth = 90;
        speed = 0.5;
        touch_dmg = 10;

        width = 75;
        height = 75;

        duplicate_delay = 8000;
        last_duplicate = 0;

        spawn = false;

        cb = new CollisionBox("slime", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        updateSpawn(); // easier to just call from here
    }

    public void dropItem(ArrayList<Item> items) {
        if (RandomGenerator.getRandom(1, 100) <= 20) {
            items.add(new Slime_Juice((int)room_x, (int)room_y));
        }
    }

    public void updateSpawn() {
        if (System.currentTimeMillis() - last_duplicate > duplicate_delay) {
            spawn = true;
            last_duplicate = System.currentTimeMillis();
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

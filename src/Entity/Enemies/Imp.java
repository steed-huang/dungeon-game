package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Items.Lightning_Stick;
import Player.Items.Magic_Ball;
import Player.Items.Sniper_Stick;
import Player.Player;
import Player.Item;

import java.util.ArrayList;

public class Imp extends Enemy {

    public Imp(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("imp.png");
        proj_sprite = ImageLoader.getImage("impproj.png");

        alive = true;
        health = 50;
        maxHealth = 50;
        speed = 3;
        touch_dmg = 1;

        width = 50;
        height = 50;

        shot_delay = 1500;

        cb = new CollisionBox("imp", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (System.currentTimeMillis() - last_shot >= shot_delay && dist < 500) {
            projectiles.add(new Projectile("imp_proj", proj_sprite, 1,750, room_x, room_y, dx, dy, 12, 20, 20));
            last_shot = System.currentTimeMillis();
        }
    }

    public void move(Player player) { // default movement | add a* later if I add more obstacles
        dx = 0; dy = 0;
        if (dist < 450) { // within 450px
            dx = px - room_x;
            dy = py - room_y;
            if (dx != 0 || dy != 0) {
                double length = Math.sqrt(dx * dx + dy * dy);
                dx /= length;
                dy /= length;
            }
            if (dist < 290) { // run away when close
                if (speed > 0) speed *= -1;
            } else if (speed < 0) speed *= -1;
        }
    }

    public void dropItem(ArrayList<Item> items) {
        if (RandomGenerator.getRandom(1, 100) <= 20) {
            items.add(new Lightning_Stick((int)room_x, (int)room_y));
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

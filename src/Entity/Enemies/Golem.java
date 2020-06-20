package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Items.Sniper_Stick;
import Player.Player;
import Player.Item;

import java.util.ArrayList;

public class Golem extends Enemy {
    public Golem(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("golem.png");
        proj_sprite = ImageLoader.getImage("golem_boulder.png");

        alive = true;
        health = 90;
        maxHealth = 90;
        speed = 1;
        touch_dmg = 5;

        width = 110;
        height = 110;

        shot_delay = 3500;

        cb = new CollisionBox("golem", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (System.currentTimeMillis() - last_shot >= shot_delay && dist < 260) {
            projectiles.add(new Projectile("golem_proj", proj_sprite, 15,5000, room_x, room_y, dx, dy, 2, 40, 40));
            last_shot = System.currentTimeMillis();
        }
    }

    public void move(Player player) { // default movement | add a* later if I add more obstacles
        dx = 0; dy = 0;
        if (dist < 500) { // within 400px
            dx = px - room_x;
            dy = py - room_y;
            if (dx != 0 || dy != 0) {
                double length = Math.sqrt(dx * dx + dy * dy);
                dx /= length;
                dy /= length;
            }
            if (dist < 250) { // run away when close
                if (speed > 0) speed *= -1;
            } else if (speed < 0) speed *= -1;
        }
    }

    public void dropItem(ArrayList<Item> items) {}

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Player;

import java.util.ArrayList;

public class Skeleton extends Enemy {

    public Skeleton(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("skeleton.png");
        proj_sprite = ImageLoader.getImage("skeleproj.png");

        alive = true;
        health = 30;
        maxHealth = 30;
        speed = 2;
        touch_dmg = 1;

        width = 60;
        height = 60;

        shot_delay = 500;

        cb = new CollisionBox("skeleton", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (System.currentTimeMillis() - last_shot >= shot_delay && dist < 280) {
            projectiles.add(new Projectile("skele_proj", proj_sprite, 3,1200, room_x, room_y, dx, dy, 4, 10, 10));
            last_shot = System.currentTimeMillis();
        }
    }

    public void move(Player player) { // default movement | add a* later if I add more obstacles
        dx = 0; dy = 0;
        if (dist < 400) { // within 400px
            dx = px - room_x;
            dy = py - room_y;
            if (dx != 0 || dy != 0) {
                double length = Math.sqrt(dx * dx + dy * dy);
                dx /= length;
                dy /= length;
            }
            if (dist < 270) { // run away when close
                if (speed > 0) speed *= -1;
            } else if (speed < 0) speed *= -1;
        }
    }


    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

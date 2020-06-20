package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Item;
import Player.Items.Triple_Stick;

import java.util.ArrayList;

public class Zombie extends Enemy {

    public Zombie(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("zombie.png");
        proj_sprite = ImageLoader.getImage("zombieproj.png");

        alive = true;
        health = 50;
        maxHealth = 50;
        speed = 1;
        touch_dmg = 5;

        width = 55;
        height = 55;

        shot_delay = 1000;

        cb = new CollisionBox("zombie", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (System.currentTimeMillis() - last_shot >= shot_delay && dist < 280) {
            // right 20 deg
            double right_x = Math.cos(0.35)*dx - Math.sin(0.35)*dy;
            double right_y = Math.sin(0.35)*dx +  Math.cos(0.35)*dy;

            // left 20 deg
            double left_x = Math.cos(-0.35)*dx - Math.sin(-0.35)*dy;
            double left_y = Math.sin(-0.35)*dx +  Math.cos(-0.35)*dy;

            projectiles.add(new Projectile("zombie_proj", proj_sprite, 3, 1000, room_x, room_y, dx, dy, 3, 15, 15));
            projectiles.add(new Projectile("zombie_proj", proj_sprite, 3,1000, room_x, room_y, right_x, right_y, 3, 15, 15));
            projectiles.add(new Projectile("zombie_proj", proj_sprite, 3,1000, room_x, room_y, left_x, left_y, 3, 15, 15));
            last_shot = System.currentTimeMillis();
        }
    }

    public void dropItem(ArrayList<Item> items) {
        if (RandomGenerator.getRandom(1, 100) <= 20) {
            items.add(new Triple_Stick((int)room_x, (int)room_y));
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Items.Magic_Ball;
import Player.Items.Random_Stick;
import Player.Items.Sniper_Stick;
import Player.Player;
import Player.Item;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Wizard extends Enemy {

    private static BufferedImage[] proj_sprites = new BufferedImage[4];

    public Wizard(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("wizard.png");
        proj_sprites[0] = ImageLoader.getImage("wizard_proj_1.png");
        proj_sprites[1] = ImageLoader.getImage("wizard_proj_2.png");
        proj_sprites[2] = ImageLoader.getImage("wizard_proj_3.png");
        proj_sprites[3] = ImageLoader.getImage("wizard_proj_4.png");

        alive = true;
        health = 50;
        maxHealth = 50;
        speed = 1;
        touch_dmg = 1;

        width = 60;
        height = 60;

        shot_delay = 1000;

        cb = new CollisionBox("wizard", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (System.currentTimeMillis() - last_shot >= shot_delay && dist < 300) {
            int rng = RandomGenerator.getRandom(1, 100);
            if (rng < 33) projectiles.add(new Projectile("wizard_proj", proj_sprites[0], 3,1200, room_x, room_y, dx, dy, 6, 30, 30));
            else if (rng > 33 && rng < 66) projectiles.add(new Projectile("wizard_proj", proj_sprites[1], 5,1200, room_x, room_y, dx, dy, 4, 40, 40));
            else if (rng > 66 && rng < 99) projectiles.add(new Projectile("wizard_proj", proj_sprites[2], 8,1500, room_x, room_y, dx, dy, 2, 50, 50));
            else if (rng == 100) projectiles.add(new Projectile("wizard_proj", proj_sprites[3], 50,5000, room_x, room_y, dx, dy, 0.5, 100, 100));
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
            items.add(new Random_Stick((int)room_x, (int)room_y));
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}
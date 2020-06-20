package Entity.Enemies;


import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Player.Item;

import java.util.ArrayList;

public class BabySlime extends Enemy {

    public BabySlime(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("babyslime.png");

        alive = true;
        health = 30;
        maxHealth = 30;
        speed = 3;
        touch_dmg = 5;

        width = 40;
        height = 40;

        cb = new CollisionBox("babyslime", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {}

    public void dropItem(ArrayList<Item> items) { }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

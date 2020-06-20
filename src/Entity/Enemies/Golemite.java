package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Item;
import Player.Items.Triple_Stick;

import java.util.ArrayList;

public class Golemite extends Enemy {

    public Golemite(int room_x, int room_y, int num) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("golemite_" + num + ".png");

        alive = true;
        health = 50;
        maxHealth = 50;
        if (num == 1) speed = 2.5;
        else if (num == 2) speed = 2;
        touch_dmg = 5;

        width = 50;
        height = 50;

        cb = new CollisionBox("golemite", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {}

    public void dropItem(ArrayList<Item> items) {}

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}
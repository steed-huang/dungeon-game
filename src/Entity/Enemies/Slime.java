package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Player.Player;

import java.util.ArrayList;

public class Slime extends Enemy {

    public Slime(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("slime.png");

        alive = true;
        health = 50;
        maxHealth = 50;
        speed = 1;
        touch_dmg = 8;

        width = 75;
        height = 75;

        cb = new CollisionBox("slime", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {}

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

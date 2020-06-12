package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Images.ImageLoader;
import Player.Player;

import javax.imageio.ImageIO;

public class Ghost extends Enemy {

    public Ghost(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("ghost.png");

        alive = true;
        health = 15;
        maxHealth = 15;
        speed = 4;
        touch_dmg = 5;

        width = 75;
        height = 75;

        cb = new CollisionBox("ghost", width, height, room_x, room_y);
    }

    public void shoot(Player player) {}


    public void draw(java.awt.Graphics2D g, int x, int y) { super.draw(g, x, y, (int)room_x, (int)room_y); }
}

package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Player.Player;

import javax.imageio.ImageIO;

public class Ghost extends Enemy {

    public Ghost(int room_x, int room_y) {
        super(room_x, room_y);

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Assets/ghost.png"));
        } catch(Exception e){
            e.printStackTrace();
        }

        alive = true;
        health = 30;
        maxHealth = 30;
        speed = 3;
        touch_dmg = 5;

        width = 60;
        height = 60;

        cb = new CollisionBox("ghost", width, height, room_x, room_y);
    }

    public void shoot(Player player) {}


    public void draw(java.awt.Graphics2D g, int x, int y) { super.draw(g, x, y, (int)room_x, (int)room_y); }
}

package Entity.Enemies;

import Entity.CollisionBox;
import Entity.Enemy;
import Entity.Projectile;
import Images.ImageLoader;
import Main.RandomGenerator;
import Player.Item;
import Player.Items.Magic_Ball;

import java.util.ArrayList;

public class Ghost extends Enemy {

    public Ghost(int room_x, int room_y) {
        super(room_x, room_y);

        sprite = ImageLoader.getImage("ghost.png");
        proj_sprite = ImageLoader.getImage("projectile.png");

        alive = true;
        health = 15;
        maxHealth = 15;
        speed = 4;
        touch_dmg = 3;

        width = 50;
        height = 50;

        cb = new CollisionBox("ghost", width, height, room_x, room_y);
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        if (dist < 35) { // explode when close
            // turn 45 deg each for 8 bullets 360 deg
            double deg = 0;
            double[][] turned_vecs = new double[8][2];

            for(int i = 0; i < 8; i++){ // generating turned vectors
                turned_vecs[i][0] = Math.cos(deg)*dx - Math.sin(deg)*dy;
                turned_vecs[i][1] = Math.sin(deg)*dx +  Math.cos(deg)*dy;
                deg += 0.785; // add 45 deg
            }

            for(int i = 0; i < 8; i++){
                projectiles.add(new Projectile("ghost_proj", proj_sprite, 2,300, room_x, room_y, turned_vecs[i][0], turned_vecs[i][1], 8, 10, 10));
            }
            this.health = 0;
        }
    }

    public void dropItem(ArrayList<Item> items) {
        if (RandomGenerator.getRandom(1, 100) <= 20) {
            items.add(new Magic_Ball((int)room_x, (int)room_y));
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y, (int)room_x, (int)room_y);
        drawHealth(g, x, y, (int)room_x, (int)room_y);
    }

    public void drawHealth(java.awt.Graphics2D g, int px, int py, int x, int y) { super.drawHealth(g, px, py, x, y); }
}

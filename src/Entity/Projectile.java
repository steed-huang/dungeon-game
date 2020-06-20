package Entity;

import Player.Player;
import World.LayoutLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Projectile extends Entity {

    private double speed;
    private boolean remove;
    private double dmg;
    private long born;
    private long lifetime;

    public Projectile(String type, BufferedImage sp, double d, long l, double x, double y, double dx, double dy, double s, int w, int h){
        sprite = sp;

        dmg = d;
        room_x = x;
        room_y = y;
        this.dx = dx;
        this.dy = dy;
        speed = s;
        width = w;
        height = h;

        born = System.currentTimeMillis();
        lifetime = l;

        cb = new CollisionBox(type, width, height, room_x, room_y);
    }

    public void update(ArrayList<CollisionBox> cbs, ArrayList<Projectile> projectiles, ArrayList<Enemy> enemies, Player player){
        checkLife();
        getNextPosition(cbs, projectiles);
        cb.setPosition(room_x, room_y);
        checkHit(enemies, player);
    }

    private void getNextPosition(ArrayList<CollisionBox> cbs, ArrayList<Projectile> projectiles) { // should add max range to projectiles
        room_x += dx*speed;
        room_y += dy*speed;

        for (CollisionBox cb : cbs){
            if ((cb.getType().equals("wall") || cb.getType().equals("door"))&& this.cb.collidesWith(cb)){
                remove = true; break;
            }
        }
    }

    private void checkHit(ArrayList<Enemy> enemies, Player player) {
        if (cb.getType().equals("player_proj")) { // player projectile hitting enemy
            for (Enemy e : enemies) {
                if ((cb.collidesWith(e.getCB()))) {
                    e.hit(dmg);
                    remove = true;
                    break;
                }
            }
        } else { // enemy projectile hitting player
            if ((cb.collidesWith(player.getCB()))) {
                player.hit(dmg);
                if (cb.getType().equals("imp_proj")) player.paralyze();
                remove = true;
            }
        }
    }

    private void checkLife() {
        if (System.currentTimeMillis() - born >= lifetime) { remove = true; }
    }

    public boolean getRemove() { return remove;}

    public void draw(java.awt.Graphics2D g, int x, int y) { super.draw(g, x, y, (int)room_x, (int)room_y); }
}

package Entity;

import World.LayoutLoader;

import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Projectile extends Entity {

    private double speed;
    private boolean remove;

    public Projectile(String type, double x, double y, double dx, double dy,  double s, int w, int h){
        try {
            sprite = ImageIO.read(LayoutLoader.class.getResourceAsStream("/Assets/projectile.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        room_x = x;
        room_y = y;
        this.dx = dx;
        this.dy = dy;
        speed = s;
        width = w;
        height = h;

        cb = new CollisionBox(type, width, height, room_x, room_y);
    }

    public void update(ArrayList<CollisionBox> cbs, ArrayList<Projectile> projectiles){
        getNextPosition(cbs, projectiles);
        cb.setPosition(room_x, room_y);
    }

    private void getNextPosition(ArrayList<CollisionBox> cbs, ArrayList<Projectile> projectiles) {
        room_x += dx*speed;
        room_y += dy*speed;

        for (CollisionBox cb : cbs){
            if ((cb.getType().equals("wall") || cb.getType().equals("door"))&& this.cb.collidesWith(cb)) remove = true;
        }
    }

    public boolean getRemove() { return remove;}

    public void draw(java.awt.Graphics2D g, int x, int y) { super.draw(g, x, y, (int)room_x, (int)room_y); }
}

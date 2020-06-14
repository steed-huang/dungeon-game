package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import java.util.ArrayList;

public class Magic_Ball extends Item {

    public Magic_Ball(int room_x, int room_y) {
        super("ability", room_x, room_y, false);

        sprite = ImageLoader.getImage("magic_ball.png");
        proj_sprite = ImageLoader.getImage("magic_proj.png");

        this.last_shot = 0;
        this.shot_delay = 6000;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y) {
        boolean ready = System.currentTimeMillis() - last_shot >= shot_delay;
        if (!this.ready && ready) this.ready = true;
        if (firing && ready) {
            double[] vec = getVector();

            // turn 45 deg each for 8 bullets 360 deg
            double deg = 0;
            double[][] turned_vecs = new double[7][2];

            for(int i = 0; i < 7; i++){ // generating turned vectors
                deg += 0.785; // add 45 deg
                turned_vecs[i][0] = Math.cos(deg)*vec[0] - Math.sin(deg)*vec[1];
                turned_vecs[i][1] = Math.sin(deg)*vec[0] +  Math.cos(deg)*vec[1];
            }

            // firing projectiles
            projectiles.add(new Projectile("player_proj", proj_sprite, 8,500, x, y, vec[0], vec[1], 5, 15, 15));
            for(int i = 0; i < 7; i++){
                projectiles.add(new Projectile("player_proj", proj_sprite, 8,500, x, y, turned_vecs[i][0], turned_vecs[i][1], 5, 15, 15));
            }

            last_shot = System.currentTimeMillis();
            this.ready = false;
        }
    }

}

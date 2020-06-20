package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import Player.Player;

import java.util.ArrayList;

public class Triple_Stick extends Item {

    public Triple_Stick(int room_x, int room_y) {
        super("weapon", room_x, room_y, false);

        sprite = ImageLoader.getImage("triple_stick.png");
        proj_sprite = ImageLoader.getImage("projectile.png");

        this.last_shot = 0;
        this.shot_delay = 600;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y, Player player) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();

            // right 20 deg
            double right_x = Math.cos(0.35)*vec[0] - Math.sin(0.35)*vec[1];
            double right_y = Math.sin(0.35)*vec[0] +  Math.cos(0.35)*vec[1];

            // left 20 deg
            double left_x = Math.cos(-0.35)*vec[0] - Math.sin(-0.35)*vec[1];
            double left_y = Math.sin(-0.35)*vec[0] +  Math.cos(-0.35)*vec[1];

            projectiles.add(new Projectile("player_proj", proj_sprite, 7,350, x, y, vec[0], vec[1], 8, 10, 10));
            projectiles.add(new Projectile("player_proj", proj_sprite, 6,350, x, y, right_x, right_y, 8, 10, 10));
            projectiles.add(new Projectile("player_proj", proj_sprite, 6,350, x, y, left_x, left_y, 8, 10, 10));

            last_shot = System.currentTimeMillis();
        }
    }
}

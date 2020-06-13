package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import java.util.ArrayList;

public class Triple_Stick extends Item {

    public Triple_Stick(int room_x, int room_y) {
        super("weapon", room_x, room_y, false);

        sprite = ImageLoader.getImage("triple_stick.png");
        proj_sprite = ImageLoader.getImage("projectile.png");

        this.last_shot = 0;
        this.shot_delay = 500;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", proj_sprite, 1,400, x, y, vec[0], vec[1], 10, 10, 10));
            projectiles.add(new Projectile("player_proj", proj_sprite, 2,400, x, y, vec[0], vec[1], 8, 10, 10));
            projectiles.add(new Projectile("player_proj", proj_sprite, 3,400, x, y, vec[0], vec[1], 5, 10, 10));
            last_shot = System.currentTimeMillis();
        }
    }
}

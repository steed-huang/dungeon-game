package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import java.util.ArrayList;

public class Sniper_Stick extends Item {

    public Sniper_Stick(int room_x, int room_y) {
        super("weapon", room_x, room_y, false);

        sprite = ImageLoader.getImage("sniper_stick.png");
        proj_sprite = ImageLoader.getImage("projectile_medium.png");

        this.last_shot = 0;
        this.shot_delay = 1500;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", proj_sprite, 20,500, x, y, vec[0], vec[1], 15, 20, 20));
            last_shot = System.currentTimeMillis();
        }
    }
}

package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import Player.Player;

import java.util.ArrayList;

public class Narwhal_Stick extends Item {

    public Narwhal_Stick(int room_x, int room_y) {
        super("weapon", room_x, room_y, false);

        sprite = ImageLoader.getImage("narwhal_stick.png");
        proj_sprite = ImageLoader.getImage("narwhalstick_proj.png");

        this.last_shot = 0;
        this.shot_delay = 80;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y, Player player) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", proj_sprite, 3,350, x, y, vec[0], vec[1], 10, 20, 20));
            last_shot = System.currentTimeMillis();
        }
    }
}

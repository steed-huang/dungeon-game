package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import Player.Player;

import java.util.ArrayList;

public class Lightning_Stick extends Item {

    public Lightning_Stick(int room_x, int room_y) {
        super("weapon", room_x, room_y, false);

        sprite = ImageLoader.getImage("lightning_stick.png");
        proj_sprite = ImageLoader.getImage("impproj.png");

        this.last_shot = 0;
        this.shot_delay = 500;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y, Player player) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", proj_sprite, 20,500, x, y, vec[0], vec[1], 20, 20, 20));
            last_shot = System.currentTimeMillis();
        }
    }
}

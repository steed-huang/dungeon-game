package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import Player.Player;

import java.util.ArrayList;

public class Rock_Ability extends Item {

    public Rock_Ability(int room_x, int room_y) {
        super("ability", room_x, room_y, false);

        sprite = ImageLoader.getImage("rock_ability.png");
        proj_sprite = ImageLoader.getImage("player_rock_proj.png");

        this.last_shot = 0;
        this.shot_delay = 8000;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y, Player player) {
        boolean ready = System.currentTimeMillis() - last_shot >= shot_delay;
        if (!this.ready && ready) this.ready = true;
        if (firing && ready) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", proj_sprite, 50,5000, x, y, vec[0], vec[1], 3, 50, 50));
            last_shot = System.currentTimeMillis();
            this.ready = false;
        }
    }
}
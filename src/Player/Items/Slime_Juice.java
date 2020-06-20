package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import Player.Player;

import java.util.ArrayList;

public class Slime_Juice extends Item {

    public Slime_Juice(int room_x, int room_y) {
        super("ability", room_x, room_y, false);

        sprite = ImageLoader.getImage("slime_juice.png");

        this.last_shot = 0;
        this.shot_delay = 10000;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y, Player player) {
        boolean ready = System.currentTimeMillis() - last_shot >= shot_delay;
        if (!this.ready && ready) this.ready = true;
        if (firing && ready) {
            player.heal(25);
            last_shot = System.currentTimeMillis();
            this.ready = false;
        }
    }
}

package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import Player.Player;

import java.util.ArrayList;

public class Minotaur_Shirt extends Item {

    public Minotaur_Shirt(int room_x, int room_y) {
        super("ability", room_x, room_y, false);

        sprite = ImageLoader.getImage("minotaur_shirt.png");

        this.last_shot = 0;
        this.shot_delay = 8000;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y, Player player) {
        boolean ready = System.currentTimeMillis() - last_shot >= shot_delay;
        if (!this.ready && ready) this.ready = true;
        if (firing && ready) {
            player.setLastSpeed(System.currentTimeMillis());
            player.setSpeed(9);

            last_shot = System.currentTimeMillis();
            this.ready = false;
        }
    }
}
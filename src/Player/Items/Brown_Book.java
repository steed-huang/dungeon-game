package Player.Items;

import Entity.Projectile;
import Player.Item;
import Images.ImageLoader;
import java.util.ArrayList;

public class Brown_Book extends Item {

    public Brown_Book(int room_x, int room_y) {
        super("ability", room_x, room_y, true);

        sprite = ImageLoader.getImage("brown_book.png");
        proj_sprite = ImageLoader.getImage("fireball.png");

        this.last_shot = 0;
        this.shot_delay = 5000;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y) {
        boolean ready = System.currentTimeMillis() - last_shot >= shot_delay;
        if (!this.ready && ready) this.ready = true;
        if (firing && ready) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", proj_sprite, 15,1000, x, y, vec[0], vec[1], 5, 30, 30));
            last_shot = System.currentTimeMillis();
            this.ready = false;
        }
    }

}

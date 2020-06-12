package Player.Items;
import Entity.Projectile;
import Player.Item;

import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Magic_Stick extends Item {

    public Magic_Stick(int room_x, int room_y) {
        super("weapon", room_x, room_y);

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Assets/projectile.png"));
        } catch(Exception e){
            e.printStackTrace();
        }

        this.last_shot = 0;
        this.shot_delay = 300;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();
            projectiles.add(new Projectile("player_proj", sprite, 5,350, x, y, vec[0], vec[1], 10, 10, 10));
            last_shot = System.currentTimeMillis();
        }
    }
}

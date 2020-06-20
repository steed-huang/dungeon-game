package Player.Items;

import Entity.Projectile;
import Main.RandomGenerator;
import Player.Item;
import Images.ImageLoader;
import Player.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Random_Stick extends Item {

    private static BufferedImage[] proj_sprites = new BufferedImage[3];

    public Random_Stick(int room_x, int room_y) {
        super("weapon", room_x, room_y, false);

        sprite = ImageLoader.getImage("random_stick.png");
        proj_sprites[0] = ImageLoader.getImage("random_proj_1.png");
        proj_sprites[1] = ImageLoader.getImage("random_proj_2.png");
        proj_sprites[2] = ImageLoader.getImage("random_proj_3.png");

        this.last_shot = 0;
        this.shot_delay = 300;
    }

    public void shoot(boolean firing, ArrayList<Projectile> projectiles, double x, double y, Player player) {
        if (firing && System.currentTimeMillis() - last_shot >= shot_delay) {
            double[] vec = getVector();
            int rng = RandomGenerator.getRandom(1, 3);
            if (rng == 1) projectiles.add(new Projectile("player_proj", proj_sprites[0] , 5,500, x, y, vec[0], vec[1], 10, 15, 15));
            else if (rng == 2) projectiles.add(new Projectile("player_proj", proj_sprites[1] , 10,600, x, y, vec[0], vec[1], 8, 20, 20));
            else if (rng == 3) projectiles.add(new Projectile("player_proj", proj_sprites[2] , 15,750, x, y, vec[0], vec[1], 5, 25, 25));
            last_shot = System.currentTimeMillis();
        }
    }
}

package Entity;

import Player.Player;
import java.util.ArrayList;

public class Boss extends Enemy {
    public Boss(int room_x, int room_y) {
        super(room_x, room_y);
    }

    public void move(Player player) {}

    public void shoot(ArrayList<Projectile> projectiles) {}

    public void shoot(Player player) {}
}

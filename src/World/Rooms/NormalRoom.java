package World.Rooms;

import Entity.Enemies.Ghost;
import World.Room;

public class NormalRoom extends Room {
    public NormalRoom(int layout_type){
        super(layout_type);
    }

    public void generateEnemies() {
        enemies.add(new Ghost(1400, 750));
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y);
    }
}
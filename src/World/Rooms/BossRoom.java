package World.Rooms;

import World.Room;

public class BossRoom extends Room {
    public BossRoom(int layout_type){
        super(layout_type);
    }

    public void generateEnemies(int current_level) {}

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y);
    }
}

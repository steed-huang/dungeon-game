package World.Rooms;

import World.Room;

public class NormalRoom extends Room {
    public NormalRoom(int layout_type){
        super(layout_type);
    }

    public void generateEnemies() {}

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y);
    }
}
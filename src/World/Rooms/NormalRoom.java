package World.Rooms;

import World.LayoutLoader;
import World.Room;

public class NormalRoom extends Room {
    public NormalRoom(){
        this.layout = LayoutLoader.getLayout(0);
    }

    public void generateEnemies() {}
    public void draw() {}
}
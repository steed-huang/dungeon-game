package World.Rooms;

import Entity.Enemies.*;
import Main.RandomGenerator;
import World.Room;

public class NormalRoom extends Room {
    public NormalRoom(int layout_type){
        super(layout_type);
    }

    public void generateEnemies() {
        int temp_ghost_count = RandomGenerator.getRandom(3, 8);
        while (temp_ghost_count > 0) {
            int row = RandomGenerator.getRandom(1, layout.grid.length-2);
            int col = RandomGenerator.getRandom(1, layout.grid.length-2);
            if (layout.grid[row][col] == 1){
                // not next to wall
                if (layout.grid[row+1][col] != 2 && layout.grid[row-1][col] != 2 &&  layout.grid[row][col-1] != 2 &&  layout.grid[row][col+1] != 2){
                    int enemy = RandomGenerator.getRandom(1, 3);
                    if (enemy == 1) enemies.add(new Ghost(col*50+25, row*50+25));
                    if (enemy == 2) enemies.add(new Slime(col*50+25, row*50+25));
                    if (enemy == 3) enemies.add(new Skeleton(col*50+25, row*50+25));
                    temp_ghost_count--;
                }
            }
        }
    }

    public void draw(java.awt.Graphics2D g, int x, int y) {
        super.draw(g, x, y);
    }
}
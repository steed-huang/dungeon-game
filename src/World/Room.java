package World;

import Entity.CollisionBox;
import Entity.Enemy;
import java.util.ArrayList;

public abstract class Room {
    protected Layout layout; // instance room layout
    protected int layout_type;
    protected ArrayList<CollisionBox> cbs = new ArrayList<>(); // list of wall cbs
    protected ArrayList<Enemy> enemies = new ArrayList<>(); // list of enemies
    protected boolean[] doors = {false, false, false, false};

    protected Room(int layout_type){
        this.layout = LayoutLoader.getLayout(layout_type);
        this.layout_type = layout_type;
    }

    public void setDoors(boolean up, boolean down, boolean left, boolean right){
        doors[0] = up; doors[1] = down; doors[2] = left; doors[3] = right;
    }

    public void generateWallCB(){
        String type;

        // generate collision boxes
        for (int i = 0; i < layout.grid.length; i++){
            for (int j = 0; j < layout.grid.length; j++){
                type = "wall";
                // door cb
                if ((doors[0] && (i == 0 && j == 14 || i == 0 && j == 15)) || (doors[1] && (i == 29 && j == 14 || i == 29 && j == 15)) || // 4 possible doors
                    (doors[2] && (i == 14 && j == 0 || i == 15 && j == 0)) || (doors[3] && (i == 14 && j == 29 || i == 15 && j == 29))) type = "door";

                // wall cb
                if (layout.grid[i][j] == 2) cbs.add(new CollisionBox(type, 50, 50, j * 50 + 25, i * 50 + 25));
            }
        }
    }

    public abstract void generateEnemies();

    public ArrayList<CollisionBox> getCBS() { return cbs; }
    public ArrayList<Enemy> getEnemies() { return enemies; }

    public int getLayoutType(){ return this.layout_type; }

    public void draw(java.awt.Graphics2D g, int x, int y){
        g.drawImage(layout.sprite, 512-x, 384-y, null);
        if (doors[0]) g.drawImage(layout.door_hor, 512 + 700 - x , 384 - y, null);
        if (doors[1]) g.drawImage(layout.door_hor, 512-x+700, 384-y+1450, null);
        if (doors[2]) g.drawImage(layout.door_ver, 512-x, 384-y+700, null);
        if (doors[3]) g.drawImage(layout.door_ver, 512-x+1450, 384-y+700, null);
    }
}
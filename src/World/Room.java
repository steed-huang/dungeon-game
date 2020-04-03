package World;

import Entity.CollisionBox;
import java.util.ArrayList;

public abstract class Room {
    protected Layout layout; // instance room layout
    protected int layout_type;
    protected ArrayList<CollisionBox> cbs = new ArrayList<>();

    protected Room(int layout_type){
        this.layout = LayoutLoader.getLayout(layout_type);
        this.layout_type = layout_type;
        
        // generate collision boxes
        for (int i = 0; i < layout.grid.length; i++){
            for (int j = 0; j < layout.grid.length; j++){
                if (layout.grid[i][j] == 2) cbs.add(new CollisionBox(50, 50, j * 50 + 25, i * 50 + 25));
            }
        }
    }

    public ArrayList<CollisionBox> getCBS() { return cbs; }
    public abstract void generateEnemies();

    public int getLayoutType(){ return this.layout_type; }

    public void draw(java.awt.Graphics2D g, int x, int y){
        g.drawImage(layout.sprite, 512-x, 384-y, null);
    }
}
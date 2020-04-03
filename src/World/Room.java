package World;

public abstract class Room {
    protected Layout layout; // instance room layout
    protected int layout_type;

    protected Room(int layout_type){
        this.layout = LayoutLoader.getLayout(layout_type);
        this.layout_type = layout_type;
    }

    public abstract void generateEnemies();

    public int getLayoutType(){ return this.layout_type; }

    public void draw(java.awt.Graphics2D g, int x, int y){
        g.drawImage(layout.sprite, 512-x, 384-y, null);
    }
}
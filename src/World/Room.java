package World;

public abstract class Room {
    protected Layout layout; // instance room layout
    protected int layout_type;
    protected Room(int layout_type){
        this.layout = LayoutLoader.getLayout(layout_type);
        this.layout_type = layout_type;
    }

    public int getLayoutType(){ return this.layout_type; }
    public abstract void generateEnemies();
    public abstract void draw();
}
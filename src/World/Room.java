package World;

public abstract class Room {
    protected Layout layout; // instance room layout
    protected Room(int layout_type){
        this.layout = LayoutLoader.getLayout(layout_type);
    }

    public abstract void generateEnemies();
    public abstract void draw();
}
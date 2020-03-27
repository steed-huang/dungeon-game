package World;

public abstract class Room {
    protected Layout layout; // instance room layout

    public abstract void generateEnemies();
    public abstract void draw();
}
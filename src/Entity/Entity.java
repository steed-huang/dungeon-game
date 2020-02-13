package Entity;

public abstract class Entity {
    // positioning and speed
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    protected int width;
    protected int height;

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int x_pos() { return (int)x; }
    public int y_pos() { return (int)y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public abstract void draw();
}
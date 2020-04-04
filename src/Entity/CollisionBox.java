package Entity;

import java.awt.*;

public class CollisionBox {
    private double r_x;
    private double r_y;
    private double width;
    private double height;

    public CollisionBox(double width, double height, double room_x, double room_y){
        this.width = width;
        this.height = height;
        this.r_x = room_x;
        this.r_y = room_y;
    }

    public void setPosition(double x, double y) {
        r_x = x;
        r_y = y;
    }

    public double[] getLimits() {
        double left = r_x - width/2;
        double top = r_y - height/2;
        double right = r_x + width/2;
        double bottom = r_y + height/2;
        return new double[]{left, top, right, bottom};
    }

    public boolean collidesWith(CollisionBox cb) {
        double[] c1 = this.getLimits();
        double[] c2 = cb.getLimits();
        return !(c2[0] >= c1[2] || c2[2] <= c1[0] || c2[1] >= c1[3] || c2[3] <= c1[1]);
    }

    // for walls
    public double[] getLimits(double x, double y) {
        double left = x - width/2;
        double top = y - height/2;
        double right = x + width/2;
        double bottom = y + height/2;
        return new double[]{left, top, right, bottom};
    }

    public boolean collidesWith(CollisionBox cb, double x, double y) {
        double[] c1 = this.getLimits(x, y);
        double[] c2 = cb.getLimits();
        return !(c2[0] >= c1[2] || c2[2] <= c1[0] || c2[1] >= c1[3] || c2[3] <= c1[1]);
    }

    public void draw(java.awt.Graphics2D g, int x, int y){
        g.setColor(new Color(255, 0, 0));
        g.drawRect(512-x+(int)(r_x-width/2), 384-y+(int)(r_y-height/2), (int)width, (int)height);
    }
}
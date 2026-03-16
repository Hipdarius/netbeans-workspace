import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class MovingObject extends Rectangle {
    private static final long serialVersionUID = 1L;

    private Color color;
    private int dX;
    private int dY;

    public MovingObject(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
        dX = 0;
        dY = 0;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDX() {
        return dX;
    }

    public void setDX(int dX) {
        this.dX = dX;
    }

    public int getDY() {
        return dY;
    }

    public void setDY(int dY) {
        this.dY = dY;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void move() {
        x += dX;
        y += dY;
    }
}

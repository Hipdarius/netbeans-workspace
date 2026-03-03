import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
    protected Point from;
    protected Point to;
    protected Color color;

    public Shape(Point from, Point to, Color color) {
        this.from = from;
        this.to = to;
        this.color = color;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    public abstract void draw(Graphics g);

    @Override
    public String toString() {
        return "(" + from.x + ", " + from.y + ") -> (" + to.x + ", " + to.y + ")";
    }
}

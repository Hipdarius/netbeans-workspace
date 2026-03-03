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

    public Shape(Point from, Point to) {
        this(from, to, Color.BLACK);
    }

    public abstract void draw(Graphics g);

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

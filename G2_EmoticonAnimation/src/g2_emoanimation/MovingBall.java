package g2_emoanimation;

import java.awt.Color;
import java.awt.Graphics2D;

public class MovingBall {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private int radius;
    private Color outline = Color.BLACK;

    public MovingBall(double x, double y, double vx, double vy, int radius) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public void step(int panelWidth, int panelHeight) {
        if (panelWidth <= 0 || panelHeight <= 0) {
            return;
        }
        x += vx;
        y += vy;

        // Bounce on the left/right edges.
        if (x - radius < 0) {
            x = radius;
            vx = -vx;
        } else if (x + radius > panelWidth) {
            x = panelWidth - radius;
            vx = -vx;
        }

        // Bounce on the top/bottom edges.
        if (y - radius < 0) {
            y = radius;
            vy = -vy;
        } else if (y + radius > panelHeight) {
            y = panelHeight - radius;
            vy = -vy;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(outline);
        int diameter = radius * 2;
        g2.drawOval((int) Math.round(x - radius), (int) Math.round(y - radius), diameter, diameter);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public int getRadius() {
        return radius;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setOutline(Color outline) {
        this.outline = outline;
    }

    public Color getOutline() {
        return outline;
    }
}

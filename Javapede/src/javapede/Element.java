package javapede;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Element {
    public static final int GRID_W = 30;
    public static final int GRID_H = 32;

    private static final Random RNG = new Random();

    private int x;
    private int y;
    private int direction;
    private int points;
    private Color color;

    public Element(int x, int y, int direction, Color color) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.color = color;
        this.points = RNG.nextInt(10) + 1;
    }

    public void draw(Graphics2D g2, int side, int offX, int offY) {
        int px = offX + x * side;
        int py = offY + y * side;
        g2.setColor(color);
        g2.fillOval(px, py, side, side);
    }

    public void move() {
        if (direction == 0) {
            return;
        }
        int nx = x + direction;
        if (nx >= 0 && nx <= GRID_W - 1) {
            x = nx;
            return;
        }
        direction = -direction;
        if (y >= GRID_H - 1) {
            y = Math.max(0, y - 1);
        } else {
            y = Math.min(GRID_H - 1, y + 1);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

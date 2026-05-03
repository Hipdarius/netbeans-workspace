
import java.awt.Color;
import java.awt.Graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Darius
 */
public class Ball {
    private double x, y;
    private int r;
    private Color color;

    public Ball(double x, double y, int r, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x - r, (int) y - r, r * 2, r * 2);

        int rr = r / 4;
        int cx = (int) (x + r / 2.0);
        int cy = (int) (y - r / 2.0);
        g.setColor(Color.WHITE);
        g.fillOval(cx - rr, cy - rr, rr * 2, rr * 2);
    }

    public boolean isTouching(Ball pBall) {
        double distance = Math.sqrt(Math.pow(pBall.x - this.x, 2) + Math.pow(pBall.y - this.y, 2));
        return distance <= this.r + pBall.r;
    }
}

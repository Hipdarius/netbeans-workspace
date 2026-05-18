package models;


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
    protected int r;
    protected double x, y;
    protected Color color;

    public Ball(double x, double y, int r, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
    }

    public int getR() {
        return r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setR(int r) {
        this.r = r;
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
        g.setColor(Color.WHITE);
        g.fillOval((int) (x + r / 2), (int) (y - r / 2), r / 4, r / 4);
    }

    public boolean isTouching(Ball ball) {
        double distance = Math.sqrt(Math.pow(ball.x - x, 2) + Math.pow(ball.y - y, 2));
        return distance <= r + ball.r;
    }

    @Override
    public String toString() {
        return x + ";" + y + ";" + r + ";" + String.format("#%06X", color.getRGB() & 0xFFFFFF);
    }
}

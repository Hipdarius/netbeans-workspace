/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;

/**
 *
 * @author Darius
 */
public class MovingBall extends Ball {

    private double dX, dY;

    public MovingBall(double x, double y, int r, Color color) {
        super(x, y, r, color);
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public double getdX() {
        return dX;
    }

    public double getdY() {
        return dY;
    }

    public void move() {
        setX(getX() + dX);
        setY(getY() + dY);
        dY += 0.2;
    }
}

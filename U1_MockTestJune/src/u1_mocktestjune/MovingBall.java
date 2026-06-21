package u1_mocktestjune;

import java.awt.Color;

public class MovingBall extends Ball {

    private double dX = 0;
    private double dY = 0;

    public MovingBall(double x, double y, int r, Color color) {
        super(x, y, r, color);
        // The movement steps start at 0.
    }

    public double getdX() {
        // Return the horizontal movement step.
        return 0;
    }

    public double getdY() {
        // Return the vertical movement step.
        return 0;
    }

    public void setdX(double dX) {
        // Update the horizontal movement step.
    }

    public void setdY(double dY) {
        // Update the vertical movement step.
    }

    public void move() {
        // Move the ball and increase dY slightly for gravity.
    }
}

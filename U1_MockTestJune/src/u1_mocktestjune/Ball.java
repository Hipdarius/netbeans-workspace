package u1_mocktestjune;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    private double x;
    private double y;
    private int r;
    private Color color;

    public Ball(double x, double y, int r, Color color) {
        // Initialize all attributes with the parameters.
    }

    public double getX() {
        // Return the x coordinate of the center.
        return 0;
    }

    public void setX(double x) {
        // Update the x coordinate.
    }

    public double getY() {
        // Return the y coordinate of the center.
        return 0;
    }

    public void setY(double y) {
        // Update the y coordinate.
    }

    public int getR() {
        // Return the radius.
        return 0;
    }

    public Color getColor() {
        // Return the fill color.
        return null;
    }

    public void draw(Graphics g) {
        // Draw the filled ball and a small white highlight.
    }

    public boolean isTouching(Ball otherBall) {
        // Return true if the two balls touch or overlap.
        return false;
    }

    @Override
    public String toString() {
        // Return one file line in the format x;y;r;colorRGB.
        return "";
    }
}

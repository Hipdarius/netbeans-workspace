import java.awt.Color;
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phili
 */
public class NormalizedRectangle extends Rectangle {

    public NormalizedRectangle(Point from, Point to, Color color) {
        // Normalize coordinates so width/height are never negative.
        int x1 = from.x;
        int y1 = from.y;
        int x2 = to.x;
        int y2 = to.y;

        // If width is negative, swap x values so left is x1 and right is x2.
        if (x2 < x1) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }

        // If height is negative, swap y values so top is y1 and bottom is y2.
        if (y2 < y1) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }

        super(new Point(x1, y1), new Point(x2, y2), color);
    }
}

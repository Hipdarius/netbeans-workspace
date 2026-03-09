
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author darius
 */
public class Line extends Shape {

    public Line(Point from, Point to, Color color) {
        super(from, to, color);
    }

    public Line(Point from, Point to) {
        super(from, to, Color.BLACK);
    }
    
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(from.x, from.y, to.x, to.y);
    }
    
    @Override
    public String toString() {
        return "L: " + super.toString();
    }
}

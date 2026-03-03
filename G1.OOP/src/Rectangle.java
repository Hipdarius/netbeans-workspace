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
public class Rectangle extends Shape {

    public Rectangle(Point from, Point to, Color color) {
        super(from, to, color);
    }

    public Rectangle(Point from, Point to) {
        super(from, to);
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.drawRect(from.x, from.y, to.x-from.x, to.y-from.y);
    }
}

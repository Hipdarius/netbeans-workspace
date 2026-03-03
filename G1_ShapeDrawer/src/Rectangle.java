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
    
    public void draw(Graphics g){
        g.setColor(color);
        int x = Math.min(from.x, to.x);
        int y = Math.min(from.y, to.y);
        int w = Math.abs(to.x - from.x);
        int h = Math.abs(to.y - from.y);
        g.drawRect(x, y, w, h);
    }
    
    @Override
    public String toString() {
        return "R: " + super.toString();
    }
}

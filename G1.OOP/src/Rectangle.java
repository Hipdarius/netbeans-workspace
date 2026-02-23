
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author phili
 */
public class Rectangle {
    private Point from;
    private Point to;
    private Color color;

    public Rectangle(Point from, Point to, Color color) {
        this.from = from;
        this.to = to;
        this.color = color;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        g.drawRect(from.x, from.y, to.x-from.x, to.y-from.y);
    }

    public void setTo(Point to) {
        this.to = to;
    }
    
    
    
    
    
}

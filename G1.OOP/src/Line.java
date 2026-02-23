
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
public class Line {

    private Point from;
    private Point to;
    private Color color;

    public Line(Point from, Point to, Color color) {
        this.from = from;
        this.to = to;
        this.color = color;
    }

    public Line(Point from, Point to) {
        this.from = from;
        this.to = to;
    }
    
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(from.x, from.y, to.x, to.y);
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}

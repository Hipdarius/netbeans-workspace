package javapede;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Stroke;

public class Bullet extends Element {
    public Bullet(int x, int y) {
        super(x, y, 0, Color.WHITE);
    }

    @Override
    public void move() {
        setY(getY() - 1);
    }

    @Override
    public void draw(Graphics2D g2, int side, int offX, int offY) {
        int px = offX + getX() * side + side / 2;
        int py = offY + getY() * side;
        Stroke old = g2.getStroke();
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(px, py, px, py + side);
        g2.setStroke(old);
    }
}

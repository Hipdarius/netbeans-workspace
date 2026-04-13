package g6_spaceinvadersii;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Invader extends MovingObject {

    public Invader(int x, int y) {
        super(x, y, 30, 30, Color.GREEN);
        int r = (int) (Math.random() * (255 - 0 + 1) + 0);
        int g = (int) (Math.random() * (255 - 0 + 1) + 0);
        int b = (int) (Math.random() * (255 - 0 + 1) + 0);
        color = new Color(r, g, b);
    }

    public boolean collidesWith(MovingObject movingObject) {
        /*return x < movingObject.x + movingObject.width
            && x + width > movingObject.x
            && y < movingObject.y + movingObject.height
            && y + height > movingObject.y;*/
        
        Rectangle r1 = new Rectangle(x, y, width, height);
        Rectangle r2 = new Rectangle (x, y, width, height);
        
        return r1.intersects(r2);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x + width, y, x, y + height);
    }
}

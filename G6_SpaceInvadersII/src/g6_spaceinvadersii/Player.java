package g6_spaceinvadersii;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends MovingObject {

    public Player(int xCenter, int y) {
        super(xCenter - 10, y, 20, 20, Color.BLUE);
    }

    public void setNewXCenter(int xCenter, int panelWidth) {
        x = xCenter - width / 2;
        if (x < 0) {
            x = 0;
        }
        if (x > panelWidth - width) {
            x = panelWidth - width;
        }
        
        if(getX() < 0 && getX() + 20 > width) {
            setX(xCenter);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        int cx = x + width / 2;
        g.setColor(color);
        g.drawLine(cx, y, x, y + height);
        g.drawLine(x, y + height, x + width, y + height);
        g.drawLine(x + width, y + height, cx, y);
    }
}

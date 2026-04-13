package g6_spaceinvadersii;

import java.awt.Color;
import java.awt.Graphics;

public class Missile extends MovingObject {

    public Missile(int xCenter, int y) {
        super(xCenter - 2, y, 4, 10, Color.RED);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}

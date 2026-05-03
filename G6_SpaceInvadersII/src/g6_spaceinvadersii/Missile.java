package g6_spaceinvadersii;

import java.awt.Color;
import java.awt.Graphics;

public class Missile extends MovingObject {

    public Missile(int xCenter, int y) {
        super(xCenter - 2, y, 5, 8, Color.RED);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}

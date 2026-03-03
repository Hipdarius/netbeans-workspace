package javapede;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Element {
    public Player(int x, int y) {
        super(x, y, 0, Color.CYAN);
    }

    @Override
    public void move() {
        int dir = getDirection();
        if (dir == 0) {
            return;
        }
        int nx = getX() + dir;
        if (nx >= 0 && nx <= GRID_W - 1) {
            setX(nx);
        }
    }

    @Override
    public void draw(Graphics2D g2, int side, int offX, int offY) {
        super.draw(g2, side, offX, offY);
        int px = offX + getX() * side;
        int py = offY + getY() * side;
        int d = side / 2;

        g2.setColor(Color.RED);
        g2.fillOval(px, py, d, d);
        g2.fillOval(px + d, py, d, d);
    }
}

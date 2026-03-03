package javapede;

import java.awt.Color;
import java.awt.Graphics2D;

public class Mushroom extends Element {
    private int hits;

    public Mushroom(int x, int y) {
        super(x, y, 0, Color.MAGENTA);
        this.hits = 0;
    }

    public void touch() {
        hits++;
    }

    public int getHits() {
        return hits;
    }

    @Override
    public void draw(Graphics2D g2, int side, int offX, int offY) {
        Color c;
        if (hits == 0) {
            c = Color.MAGENTA;
        } else if (hits == 1) {
            c = Color.BLUE;
        } else {
            c = Color.GREEN;
        }

        int px = offX + getX() * side;
        int py = offY + getY() * side;

        g2.setColor(c);
        g2.fillOval(px, py, side, side);

        g2.setColor(Color.BLACK);
        g2.fillRect(px, py + side / 2, side, side / 2);

        g2.setColor(c);
        g2.fillRect(px + side / 4, py + side / 2, side / 2, side / 2);
    }
}

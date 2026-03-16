import java.awt.Color;
import java.awt.Graphics;

public class Player extends MovingObject {
    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 30;
    private static final int HEIGHT = 20;
    private static final int INNER_SIZE = 12;

    public Player(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.ORANGE);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int innerX = x + (width - INNER_SIZE) / 2;
        int innerY = y + (height - INNER_SIZE) / 2;

        g.setColor(Color.BLACK);
        g.fillRect(innerX, innerY, INNER_SIZE, INNER_SIZE);
    }
}


import java.awt.Color;
import java.awt.Graphics;

public class Missile extends MovingObject {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 10;
    private static final int FLAME_HEIGHT = 5;

    public Missile(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.YELLOW);
        setDX(0);
        setDY(-3);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int flameBaseX = x + width / 2;
        int flameTopY = y + height;
        int flameBottomY = flameTopY + FLAME_HEIGHT;

        g.setColor(Color.RED);
        g.drawLine(flameBaseX, flameTopY, x, flameBottomY);
        g.drawLine(flameBaseX, flameTopY, x + width, flameBottomY);
    }
}

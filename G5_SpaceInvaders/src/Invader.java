import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class Invader extends MovingObject {
    private static final int SIZE = 30;
    private static final int MIN_SPEED = -2;
    private static final int MAX_SPEED = 2;

    public Invader(int x, int y) {
        super(x, y, SIZE, SIZE, Color.GREEN);
        setDX(randomSpeed());
        setDY(randomSpeed());
    }

    private int randomSpeed() {
        return ThreadLocalRandom.current().nextInt(MIN_SPEED, MAX_SPEED + 1);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x + width, y + height);
        g.drawLine(x + width, y, x, y + height);
    }

    @SuppressWarnings("deprecation")
    public void move(int pWidth, int pHeight) {
        move();

        int maxX = Math.max(0, pWidth - width);
        int maxY = Math.max(0, (pHeight * 3) / 4 - height);

        if (x < 0) {
            x = 0;
            setDX(-getDX());
        } else if (x > maxX) {
            x = maxX;
            setDX(-getDX());
        }

        if (y < 0) {
            y = 0;
            setDY(-getDY());
        } else if (y > maxY) {
            y = maxY;
            setDY(-getDY());
        }
    }
}

package e9_fallingballs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Balls {
    private final ArrayList<Ball> aBalls = new ArrayList<>();
    private final Random rnd = new Random();

    public Balls(int n, int pWidth, int pHeight) {
        for (int i = 0; i < n; i++) {
            int radius = random(20, 50);
            int minY = radius;
            int maxY = Math.max(minY, (pHeight / 4) - radius);
            int minX = radius;
            int maxX = Math.max(minX, pWidth - radius);

            int x = random(minX, maxX);
            int y = random(minY, maxY);
            double friction = random(65, 80) / 100.0;
            int red = random(150, 220);
            int green = random(0, 60);
            int blue = random(0, 60);

            Ball ball = new Ball(x, y, radius, new Color(red, green, blue), friction);
            aBalls.add(ball);
        }
    }

    public static int random(int pMin, int pMax) {
        if (pMax < pMin) {
            return pMin;
        }
        return pMin + (int) Math.round(Math.random() * (pMax - pMin));
    }

    public void draw(Graphics2D g2) {
        for (Ball ball : aBalls) {
            ball.draw(g2);
        }
    }

    public void dropBall() {
        for (Ball ball : aBalls) {
            if (!ball.isFalling()) {
                ball.drop();
                break;
            }
        }
    }

    public void move(int pHeight) {
        for (Ball ball : aBalls) {
            ball.move(pHeight);
        }
    }
}

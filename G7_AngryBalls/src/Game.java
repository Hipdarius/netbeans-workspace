
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Darius
 */
public class Game {

    private int width, height;
    private MovingBall playerBall;
    private ArrayList<Ball> alBalls;
    private Point mousePosition = null;

    public Game(int pWidth, int pHeight) {
        this.width = pWidth;
        this.height = pHeight;
        this.alBalls = new ArrayList<>();

        int r = 20;
        int minX = width / 2 + r;
        int maxX = width - r;
        int minY = height / 4 + r;
        int maxY = height - r;

        for (int i = 0; i < 10; i++) {
            double x = Math.random() * (maxX - minX) + minX;
            double y = Math.random() * (maxY - minY) + minY;
            alBalls.add(new Ball(x, y, r, Color.RED));
        }
        playerBall = new MovingBall(20, height - 20, 20, Color.GREEN);
    }

    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public void setPlayerBallSteps() {
        if (mousePosition != null) {
            playerBall.setdX((mousePosition.x - playerBall.getX()) / 20.0);
            playerBall.setdY((mousePosition.y - playerBall.getY()) / 20.0);
        }
    }

    public void draw(Graphics g) {
        for (Ball ball : alBalls) {
            ball.draw(g);
        }

        playerBall.draw(g);

        // draw Wall
        g.setColor(Color.YELLOW);
        g.fillRect(width / 3, height / 2, 10, height / 2);

        // draw force Line
        if (mousePosition != null) {
            g.setColor(Color.WHITE);
            g.drawLine((int) playerBall.getX(), (int) playerBall.getY(), mousePosition.x, mousePosition.y);
        }
    }

    public int move() {
        playerBall.move();

        // test if playerBall hits wall
        int r = playerBall.getR();
        int wallLeft = width / 3;
        int wallRight = wallLeft + 10;
        int wallTop = height / 2;
        double px = playerBall.getX();
        double py = playerBall.getY();

        boolean horizontalOverlap = (px + r >= wallLeft) && (px - r <= wallRight);
        boolean verticalInRange = py >= wallTop && py <= height;

        if (horizontalOverlap && verticalInRange) {
            playerBall.setdX(-playerBall.getdX());
            if (px < (wallLeft + wallRight) / 2.0) {
                playerBall.setX(wallLeft - r - 1);
            } else {
                playerBall.setX(wallRight + r + 1);
            }
        }

        // remove balls that are hit
        Iterator<Ball> it = alBalls.iterator();
        while (it.hasNext()) {
            Ball ball = it.next();
            if (playerBall.isTouching(ball)) {
                it.remove();
            }
        }

        // test if playerBall quits playground
        if (playerBall.getX() - playerBall.getR() > width
                || playerBall.getX() + playerBall.getR() < 0
                || playerBall.getY() - playerBall.getR() > height) {
            return 1;
        }
        return 0;
    }

    public void playerBallReset() {
        playerBall = new MovingBall(20, height - 20, 20, Color.GREEN);
    }

    public boolean isOver() {
        return alBalls.isEmpty();
    }
}

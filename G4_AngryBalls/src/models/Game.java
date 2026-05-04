package models;


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
    private ArrayList<Ball> alBalls = new ArrayList<>();
    private Point mousePosition = null;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;

        int minX = width / 2 + 20;
        int maxX = width - 20;
        int minY = height / 4 + 20;
        int maxY = height - 20;

        for (int i = 0; i < 10; i++) {
            double x = Math.random() * (maxX - minX) + minX;
            double y = Math.random() * (maxY - minY) + minY;
            alBalls.add(new Ball(x, y, 20, Color.RED));
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

        g.setColor(Color.YELLOW);
        g.fillRect(width / 3, height / 2, 10, height / 2);

        if (mousePosition != null) {
            g.setColor(Color.WHITE);
            g.drawLine((int) playerBall.getX(), (int) playerBall.getY(), mousePosition.x, mousePosition.y);
        }
    }

    public int move() {
        playerBall.move();

        int r = playerBall.getR();
        double x = playerBall.getX();
        double y = playerBall.getY();

        if (x + r >= width / 3 && x - r <= width / 3 + 10 && y >= height / 2) {
            playerBall.setdX(-playerBall.getdX());
            playerBall.setX(width / 3 - r - 1);
        }

        Iterator<Ball> it = alBalls.iterator();
        while (it.hasNext()) {
            if (playerBall.isTouching(it.next())) {
                it.remove();
            }
        }

        if (x - r > width || x + r < 0 || y - r > height) {
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

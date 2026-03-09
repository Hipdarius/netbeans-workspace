
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author darius
 */
public class MovingBalls {
    private static final int MIN_COUNT = 25;
    private static final int MAX_COUNT = 40;
    private static final int MIN_RADIUS = 18;
    private static final int MAX_RADIUS = 35;
    private static final int MIN_SPEED = -4;
    private static final int MAX_SPEED = 4;

    private ArrayList<Ball> alBalls = new ArrayList();
    public void add(int n, int w, int h) {
        for (int i = 0; i < n; i++) {
            int radius = rand(MIN_RADIUS, MAX_RADIUS);
            double x = radius + rand(0, Math.max(0, w - radius * 2));
            double y = radius + rand(0, Math.max(0, h - radius * 2));
            double xStep = randomStep();
            double yStep = randomStep();

            int roll = rand(1, 100);
            Ball ball;
            if (roll <= 10) {
                ball = new Ball(x, y, radius, xStep, yStep);
            } else if (roll <= 35) {
                ball = new EmoSmile(x, y, radius, xStep, yStep);
            } else if (roll <= 40) {
                ball = new EmoSad(x, y, radius, xStep, yStep);
            } else if (roll <= 60) {
                ball = new EmoBigSmile(x, y, radius, xStep, yStep);
            } else if (roll <= 70) {
                ball = new EmoSurprised(x, y, radius, xStep, yStep);
            } else {
                ball = new EmoFreaky(x, y, radius, xStep, yStep);
            }
            alBalls.add(ball);
        }
    }

    public void addRandom(int w, int h) {
        int count = rand(MIN_COUNT, MAX_COUNT);
        add(count, w, h);
    }

    private double randomStep() {
        for (int v = 0; v == 0;) {
            v = rand(MIN_SPEED, MAX_SPEED);
            if (v != 0) {
                return v;
            }
        }
        return MIN_SPEED == 0 ? 1 : MIN_SPEED;
    }

    private int rand(int min, int max) {
        return (int)(Math.random() * (max - min + 1)) + min;
    }
    
    public void draw(Graphics g) {
        for (int i = 0; i < alBalls.size(); i++) {
            alBalls.get(i).draw(g);
        }
    }
    
    public void doStep(int w,int h) {
        for (int i = 0; i < alBalls.size(); i++) {
            alBalls.get(i).doStep(w, h);
        }
    }
    
    public void removeBallsAt(int pX,int pY) {
        for (int i = alBalls.size()-1; i >= 0; i--) {
            Ball get = alBalls.get(i);
            if(get.isInside(pX,pY)) {
                alBalls.remove(i);
            }
        }
    }
}


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

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
    private final Random rng = new Random();

    public void add(int n, int w, int h) {
        for (int i = 0; i < n; i++) {
            int radius = MIN_RADIUS + rng.nextInt(MAX_RADIUS - MIN_RADIUS + 1);
            double x = radius + rng.nextInt(Math.max(1, w - radius * 2));
            double y = radius + rng.nextInt(Math.max(1, h - radius * 2));
            double xStep = randomStep();
            double yStep = randomStep();

            int type = rng.nextInt(5);
            Ball ball;
            if (type == 0) {
                ball = new Ball(x, y, radius, xStep, yStep);
            } else if (type == 1) {
                ball = new EmoSmile(x, y, radius, xStep, yStep);
            } else if (type == 2) {
                ball = new EmoSad(x, y, radius, xStep, yStep);
            } else if (type == 3) {
                ball = new EmoBigSmile(x, y, radius, xStep, yStep);
            } else {
                ball = new EmoSurprised(x, y, radius, xStep, yStep);
            }
            alBalls.add(ball);
        }
    }

    public void addRandom(int w, int h) {
        int count = MIN_COUNT + rng.nextInt(MAX_COUNT - MIN_COUNT + 1);
        add(count, w, h);
    }

    private double randomStep() {
        int v;
        do {
            v = rng.nextInt(MAX_SPEED - MIN_SPEED + 1) + MIN_SPEED;
        } while (v == 0);
        return v;
    }
    
    public void draw(Graphics2D g2) {
        for (int i = 0; i < alBalls.size(); i++) {
            alBalls.get(i).draw(g2);
        }
    }
    
    public void doStep(int w,int h) {
        for (int i = 0; i < alBalls.size(); i++) {
            alBalls.get(i).doStep(w, h);
        }
    }
    
    public void removeBallsAt(int pX,int pY) {
        for (int i = alBalls.size()-1; i>=0; i--) {
            Ball get = alBalls.get(i);
            if(get.isInside(pX,pY)) {
                alBalls.remove(i);
            }
            
        }
        
    }
    
}

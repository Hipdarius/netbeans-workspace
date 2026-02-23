package g2_emoanimation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class AnimationPanel extends javax.swing.JPanel implements ActionListener {
    private static final int MIN_COUNT = 25;
    private static final int MAX_COUNT = 40;
    private static final int MIN_RADIUS = 18;
    private static final int MAX_RADIUS = 35;
    private static final int TIMER_DELAY_MS = 20;

    private final List<MovingBall> balls = new ArrayList<>();
    private final Random rng = new Random();
    private Timer timer;

    public AnimationPanel() {
        initComponents();
        initCustom();
    }

    private void initCustom() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
        initBalls(getPreferredSize().width, getPreferredSize().height);
        timer = new Timer(TIMER_DELAY_MS, this);
        timer.start();
    }

    private void initBalls(int width, int height) {
        int count = MIN_COUNT + rng.nextInt(MAX_COUNT - MIN_COUNT + 1);
        for (int i = 0; i < count; i++) {
            int radius = MIN_RADIUS + rng.nextInt(MAX_RADIUS - MIN_RADIUS + 1);
            double x = radius + rng.nextInt(Math.max(1, width - radius * 2));
            double y = radius + rng.nextInt(Math.max(1, height - radius * 2));
            double vx = randomVelocity();
            double vy = randomVelocity();

            int type = rng.nextInt(5);
            MovingBall ball;
            if (type == 0) {
                ball = new MovingBall(x, y, vx, vy, radius);
            } else if (type == 1) {
                ball = new EmoSmile(x, y, vx, vy, radius);
            } else if (type == 2) {
                ball = new EmoSad(x, y, vx, vy, radius);
            } else if (type == 3) {
                ball = new EmoBigSmile(x, y, vx, vy, radius);
            } else {
                ball = new EmoSurprised(x, y, vx, vy, radius);
            }
            balls.add(ball);
        }
    }

    private double randomVelocity() {
        int v;
        do {
            v = rng.nextInt(9) - 4;
        } while (v == 0);
        return v;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (MovingBall ball : balls) {
            ball.draw(g2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int w = getWidth();
        int h = getHeight();
        for (MovingBall ball : balls) {
            ball.step(w, h);
        }
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

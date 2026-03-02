package pong;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.util.Random;

import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pong Game");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);

            GamePanel panel = new GamePanel();
            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class GamePanel extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    private static final int PADDLE_W = 12;
    private static final int PADDLE_H = 90;
    private static final int BALL_SIZE = 12;
    private static final int PADDLE_SPEED = 6;
    private static final int INITIAL_BALL_SPEED = 5;

    private int leftY = (HEIGHT - PADDLE_H) / 2;
    private int rightY = (HEIGHT - PADDLE_H) / 2;

    private int ballX = (WIDTH - BALL_SIZE) / 2;
    private int ballY = (HEIGHT - BALL_SIZE) / 2;
    private int ballVX = INITIAL_BALL_SPEED;
    private int ballVY = INITIAL_BALL_SPEED;

    private int leftScore = 0;
    private int rightScore = 0;

    private boolean leftUp = false;
    private boolean leftDown = false;
    private boolean rightUp = false;
    private boolean rightDown = false;
    private boolean paused = false;
    private boolean aiEnabled = true;

    private final Timer timer;
    private final Random rand = new Random();
    private final AIOpponent ai;

    GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(20, 20, 20));
        setFocusable(true);
        setupKeyBindings();
        ai = new AIOpponent(HEIGHT, PADDLE_H, 5);

        timer = new Timer(16, e -> {
            if (!paused) {
                updateGame();
            }
            repaint();
        });
        timer.start();
    }

    private void setupKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke("pressed W"), "w-pressed");
        im.put(KeyStroke.getKeyStroke("released W"), "w-released");
        im.put(KeyStroke.getKeyStroke("pressed S"), "s-pressed");
        im.put(KeyStroke.getKeyStroke("released S"), "s-released");

        im.put(KeyStroke.getKeyStroke("pressed UP"), "up-pressed");
        im.put(KeyStroke.getKeyStroke("released UP"), "up-released");
        im.put(KeyStroke.getKeyStroke("pressed DOWN"), "down-pressed");
        im.put(KeyStroke.getKeyStroke("released DOWN"), "down-released");

        im.put(KeyStroke.getKeyStroke("pressed SPACE"), "toggle-pause");
        im.put(KeyStroke.getKeyStroke("pressed R"), "reset");
        im.put(KeyStroke.getKeyStroke("pressed A"), "toggle-ai");

        am.put("w-pressed", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { leftUp = true; }
        });
        am.put("w-released", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { leftUp = false; }
        });
        am.put("s-pressed", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { leftDown = true; }
        });
        am.put("s-released", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { leftDown = false; }
        });

        am.put("up-pressed", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { rightUp = true; }
        });
        am.put("up-released", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { rightUp = false; }
        });
        am.put("down-pressed", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { rightDown = true; }
        });
        am.put("down-released", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { rightDown = false; }
        });

        am.put("toggle-pause", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { paused = !paused; }
        });
        am.put("reset", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                leftScore = 0;
                rightScore = 0;
                resetBall(rand.nextBoolean() ? 1 : -1);
            }
        });
        am.put("toggle-ai", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                aiEnabled = !aiEnabled;
            }
        });
    }

    private void updateGame() {
        if (leftUp) leftY -= PADDLE_SPEED;
        if (leftDown) leftY += PADDLE_SPEED;
        if (aiEnabled) {
            rightY = ai.update(rightY, ballX, ballY, ballVX, WIDTH);
        } else {
            if (rightUp) rightY -= PADDLE_SPEED;
            if (rightDown) rightY += PADDLE_SPEED;
        }

        leftY = clamp(leftY, 0, HEIGHT - PADDLE_H);
        rightY = clamp(rightY, 0, HEIGHT - PADDLE_H);

        ballX += ballVX;
        ballY += ballVY;

        if (ballY <= 0 || ballY + BALL_SIZE >= HEIGHT) {
            ballVY = -ballVY;
        }

        Rectangle ball = new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE);
        Rectangle leftPaddle = new Rectangle(20, leftY, PADDLE_W, PADDLE_H);
        Rectangle rightPaddle = new Rectangle(WIDTH - 20 - PADDLE_W, rightY, PADDLE_W, PADDLE_H);

        if (ball.intersects(leftPaddle) && ballVX < 0) {
            ballVX = -ballVX;
            ballX = leftPaddle.x + leftPaddle.width;
            adjustBallAngle(leftPaddle);
        }

        if (ball.intersects(rightPaddle) && ballVX > 0) {
            ballVX = -ballVX;
            ballX = rightPaddle.x - BALL_SIZE;
            adjustBallAngle(rightPaddle);
        }

        if (ballX < -BALL_SIZE) {
            rightScore++;
            resetBall(1);
        } else if (ballX > WIDTH + BALL_SIZE) {
            leftScore++;
            resetBall(-1);
        }
    }

    private void adjustBallAngle(Rectangle paddle) {
        int paddleCenter = paddle.y + paddle.height / 2;
        int ballCenter = ballY + BALL_SIZE / 2;
        int offset = ballCenter - paddleCenter;

        double normalized = Math.max(-1.0, Math.min(1.0, offset / (paddle.height / 2.0)));
        ballVY = (int) Math.round(normalized * 6);
        if (ballVY == 0) {
            ballVY = rand.nextBoolean() ? 1 : -1;
        }

        int speed = Math.min(12, Math.abs(ballVX) + 1);
        ballVX = (ballVX > 0 ? 1 : -1) * speed;
    }

    private void resetBall(int direction) {
        ballX = (WIDTH - BALL_SIZE) / 2;
        ballY = (HEIGHT - BALL_SIZE) / 2;

        int vy = rand.nextInt(5) + 2;
        ballVX = direction * INITIAL_BALL_SPEED;
        ballVY = rand.nextBoolean() ? vy : -vy;
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        for (int y = 0; y < HEIGHT; y += 20) {
            g2.drawLine(WIDTH / 2, y, WIDTH / 2, y + 10);
        }

        g2.fillRect(20, leftY, PADDLE_W, PADDLE_H);
        g2.fillRect(WIDTH - 20 - PADDLE_W, rightY, PADDLE_W, PADDLE_H);
        g2.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        g2.setFont(new Font("SansSerif", Font.BOLD, 36));
        String scoreText = leftScore + "   " + rightScore;
        int textWidth = g2.getFontMetrics().stringWidth(scoreText);
        g2.drawString(scoreText, (WIDTH - textWidth) / 2, 40);

        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        String controls = "W/S move | Up/Down move | SPACE pause | R reset | A toggle AI (" + (aiEnabled ? "ON" : "OFF") + ")";
        g2.drawString(controls, 16, HEIGHT - 14);

        if (paused) {
            g2.setFont(new Font("SansSerif", Font.BOLD, 28));
            String pausedText = "Paused";
            int pw = g2.getFontMetrics().stringWidth(pausedText);
            g2.drawString(pausedText, (WIDTH - pw) / 2, HEIGHT / 2);
        }
    }
}


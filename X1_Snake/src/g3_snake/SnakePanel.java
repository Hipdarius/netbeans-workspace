package g3_snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements ActionListener {
    private static final int CELL_SIZE = 24;
    private static final int GRID_W = 25;
    private static final int GRID_H = 25;
    private static final int PANEL_W = GRID_W * CELL_SIZE;
    private static final int PANEL_H = GRID_H * CELL_SIZE;
    private static final int TICK_MS = 110;

    private final Timer timer;
    private final Random rng;
    private final Deque<Point> snake;

    private Point food;
    private int dx;
    private int dy;
    private int pendingDx;
    private int pendingDy;
    private int score;
    private boolean gameOver;
    private boolean paused;

    public SnakePanel() {
        this.rng = new Random();
        this.snake = new ArrayDeque<>();
        this.timer = new Timer(TICK_MS, this);

        setBackground(new Color(18, 20, 24));
        setPreferredSize(new Dimension(PANEL_W, PANEL_H + 48));
        setFocusable(true);
        addKeyListener(new KeyHandler());

        resetGame();
        timer.start();
    }

    private void resetGame() {
        snake.clear();
        int startX = GRID_W / 2;
        int startY = GRID_H / 2;
        for (int i = 0; i < 4; i++) {
            snake.addLast(new Point(startX - i, startY));
        }

        dx = 1;
        dy = 0;
        pendingDx = dx;
        pendingDy = dy;
        score = 0;
        gameOver = false;
        paused = false;

        spawnFood();
        repaint();
    }

    private void spawnFood() {
        while (true) {
            int fx = rng.nextInt(GRID_W);
            int fy = rng.nextInt(GRID_H);
            Point p = new Point(fx, fy);
            if (!snakeContains(p)) {
                food = p;
                return;
            }
        }
    }

    private boolean snakeContains(Point p) {
        for (Point s : snake) {
            if (s.equals(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver || paused) {
            repaint();
            return;
        }

        dx = pendingDx;
        dy = pendingDy;

        Point head = snake.peekFirst();
        int nx = head.x + dx;
        int ny = head.y + dy;

        if (nx < 0 || ny < 0 || nx >= GRID_W || ny >= GRID_H) {
            gameOver = true;
            repaint();
            return;
        }

        Point newHead = new Point(nx, ny);
        if (snakeContains(newHead)) {
            gameOver = true;
            repaint();
            return;
        }

        snake.addFirst(newHead);
        if (newHead.equals(food)) {
            score++;
            spawnFood();
        } else {
            snake.removeLast();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2);
        drawFood(g2);
        drawSnake(g2);
        drawHud(g2);

        if (gameOver) {
            drawCenteredMessage(g2, "Game Over - Press R to Restart");
        } else if (paused) {
            drawCenteredMessage(g2, "Paused - Press P to Resume");
        }

        g2.dispose();
    }

    private void drawGrid(Graphics2D g2) {
        g2.setColor(new Color(30, 34, 40));
        for (int x = 0; x <= GRID_W; x++) {
            int px = x * CELL_SIZE;
            g2.drawLine(px, 0, px, GRID_H * CELL_SIZE);
        }
        for (int y = 0; y <= GRID_H; y++) {
            int py = y * CELL_SIZE;
            g2.drawLine(0, py, GRID_W * CELL_SIZE, py);
        }
    }

    private void drawFood(Graphics2D g2) {
        if (food == null) {
            return;
        }
        int x = food.x * CELL_SIZE;
        int y = food.y * CELL_SIZE;
        g2.setColor(new Color(233, 91, 105));
        g2.fillOval(x + 4, y + 4, CELL_SIZE - 8, CELL_SIZE - 8);
    }

    private void drawSnake(Graphics2D g2) {
        int i = 0;
        for (Point s : snake) {
            int x = s.x * CELL_SIZE;
            int y = s.y * CELL_SIZE;
            if (i == 0) {
                g2.setColor(new Color(120, 200, 120));
            } else {
                g2.setColor(new Color(80, 170, 120));
            }
            g2.fillRoundRect(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4, 10, 10);
            i++;
        }
    }

    private void drawHud(Graphics2D g2) {
        g2.setColor(new Color(210, 210, 210));
        g2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        int y = PANEL_H + 32;
        g2.drawString("Score: " + score, 12, y);
        g2.drawString("Arrows/WASD to move, P pause, R restart", 140, y);
    }

    private void drawCenteredMessage(Graphics2D g2, String message) {
        g2.setFont(new Font("Segoe UI", Font.BOLD, 22));
        int textWidth = g2.getFontMetrics().stringWidth(message);
        int x = (PANEL_W - textWidth) / 2;
        int y = PANEL_H / 2;
        g2.setColor(new Color(0, 0, 0, 140));
        g2.fillRoundRect(60, y - 40, PANEL_W - 120, 80, 16, 16);
        g2.setColor(new Color(240, 240, 240));
        g2.drawString(message, x, y + 8);
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    setDirection(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    setDirection(0, 1);
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    setDirection(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    setDirection(1, 0);
                    break;
                case KeyEvent.VK_P:
                    if (!gameOver) {
                        paused = !paused;
                    }
                    break;
                case KeyEvent.VK_R:
                    resetGame();
                    break;
                default:
                    break;
            }
        }

        private void setDirection(int ndx, int ndy) {
            if (dx == -ndx && dy == -ndy) {
                return;
            }
            pendingDx = ndx;
            pendingDy = ndy;
        }
    }
}

package e9_fallingballs;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
    private static final double GRAVITY = 0.981;

    private double x;
    private double y;
    private int radius;
    private double dy;
    private double friction;
    private boolean falling;
    private Color color;

    public Ball(double x, double y, int radius, Color color, double friction) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.friction = friction;
        this.dy = 0.0;
        this.falling = false;
    }

    public boolean isFalling() {
        return falling;
    }

    public void drop() {
        falling = true;
    }

    public void move(int pHeight) {
        if (!falling) {
            return;
        }

        dy = dy + GRAVITY;
        y = y + dy;

        if (y + radius > pHeight) {
            y = pHeight - radius;
            dy = dy * (-1) * friction;
        }
    }

    public void draw(Graphics2D g2) {
        int diameter = radius * 2;
        int drawX = (int) Math.round(x - radius);
        int drawY = (int) Math.round(y - radius);

        // Base fill: dark red.
        g2.setColor(color);
        g2.fillOval(drawX, drawY, diameter, diameter);

        // Outer outline: darker than fill.
        Color outline = color.darker().darker();
        g2.setColor(outline);
        g2.drawOval(drawX, drawY, diameter, diameter);

        // Concentric ripple rings (6–10) with alternating lighter/darker red.
        int rings = 6 + (int) Math.round(Math.random() * 4); // 6..10
        int step = Math.max(1, radius / (rings + 1));
        int currentRadius = radius - step;
        int baseRed = color.getRed();
        int baseGreen = color.getGreen();
        int baseBlue = color.getBlue();
        for (int i = 0; i < rings; i++) {
            if (currentRadius <= 0) {
                break;
            }
            int delta = (i % 2 == 0) ? 12 : -12;
            int red = clamp(baseRed + delta, 0, 255);
            Color ringColor = new Color(red, baseGreen, baseBlue);
            int d = currentRadius * 2;
            int x0 = (int) Math.round(x - currentRadius);
            int y0 = (int) Math.round(y - currentRadius);
            g2.setColor(ringColor);
            g2.drawOval(x0, y0, d, d);
            currentRadius -= step;
        }

        // Subtle lighter center highlight.
        int centerRadius = Math.max(2, radius / 6);
        int cx = (int) Math.round(x - centerRadius);
        int cy = (int) Math.round(y - centerRadius);
        Color center = new Color(clamp(baseRed + 18, 0, 255), baseGreen, baseBlue);
        g2.setColor(center);
        g2.fillOval(cx, cy, centerRadius * 2, centerRadius * 2);
    }

    private static int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }
}

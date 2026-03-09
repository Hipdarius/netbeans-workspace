
public class Ball {
    private double x;
    private double y;
    private int radius;
    private double xStep;
    private double yStep;
    private java.awt.Color outline = java.awt.Color.BLACK;

    public Ball(double x, double y, int radius, double xStep, double yStep) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xStep = xStep;
        this.yStep = yStep;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public double getxStep() {
        return xStep;
    }

    public double getyStep() {
        return yStep;
    }

    public void setxStep(int xStep) {
        this.xStep = xStep;
    }

    public void setyStep(int yStep) {
        this.yStep = yStep;
    }

    public void doStep(int panelW, int panelH) {
        if (panelW <= 0 || panelH <= 0) {
            return;
        }
        x += xStep;
        y += yStep;

        int minX = radius;
        int maxX = Math.max(radius, panelW - radius);
        int minY = radius;
        int maxY = Math.max(radius, panelH - radius);

        if (x < minX) {
            x = minX;
            xStep = Math.abs(xStep);
        } else if (x > maxX) {
            x = maxX;
            xStep = -Math.abs(xStep);
        }

        if (y < minY) {
            y = minY;
            yStep = Math.abs(yStep);
        } else if (y > maxY) {
            y = maxY;
            yStep = -Math.abs(yStep);
        }
    }

    public void draw(java.awt.Graphics g) {
        g.setColor(outline);
        g.drawOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
    }

    public boolean isInside(int pX, int pY) {
        double a = pX - x;
        double b = pY - y;
        double c = Math.sqrt(a * a + b * b);
        return c <= radius;
    }

    public void setOutline(java.awt.Color outline) {
        this.outline = outline;
    }

    public java.awt.Color getOutline() {
        return outline;
    }
}

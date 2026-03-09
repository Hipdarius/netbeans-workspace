import java.awt.Color;
import java.awt.Graphics;

public abstract class Emoticon extends Ball {
    private static final Color FACE = new Color(255, 225, 60);
    private static final Color EYE_WHITE = Color.WHITE;
    private static final Color IRIS = new Color(70, 130, 180);
    private static final Color PUPIL = Color.BLACK;

    public Emoticon(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    public void draw(Graphics g) {
        int radius = super.getRadius();
        int diameter = radius * 2;
        int faceX = (int) Math.round(super.getX() - radius);
        int faceY = (int) Math.round(super.getY() - radius);

        // Face
        g.setColor(FACE);
        g.fillOval(faceX, faceY, diameter, diameter);
        super.draw(g);

        int eyeOffsetX = (int) Math.round(radius * 0.45);
        int eyeOffsetY = (int) Math.round(radius * 0.35);
        int eyeRadius = Math.max(2, (int) Math.round(radius * 0.18));
        // Left eye
        drawEye(g, (int) Math.round(super.getX() - eyeOffsetX), (int) Math.round(super.getY() - eyeOffsetY), eyeRadius);
        // Right eye
        drawEye(g, (int) Math.round(super.getX() + eyeOffsetX), (int) Math.round(super.getY() - eyeOffsetY), eyeRadius);

        // Mouth
        drawMouth(g, faceX, faceY, diameter);
    }

    private void drawEye(Graphics g, int centerX, int centerY, int eyeRadius) {
        int eyeDiameter = eyeRadius * 2;
        // Eye white
        g.setColor(EYE_WHITE);
        g.fillOval(centerX - eyeRadius, centerY - eyeRadius, eyeDiameter, eyeDiameter);
        // Eye outline
        g.setColor(Color.BLACK);
        g.drawOval(centerX - eyeRadius, centerY - eyeRadius, eyeDiameter, eyeDiameter);

        int irisRadius = Math.max(1, (int) Math.round(eyeRadius * 0.6));
        int pupilRadius = Math.max(1, (int) Math.round(eyeRadius * 0.35));
        // Iris
        g.setColor(IRIS);
        g.fillOval(centerX - irisRadius, centerY - irisRadius, irisRadius * 2, irisRadius * 2);
        // Pupil
        g.setColor(PUPIL);
        g.fillOval(centerX - pupilRadius, centerY - pupilRadius, pupilRadius * 2, pupilRadius * 2);
    }

    protected abstract void drawMouth(Graphics g, int faceX, int faceY, int diameter);
}

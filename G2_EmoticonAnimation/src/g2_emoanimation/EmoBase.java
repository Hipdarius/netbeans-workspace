package g2_emoanimation;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class EmoBase extends MovingBall {
    private static final Color FACE = new Color(255, 225, 60);
    private static final Color EYE_WHITE = Color.WHITE;
    private static final Color IRIS = new Color(70, 130, 180);
    private static final Color PUPIL = Color.BLACK;

    public EmoBase(double x, double y, double vx, double vy, int radius) {
        super(x, y, vx, vy, radius);
    }

    @Override
    public void draw(Graphics2D g2) {
        int radius = getRadius();
        int diameter = radius * 2;
        int faceX = (int) Math.round(getX() - radius);
        int faceY = (int) Math.round(getY() - radius);

        // Face circle: filled yellow with black outline.
        g2.setColor(FACE);
        g2.fillOval(faceX, faceY, diameter, diameter);
        g2.setColor(Color.BLACK);
        g2.drawOval(faceX, faceY, diameter, diameter);

        // Eyes: same positions and scaling for all emoticons.
        int eyeOffsetX = (int) Math.round(radius * 0.45);
        int eyeOffsetY = (int) Math.round(radius * 0.35);
        int eyeRadius = Math.max(2, (int) Math.round(radius * 0.18));
        drawEye(g2, (int) Math.round(getX() - eyeOffsetX), (int) Math.round(getY() - eyeOffsetY), eyeRadius);
        drawEye(g2, (int) Math.round(getX() + eyeOffsetX), (int) Math.round(getY() - eyeOffsetY), eyeRadius);

        // Mouth is delegated to the specific emoticon.
        drawMouth(g2, faceX, faceY, diameter);
    }

    private void drawEye(Graphics2D g2, int centerX, int centerY, int eyeRadius) {
        int eyeDiameter = eyeRadius * 2;
        g2.setColor(EYE_WHITE);
        g2.fillOval(centerX - eyeRadius, centerY - eyeRadius, eyeDiameter, eyeDiameter);
        g2.setColor(Color.BLACK);
        g2.drawOval(centerX - eyeRadius, centerY - eyeRadius, eyeDiameter, eyeDiameter);

        int irisRadius = Math.max(1, (int) Math.round(eyeRadius * 0.6));
        int pupilRadius = Math.max(1, (int) Math.round(eyeRadius * 0.35));

        g2.setColor(IRIS);
        g2.fillOval(centerX - irisRadius, centerY - irisRadius, irisRadius * 2, irisRadius * 2);
        g2.setColor(PUPIL);
        g2.fillOval(centerX - pupilRadius, centerY - pupilRadius, pupilRadius * 2, pupilRadius * 2);
    }

    protected abstract void drawMouth(Graphics2D g2, int faceX, int faceY, int diameter);
}

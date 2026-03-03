import java.awt.Graphics2D;

public class EmoSurprised extends EmoBase {
    public EmoSurprised(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics2D g2, int faceX, int faceY, int diameter) {
        int mouthRadius = (int) Math.round(diameter * 0.12);
        int mouthX = (int) Math.round(getX() - mouthRadius);
        int mouthY = faceY + (int) Math.round(diameter * 0.6) - mouthRadius;
        g2.drawOval(mouthX, mouthY, mouthRadius * 2, mouthRadius * 2);
    }
}

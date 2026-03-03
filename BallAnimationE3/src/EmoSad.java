import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class EmoSad extends EmoBase {
    public EmoSad(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics2D g2, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.5);
        int mouthH = (int) Math.round(diameter * 0.3);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.62);
        g2.setStroke(new BasicStroke(3));
        g2.drawArc(mouthX, mouthY, mouthW, mouthH, 0, 180);
        g2.setStroke(new BasicStroke(1));
    }
}

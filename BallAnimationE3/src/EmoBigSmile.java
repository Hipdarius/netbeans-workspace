import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class EmoBigSmile extends EmoBase {
    public EmoBigSmile(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics2D g2, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.65);
        int mouthH = (int) Math.round(diameter * 0.4);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.5);
        g2.setStroke(new BasicStroke(4));
        g2.drawArc(mouthX, mouthY, mouthW, mouthH, 180, 180);
        g2.setStroke(new BasicStroke(1));
    }
}

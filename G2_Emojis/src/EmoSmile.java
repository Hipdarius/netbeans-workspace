import java.awt.Graphics;

public class EmoSmile extends Emoticon {
    public EmoSmile(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics g, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.55);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.6);
        int height = Math.max(2, (int) Math.round(diameter * 0.18));
        int centerX = mouthX + mouthW / 2;

        for (int x = mouthX; x <= mouthX + mouthW; x++) {
            int dx = x - centerX;
            int y = mouthY + (dx * dx) / Math.max(1, (mouthW * mouthW) / (4 * height));
            g.drawLine(x, y, x, y);
        }
    }
}

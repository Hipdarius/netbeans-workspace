import java.awt.Graphics;

public class EmoBigSmile extends Emoticon {
    public EmoBigSmile(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics g, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.7);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.56);
        int height = Math.max(3, (int) Math.round(diameter * 0.22));
        int thickness = Math.max(2, (int) Math.round(diameter * 0.04));
        int centerX = mouthX + mouthW / 2;

        for (int t = 0; t < thickness; t++) {
            for (int x = mouthX; x <= mouthX + mouthW; x++) {
                int dx = x - centerX;
                int y = mouthY + t + (dx * dx) / Math.max(1, (mouthW * mouthW) / (4 * height));
                g.drawLine(x, y, x, y);
            }
        }
    }
}

import java.awt.Graphics;

public class EmoBigSmile extends Emoticon {
    public EmoBigSmile(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics g, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.7);
        int mouthH = Math.max(3, (int) Math.round(diameter * 0.22));
        int thickness = Math.max(2, (int) Math.round(diameter * 0.04));
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.52);

        for (int t = 0; t < thickness; t++) {
            g.drawArc(mouthX, mouthY + t, mouthW, mouthH, 180, 180);
        }
    }
}

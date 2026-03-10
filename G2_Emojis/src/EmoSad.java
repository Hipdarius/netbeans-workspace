import java.awt.Graphics;

public class EmoSad extends Emoticon {
    public EmoSad(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics g, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.55);
        int mouthH = Math.max(2, (int) Math.round(diameter * 0.18));
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.58);

        g.drawArc(mouthX, mouthY, mouthW, mouthH, 0, 180);
    }
}

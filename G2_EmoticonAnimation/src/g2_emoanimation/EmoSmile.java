package g2_emoanimation;

import java.awt.Color;
import java.awt.Graphics2D;

public class EmoSmile extends EmoBase {
    public EmoSmile(double x, double y, double vx, double vy, int radius) {
        super(x, y, vx, vy, radius);
    }

    @Override
    protected void drawMouth(Graphics2D g2, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.55);
        int mouthH = (int) Math.round(diameter * 0.35);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.50);

        g2.setColor(Color.BLACK);
        g2.drawArc(mouthX, mouthY, mouthW, mouthH, 180, 180);
    }
}

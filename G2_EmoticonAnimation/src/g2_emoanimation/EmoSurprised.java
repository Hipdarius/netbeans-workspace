package g2_emoanimation;

import java.awt.Color;
import java.awt.Graphics2D;

public class EmoSurprised extends EmoBase {
    public EmoSurprised(double x, double y, double vx, double vy, int radius) {
        super(x, y, vx, vy, radius);
    }

    @Override
    protected void drawMouth(Graphics2D g2, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.25);
        int mouthH = (int) Math.round(diameter * 0.30);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.55);

        g2.setColor(Color.BLACK);
        g2.fillOval(mouthX, mouthY, mouthW, mouthH);

        // Small highlight to keep the "O" from looking flat.
        g2.setColor(new Color(255, 255, 255, 180));
        int highlightW = Math.max(2, mouthW / 3);
        int highlightH = Math.max(2, mouthH / 3);
        g2.fillOval(mouthX + mouthW / 3, mouthY + mouthH / 4, highlightW, highlightH);
    }
}

package g2_emoanimation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class EmoBigSmile extends EmoBase {
    public EmoBigSmile(double x, double y, double vx, double vy, int radius) {
        super(x, y, vx, vy, radius);
    }

    @Override
    protected void drawMouth(Graphics2D g2, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.65);
        int mouthH = (int) Math.round(diameter * 0.45);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.48);

        Stroke old = g2.getStroke();
        float thickness = Math.max(2f, (float) (diameter * 0.08));
        g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(Color.BLACK);
        g2.drawArc(mouthX, mouthY, mouthW, mouthH, 180, 180);
        g2.setStroke(old);
    }
}

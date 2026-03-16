import java.awt.Graphics;

public class EmoSurprised extends Emoticon {
    public EmoSurprised(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics g, int faceX, int faceY, int diameter) {
        int mouthRadius = (int) Math.round(diameter * 0.12);
        int mouthX = (int) Math.round(super.getX() - mouthRadius);
        int mouthY = faceY + (int) Math.round(diameter * 0.6) - mouthRadius;
        
        // int mouthX = x-radius * 0.25;
        // int mouthY = y+radius * 0,25;
        // int mouthW = radius * 2 * 0,25;
        // int mouthH = radius * 2 * 0,25;
        //g.fillOval(mouthX, mouthY, mouthW, mouthH);
        
        g.drawOval(mouthX, mouthY, mouthRadius * 2, mouthRadius * 2);
    }
}

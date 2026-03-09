import java.awt.Color;
import java.awt.Graphics;

public class EmoFreaky extends Emoticon {
    private static final Color FACE = new Color(255, 225, 60);
    private static final Color TONGUE = new Color(255, 120, 160);

    public EmoFreaky(double x, double y, int radius, double xStep, double yStep) {
        super(x, y, radius, xStep, yStep);
    }

    @Override
    protected void drawMouth(Graphics g, int faceX, int faceY, int diameter) {
        int mouthW = (int) Math.round(diameter * 0.55);
        int mouthX = faceX + (diameter - mouthW) / 2;
        int mouthY = faceY + (int) Math.round(diameter * 0.62);

        for (int x = mouthX; x <= mouthX + mouthW; x++) {
            g.drawLine(x, mouthY, x, mouthY);
        }

        if (isTongueVisible()) {
            int tongueW = Math.max(2, (int) Math.round(mouthW * 0.38));
            int tongueH = Math.max(2, (int) Math.round(diameter * 0.32));
            int tongueX = mouthX + (mouthW - tongueW) / 2;
            int tongueY = mouthY - tongueH / 2;
            g.setColor(TONGUE);
            g.fillOval(tongueX, tongueY, tongueW, tongueH);
            g.setColor(FACE);
            g.fillRect(tongueX, tongueY, tongueW, tongueH / 2);
        }
    }

    private boolean isTongueVisible() {
        return (System.currentTimeMillis() / 500) % 2 == 0;
    }
}

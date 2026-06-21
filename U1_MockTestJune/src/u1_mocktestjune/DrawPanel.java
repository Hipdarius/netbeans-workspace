package u1_mocktestjune;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

    private Game game = null;

    public DrawPanel() {
        setBackground(Color.BLACK);
    }

    public void setGame(Game game) {
        // Store the game object that should be drawn.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the black background, then draw the game if possible.
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(520, 320);
    }
}

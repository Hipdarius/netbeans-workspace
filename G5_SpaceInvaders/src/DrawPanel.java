import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    private Game game;

    public DrawPanel() {
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(MainFrame.PANEL_WIDTH, MainFrame.PANEL_HEIGHT));
        setFocusable(true);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (game != null) {
            game.draw(g);
        }
    }
}

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MainFrame extends JFrame {
    public static final int PANEL_WIDTH = 800;
    public static final int PANEL_HEIGHT = 600;
    private static final int TIMER_DELAY = 10;

    private Game game;
    private Timer timer;
    private DrawPanel drawPanel;

    public MainFrame() {
        initComponents();
        initGame();
        initTimer();
    }

    private void initComponents() {
        setTitle("Space Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();
        setContentPane(drawPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (game != null) {
                    game.setPlayer(e.getX(), drawPanel.getWidth());
                    drawPanel.repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && game != null) {
                    game.launchMissile();
                }
            }
        };

        drawPanel.addMouseMotionListener(mouseAdapter);
        drawPanel.addMouseListener(mouseAdapter);
    }

    private void initGame() {
        game = new Game(drawPanel.getPreferredSize().width, drawPanel.getPreferredSize().height);
        drawPanel.setGame(game);
    }

    private void initTimer() {
        timer = new Timer(TIMER_DELAY, e -> {
            game.move(drawPanel.getWidth(), drawPanel.getHeight());
            if (game.isOver()) {
                game = new Game(drawPanel.getWidth(), drawPanel.getHeight());
                drawPanel.setGame(game);
            }
            drawPanel.repaint();
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

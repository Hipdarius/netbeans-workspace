import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MainFrame extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    public static final int PANEL_WIDTH = 800;
    public static final int PANEL_HEIGHT = 600;
    private static final int TIMER_DELAY = 10;

    private transient Game game;
    private Timer timer;

    public MainFrame() {
        initComponents();
        stepButton.setVisible(false);
        setTitle("Space Invaders"); 
        initGame();
        initTimer();
        attachMouseControls();
    }

    private void initGame() {
        game = new Game(drawPanel.getWidth(), drawPanel.getPreferredSize().height);
        drawPanel.setGame(game);
    }

    private void initTimer() {
        timer = new Timer(TIMER_DELAY, this::stepButtonActionPerformed);
        timer.start();
    }

    private void attachMouseControls() {
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

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {
        game.move(drawPanel.getWidth(), drawPanel.getHeight());
        if (game.isOver()) {
            game = new Game(drawPanel.getWidth(), drawPanel.getHeight());
            drawPanel.setGame(game);
        }
        drawPanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drawPanel = new DrawPanel();
        stepButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        drawPanel.setBackground(new java.awt.Color(255, 255, 255));
        drawPanel.setForeground(new java.awt.Color(255, 255, 255));

        stepButton.setText("stepButton");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawPanelLayout.createSequentialGroup()
                .addContainerGap(549, Short.MAX_VALUE)
                .addComponent(stepButton)
                .addGap(159, 159, 159))
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawPanelLayout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(stepButton)
                .addGap(259, 259, 259))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DrawPanel drawPanel;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables
}

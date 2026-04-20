package g6_spaceinvadersii;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class MainFrame extends javax.swing.JFrame {

    private int TIMER_DELAY = 20;
    private Timer timer;
    private Game game;

    public MainFrame() {
        initComponents();
        setTitle("Space Invaders II");
        initGame();
        initTimer();
        stepButton.setVisible(false);
    }
    
    private void initGame() {
        int width = drawPanel.getPreferredSize().width;
        int height = drawPanel.getPreferredSize().height;
        game = new Game(width, height);
        drawPanel.setGame(game);
        scoreLabel.setText(game.getScoreText());
    }

    private void initTimer() {
        timer = new Timer(TIMER_DELAY, this::onTimerTick);
    }

    private void onTimerTick(ActionEvent e) {
        game.doStep();
        scoreLabel.setText(game.getScoreText());
        drawPanel.repaint();

        if (game.checkGameOver()) {
            timer.stop();
            game.getAllScores().add(game.getScore());
            game.sortScores();
            refreshScoreList();
        }
    }

    private void refreshScoreList() {
        scoreList.setListData(game.getAllScores().toArray());
    }

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        timer.stop();
        game.reset();
        scoreLabel.setText(game.getScoreText());
        drawPanel.repaint();
        timer.start();
    }//GEN-LAST:event_startButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        game.doStep();
        drawPanel.repaint();
    }//GEN-LAST:event_stepButtonActionPerformed

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drawPanel = new g6_spaceinvadersii.DrawPanel();
        rightPanel = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scoreList = new javax.swing.JList();
        startButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        drawPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                drawPanelMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                drawPanelMouseMoved(evt);
            }
        });
        drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                drawPanelMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                drawPanelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        rightPanel.setLayout(new javax.swing.BoxLayout(rightPanel, javax.swing.BoxLayout.Y_AXIS));

        scoreLabel.setText("Score: 0");
        rightPanel.add(scoreLabel);

        jScrollPane1.setViewportView(scoreList);

        rightPanel.add(jScrollPane1);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        rightPanel.add(startButton);

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });
        rightPanel.add(stepButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseClicked
    }//GEN-LAST:event_drawPanelMouseClicked

    private void drawPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseEntered
    }//GEN-LAST:event_drawPanelMouseEntered

    private void drawPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMousePressed
        game.newMissile();
    }//GEN-LAST:event_drawPanelMousePressed

    private void drawPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseReleased
    }//GEN-LAST:event_drawPanelMouseReleased

    private void drawPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseDragged
    }//GEN-LAST:event_drawPanelMouseDragged

    private void drawPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanelMouseMoved
        game.setPlayerXCenter(evt.getX());
        drawPanel.repaint();
    }//GEN-LAST:event_drawPanelMouseMoved

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private g6_spaceinvadersii.DrawPanel drawPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JList scoreList;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables
}

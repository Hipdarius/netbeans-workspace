package e9_fallingballs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class MainFrame extends javax.swing.JFrame {
    private static final int TIMER_DROP_MS = 90;
    private static final int TIMER_MOVE_MS = 40;

    private Timer timerDrop;
    private Timer timerMove;
    private Balls balls;

    public MainFrame() {
        initComponents();
        setTitle("Falling balls");
        initCustomUi();
        initTimers();
        wireActions();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    private void initCustomUi() {
        rightPanel.setBackground(new Color(245, 245, 245));
        rightPanel.setPreferredSize(new Dimension(220, 450));

        labelNumberOfBalls.setFont(labelNumberOfBalls.getFont().deriveFont(Font.PLAIN, 14f));
        numberOfBallsSlider.setMajorTickSpacing(10);
        numberOfBallsSlider.setMinorTickSpacing(1);
        numberOfBallsSlider.setPaintTicks(true);
        numberOfBallsSlider.setPaintLabels(true);

        dropButton.setVisible(false);
        stepButton.setVisible(false);
    }

    private void initTimers() {
        timerDrop = new Timer(TIMER_DROP_MS, (ActionEvent e) -> {
            if (balls != null) {
                balls.dropBall();
                drawPanel.repaint();
            }
        });

        timerMove = new Timer(TIMER_MOVE_MS, (ActionEvent e) -> {
            if (balls != null) {
                balls.move(drawPanel.getHeight());
                drawPanel.repaint();
            }
        });
    }

    private void wireActions() {
        startButton.addActionListener((ActionEvent e) -> onStart());
        resetButton.addActionListener((ActionEvent e) -> onReset());
        dropButton.addActionListener((ActionEvent e) -> onDrop());
        stepButton.addActionListener((ActionEvent e) -> onStep());
    }

    private void onStart() {
        int n = numberOfBallsSlider.getValue();
        int w = drawPanel.getWidth();
        int h = drawPanel.getHeight();
        if (w <= 0 || h <= 0) {
            Dimension pref = drawPanel.getPreferredSize();
            w = pref.width;
            h = pref.height;
        }
        balls = new Balls(n, w, h);
        drawPanel.setBalls(balls);
        timerMove.start();
        timerDrop.start();
        startButton.setEnabled(false);
    }

    private void onReset() {
        timerDrop.stop();
        timerMove.stop();
        balls = null;
        drawPanel.setBalls(null);
        numberOfBallsSlider.setValue(30);
        startButton.setEnabled(true);
    }

    private void onDrop() {
        if (balls != null) {
            balls.dropBall();
            drawPanel.repaint();
        }
    }

    private void onStep() {
        if (balls != null) {
            balls.move(drawPanel.getHeight());
            drawPanel.repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drawPanel = new e9_fallingballs.DrawPanel();
        rightPanel = new javax.swing.JPanel();
        labelNumberOfBalls = new javax.swing.JLabel();
        numberOfBallsSlider = new javax.swing.JSlider();
        buttonsRow = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        dropButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rightPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        rightPanel.setLayout(new javax.swing.BoxLayout(rightPanel, javax.swing.BoxLayout.Y_AXIS));

        labelNumberOfBalls.setText("Number of balls:");
        labelNumberOfBalls.setAlignmentX(LEFT_ALIGNMENT);
        rightPanel.add(labelNumberOfBalls);
        rightPanel.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 8)));

        numberOfBallsSlider.setMinimum(1);
        numberOfBallsSlider.setMaximum(60);
        numberOfBallsSlider.setValue(30);
        numberOfBallsSlider.setAlignmentX(LEFT_ALIGNMENT);
        rightPanel.add(numberOfBallsSlider);
        rightPanel.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 16)));

        buttonsRow.setLayout(new javax.swing.BoxLayout(buttonsRow, javax.swing.BoxLayout.X_AXIS));

        startButton.setText("Start");
        buttonsRow.add(startButton);

        buttonsRow.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(8, 0)));

        resetButton.setText("Reset");
        buttonsRow.add(resetButton);

        buttonsRow.setAlignmentX(LEFT_ALIGNMENT);
        rightPanel.add(buttonsRow);
        rightPanel.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 8)));

        dropButton.setText("Drop");
        dropButton.setAlignmentX(LEFT_ALIGNMENT);
        rightPanel.add(dropButton);
        rightPanel.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 8)));

        stepButton.setText("Step");
        stepButton.setAlignmentX(LEFT_ALIGNMENT);
        rightPanel.add(stepButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsRow;
    private e9_fallingballs.DrawPanel drawPanel;
    private javax.swing.JButton dropButton;
    private javax.swing.JLabel labelNumberOfBalls;
    private javax.swing.JSlider numberOfBallsSlider;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables
}
